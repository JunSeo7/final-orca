package com.groupware.orca.document.mapper;

import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import com.groupware.orca.approvalLine.vo.ApproverVo;
import com.groupware.orca.document.vo.*;
import com.groupware.orca.docTemplate.vo.TemplateVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DocumentMapper {

    // 결재선 등록 템플릿 카테고리 가져오기
    @Select("""
            SELECT CATEGORY_NO, NAME AS categoryName
            FROM DOC_TEMPLATE_CATEGORY
            WHERE DEL_YN = 'N'
            """)
    List<TemplateVo> getCategory();

    // 결재선 등록 카테고리번호 - 결재양식 제목 가져오기
    @Select("""
            SELECT CATEGORY_NO, TEMPLATE_NO, TITLE
            FROM DOC_TEMPLATE
            WHERE DEL_YN = 'N' AND CATEGORY_NO = #{categoryNo}
            """)
    List<TemplateVo> getTemplateByCategoryNo(int categoryNo);

    // 결재선 등록 카테고리번호 - 템플릿 내용 가져오기
    @Select("""
            SELECT TEMPLATE_NO, TITLE, CONTENT
            FROM DOC_TEMPLATE
            WHERE TEMPLATE_NO = #{templateNo}
            """)
    TemplateVo getTemplateContent(int templateNo);

    // 결재 작성 결재선 가져오기
    @Select("""
            SELECT AT.APPR_LINE_NO, AT.APPR_LINE_NAME, AT.CREATED_DATE, DT.TEMPLATE_NO, DT.TITLE,
                   DT.CONTENT, DTC.CATEGORY_NO, DTC.NAME AS CATEGORY_NAME
            FROM APPR_LINE_TEMPLATE AT
            JOIN DOC_TEMPLATE DT ON AT.TEMPLATE_NO = DT.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY DTC ON DT.CATEGORY_NO = DTC.CATEGORY_NO
            WHERE AT.TEMPLATE_NO = #{templateNo} AND AT.WRITER_NO IS NULL AND AT.DEL_YN = 'N'
            """)
    ApprovalLineVo getTemplateApprLine(int templateNo);

    // 결재 작성 결재자 여러명 가져오기
    @Select("""
            SELECT AI.APPROVER_INFO_NO, AI.APPR_LINE_NO, AI.SEQ, AI.APPROVER_CLASSIFICATION_NO, PI.NAME AS approverName,
                   D.DEPT_CODE, D.PARTNAME AS DEPT_NAME, P.NAME_OF_POSITION AS positionName, AI.APPROVER_NO, PI.POSITION_CODE
            FROM APPROVER_INFO AI
            JOIN PERSONNEL_INFORMATION PI ON AI.APPROVER_NO = PI.EMP_NO
            JOIN DEPARTMENT D ON D.DEPT_CODE = PI.DEPT_CODE
            JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE APPR_LINE_NO = #{apprLineNo}
            ORDER BY AI.SEQ
            """)
    List<ApproverVo> getApproverList(int apprLineNo);

    // 결재 작성 (임시저장일 경우 기안날짜가 null / 기안 올리면 기안날짜 sysdate)
    @Insert("""
            <script>
            INSERT INTO DOCUMENT (DOC_NO, WRITER_NO, TEMPLATE_NO, STATUS, TITLE, CONTENT, URGENT, ENROLL_DATE
            <if test='status == 2'>, CREDIT_DATE</if>)
            VALUES (SEQ_DOCUMENT.NEXTVAL, #{writerNo}, #{templateNo}, #{status}, #{title}, #{content}, #{urgent}, SYSDATE
            <if test='status == 2'>, SYSDATE</if>)
            </script>
            """)
    // 결재 시쿼스 번호 가져오기
    @SelectKey(statement = "SELECT SEQ_DOCUMENT.CURRVAL FROM dual", keyProperty = "docNo", before = false, resultType = int.class)
    int writeDocument(DocumentVo vo);

    // 결재 작성 - 결재선 업로드
    @Insert("""
            INSERT INTO APPR_LINE
            (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, DEPT_CODE, POSITION_CODE, "COMMENT", APPROVER_CLASSIFICATION_NO)
            VALUES
            (SEQ_APPR_LINE.NEXTVAL, #{docNo}, #{seq}, #{approverNo}, #{deptCode}, #{positionCode}, #{comment}, #{approverClassificationNo})
            """)
    int writeDocumentApprover(ApproverVo vo);

    // 결재 작성 - 참조자 업로드
    @Insert("""
            INSERT INTO DOC_REFERENCE_LIST (REFERENCE_LIST_NO, DOC_NO, REFERRER_NO)
            VALUES (SEQ_DOC_REFERENCE_LIST.NEXTVAL, #{docNo}, #{referrerNo})
            """)
    int writeDocumentReferrer(ReferencerVo vo);

    // 결재 작성 - 파일 업로드
    @Insert("""
            INSERT INTO DOC_FILES (FILE_NO, DOC_NO, CHANGE_NAME, ORIGIN_NAME)
            VALUES (SEQ_DOC_FILES.NEXTVAL, #{docNo}, #{changeName}, #{originName})
            """)
    int writeDocumentFile(DocFileVo vo);


    // 1: 임시저장 2: 기안 3: 종결 4: 반려  5: 결재취소
    // (기안 완) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @Select("""
            <script>
            SELECT D.DOC_NO, D.WRITER_NO, D.STATUS, D.TITLE, D.CONTENT, D.ENROLL_DATE, TO_CHAR(D.CREDIT_DATE, 'YYYY-MM-DD') AS CREDIT_DATE, D.STATUS, D.URGENT,
                   T.TITLE AS templateTitle, TC.NAME AS categoryName, DSL.DOC_STATUS_NAME AS statusName,
                   PI.NAME AS writerName, DEPT.PARTNAME AS deptName, P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D
            JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE D.DEL_YN = 'N'
            <if test="status != null">
                AND D.STATUS = #{status}
            </if>
            AND D.WRITER_NO = #{loginUserNo}
            ORDER BY D.URGENT DESC, D.CREDIT_DATE DESC
            </script>
            """)
    List<DocumentVo> getDocumentList(String loginUserNo, Integer status);

    // (공람) - 종결된 결재 중 참조인에 해당하는 사람에게 보임
    @Select("""
            SELECT D.DOC_NO, D.WRITER_NO, D.STATUS, D.TITLE, D.CONTENT, D.ENROLL_DATE, TO_CHAR(D.CREDIT_DATE, 'YYYY-MM-DD') AS CREDIT_DATE, D.STATUS, D.URGENT,
                   T.TITLE AS templateTitle, TC.NAME AS categoryName, DSL.DOC_STATUS_NAME AS statusName,
                   PI.NAME AS writerName, DEPT.PARTNAME AS deptName, P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D
            JOIN DOC_REFERENCE_LIST DRL ON D.DOC_NO = DRL.DOC_NO
            JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE D.DEL_YN = 'N' AND DRL.REFERRER_NO = #{loginUserNo} AND D.STATUS = 3
            ORDER BY D.URGENT DESC, D.CREDIT_DATE DESC
            """)
    List<DocumentVo> getPublicDocumentList(String loginUserNo);

    // 내가 받은 결재 (앞 결재자의 상태가 2인지 확인 하고, 그러면 보이게하기..)
    @Select("""
            SELECT D.DOC_NO, D.WRITER_NO, D.STATUS, D.TITLE, D.CONTENT, D.ENROLL_DATE, TO_CHAR(D.CREDIT_DATE, 'YYYY-MM-DD') AS CREDIT_DATE, D.STATUS,
                   D.URGENT, T.TITLE AS templateTitle, TC.NAME AS categoryName, DSL.DOC_STATUS_NAME AS statusName,
                   PI.NAME AS writerName, DEPT.PARTNAME AS deptName, P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D
            JOIN APPR_LINE A ON D.DOC_NO = A.DOC_NO
            JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE A.APPROVER_NO = #{loginUserNo}
              AND A.APPROVAL_STAGE IN (1, 2)
              AND (D.STATUS = 2 OR D.STATUS = 3 OR D.STATUS = 4)
              AND (A.SEQ = 1 OR
                   EXISTS (
                       SELECT 1
                       FROM APPR_LINE B
                       WHERE A.DOC_NO = B.DOC_NO
                         AND A.SEQ - 1 = B.SEQ
                         AND B.APPROVAL_STAGE = 2
                   )
                  )
            ORDER BY
                 CASE
                     WHEN D.STATUS = 2 THEN 0
                     ELSE 1
                 END,
                 CASE
                     WHEN D.STATUS = 2 THEN D.URGENT
                     ELSE NULL
                 END DESC,
                 D.CREDIT_DATE DESC
            """)
    List<DocumentVo> getSendDocumentList(String loginUserNo);

    //검색
    @Select("""
        <script>
        SELECT D.DOC_NO, D.WRITER_NO, D.STATUS, D.TITLE, D.CONTENT, D.ENROLL_DATE, TO_CHAR(D.CREDIT_DATE, 'YYYY-MM-DD') AS CREDIT_DATE, D.STATUS, D.URGENT,
               T.TITLE AS templateTitle, TC.NAME AS categoryName, DSL.DOC_STATUS_NAME AS statusName,
               PI.NAME AS writerName, DEPT.PARTNAME AS deptName, P.NAME_OF_POSITION AS positionName
        FROM DOCUMENT D
        JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
        JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
        JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
        JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
        LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
        LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
        WHERE D.DEL_YN = 'N'
         <if test="status != null and status != ''">
             AND D.STATUS = #{status}
         </if>
        <choose>
          <when test="searchType == 'writerName'">
              AND PI.NAME LIKE '%' || #{searchText} || '%'
          </when>
          <when test="searchType == 'title'">
              AND D.TITLE LIKE '%' || #{searchText} || '%'
          </when>
          <when test="searchType == 'content'">
              AND D.CONTENT LIKE '%' || #{searchText} || '%'
          </when>
        </choose>
        AND D.WRITER_NO = #{loginUserNo}
        ORDER BY D.URGENT DESC, D.CREDIT_DATE DESC
        </script>
        """)
    List<DocumentVo> searchDocumentList(@Param("loginUserNo") String loginUserNo,
                                        @Param("searchType") String searchType,
                                        @Param("searchText") String searchText,
                                        @Param("status") Integer status);

    // 상세보기
    // 결재 문서 조회(카테고리, 양식, 기안자관련)
    @Select("""
            SELECT D.DOC_NO, D.WRITER_NO, D.STATUS, D.TITLE, D.CONTENT, D.ENROLL_DATE, TO_CHAR(D.CREDIT_DATE, 'YYYY-MM-DD HH24:MI') AS CREDIT_DATE, D.STATUS,
                   D.URGENT, T.TITLE AS templateTitle, TC.NAME AS categoryName, DSL.DOC_STATUS_NAME AS statusName,
                   PI.NAME AS writerName, DEPT.PARTNAME AS deptName, P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D
            JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE D.DOC_NO = #{docNo}
            """)
    DocumentVo getDocumentByNo(int docNo);

    // 결재선 목록 조회
    @Select("""
            SELECT DISTINCT AL.DOC_NO AS approvalDocNo, AL.SEQ, TO_CHAR(AL.APPROVAL_DATE, 'YYYY-MM-DD HH24:MI') AS APPROVAL_DATE
                            , AL.APPROVER_CLASSIFICATION_NO, AL."COMMENT", PI.NAME AS approverName, DEPT.PARTNAME AS deptName
                            , P.NAME_OF_POSITION AS positionName, DRL.REFERRER_NO, AL.APPROVAL_STAGE, ASL.APPR_STAGE_NAME AS apprStageName, AL.APPROVER_NO
            FROM APPR_LINE AL
            JOIN PERSONNEL_INFORMATION PI ON AL.APPROVER_NO = PI.EMP_NO
            LEFT JOIN APPR_STAGE_LIST ASL ON AL.APPROVAL_STAGE = ASL.APPR_STAGE_NO
            LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = AL.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = AL.POSITION_CODE
            WHERE AL.DOC_NO = #{docNo}
            ORDER BY AL.SEQ
            """)
    List<ApproverVo> getApprovalLineByNo(int docNo);

    // 참조인 목록 조회
    @Select("""
            SELECT DISTINCT DL.DOC_NO, PI.NAME AS referrerName, DEPT.PARTNAME AS deptName,
                   P.NAME_OF_POSITION AS positionName, DRL.REFERRER_NO AS referrerNo
            FROM DOC_REFERENCE_LIST DL
            JOIN PERSONNEL_INFORMATION PI ON DL.REFERRER_NO = PI.EMP_NO
            LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE DL.DOC_NO = #{docNo}
            """)
    List<ReferencerVo> getReferencerByNo(int docNo);

    // 파일 목록 조회
    @Select("""
            SELECT DISTINCT * FROM DOC_FILES
            WHERE DOC_NO = #{docNo} AND DEL_YN = 'N'
            """)
    List<DocFileVo> getDocFileByNo(int docNo);

    // 내 차례인지 확인하기 - 1이면 승인, 반려버튼 구현  -- 뒷순서의 결재자 - 대기(1) 상태 -- 마지막 결재자인 경우는 체크 안함
    @Select("""
            SELECT CASE
               WHEN (A.SEQ = 1 OR EXISTS (
                   SELECT 1
                   FROM APPR_LINE B
                   WHERE A.DOC_NO = B.DOC_NO
                     AND A.SEQ - 1 = B.SEQ
                     AND B.APPROVAL_STAGE = 2
                       ))
                       AND (
                           EXISTS (
                               SELECT 1
                               FROM APPR_LINE C
                               WHERE A.DOC_NO = C.DOC_NO
                                 AND A.SEQ + 1 = C.SEQ
                                 AND C.APPROVAL_STAGE = 1
                           )
                           OR
                           NOT EXISTS (
                               SELECT 1
                               FROM APPR_LINE C
                               WHERE A.DOC_NO = C.DOC_NO
                                 AND A.SEQ + 1 = C.SEQ
                           )
                       )
                       THEN 1
                       ELSE 0
                   END AS isMyTurn
            FROM APPR_LINE A
            WHERE A.DOC_NO = #{docNo}
              AND A.APPROVER_NO = #{loginUserNo}
              AND A.APPROVAL_STAGE = 1
            """)
    Integer isMyTurn(int docNo, String loginUserNo);


    // 기안서 수정 (임시저장 상태일 경우만) // 제목, 내용, 상태(기안)만 수정가능
    @Update("""
            <script>
            UPDATE DOCUMENT
            <set>
                <if test="title != null">TITLE = #{title},</if>
                <if test="content != null">CONTENT = #{content},</if>
                <if test="urgent != null">URGENT = #{urgent},</if>
                <if test="templateNo != null">TEMPLATE_NO = #{templateNo},</if>
                <if test="status != null">STATUS = #{status},</if>
            </set>
            WHERE DOC_NO = #{docNo}
              AND WRITER_NO = #{writerNo}
              AND STATUS = 1
            </script>
            """)
    int editDocument(DocumentVo vo);

    // 기안서 상태 수정 (임시저장 상태일 경우 - 기안으로 )
    @Update("""
            <script>
            UPDATE DOCUMENT
            SET STATUS = #{status}
            WHERE DOC_NO = #{docNo}
              AND WRITER_NO = #{writerNo}
              AND STATUS = 1
            </script>
            """)
    int updateStatusDocument(DocumentVo vo);

    // 결재 기안 철회/삭제(아무도 결재승인 안했을 경우 가능)
    @Update("""
            UPDATE DOCUMENT
            SET DEL_YN = 'Y', STATUS = 5
            WHERE DOC_NO = #{docNo}
              AND WRITER_NO = #{loginUserNo}
              AND DOC_NO NOT IN (
                  SELECT DOC_NO
                  FROM APPR_LINE
                  WHERE DOC_NO = #{docNo}
                    AND APPROVAL_STAGE != 1
              )
            """)
    int deleteDocumentByNo(int docNo, String loginUserNo);
}