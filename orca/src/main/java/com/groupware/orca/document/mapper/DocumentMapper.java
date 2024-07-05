package com.groupware.orca.document.mapper;

import com.groupware.orca.document.vo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DocumentMapper {


    // 결재선 등록 템플릿 카테고리 가져오기
    @Select("SELECT CATEGORY_NO ,NAME categoryName FROM  DOC_TEMPLATE_CATEGORY WHERE DEL_YN='N'")
    List<TemplateVo> getCategory();
    // 결재선 등록 카테고리번호 - 결재양식 제목 가져오기
    @Select("SELECT CATEGORY_NO ,TEMPLATE_NO ,TITLE FROM DOC_TEMPLATE WHERE DEL_YN='N' AND CATEGORY_NO=#{categoryNo}")
    List<TemplateVo> getTemplateByCategoryNo(int categoryNo);
    // 결재선 등록 카테고리번호 - 템플릿 내용 가져오기
    @Select("SELECT TEMPLATE_NO, TITLE, CONTENT FROM DOC_TEMPLATE WHERE TEMPLATE_NO = #{templateNo}")
    TemplateVo getTemplateContent(int templateNo);
    // 결재 작성 결재선 가져오기
    @Select("SELECT AT.APPR_LINE_NO, AT.APPR_LINE_NAME , AT.CREATED_DATE, DT.TEMPLATE_NO , DT.TITLE , DT.CONTENT ,DTC.CATEGORY_NO, DTC.NAME AS CATEGORY_NAME FROM APPR_LINE_TEMPLATE AT JOIN DOC_TEMPLATE DT ON AT.TEMPLATE_NO = DT.TEMPLATE_NO JOIN DOC_TEMPLATE_CATEGORY DTC ON DT.CATEGORY_NO = DTC.CATEGORY_NO WHERE AT.TEMPLATE_NO = #{templateNo} AND WRITER_NO IS NULL")
    ApprovalLineVo getTemplateApprLine(int templateNo);
    // 결재 작성 결재자 여러명 가져오기
    @Select("""
            SELECT AI.APPROVER_INFO_NO , AI.APPR_LINE_NO, AI.SEQ, AI.APPROVER_CLASSIFICATION_NO, PI.NAME approverName,
            D.DEPT_CODE, D.PARTNAME AS DEPT_NAME, P.NAME_OF_POSITION positionName, AI.APPROVER_NO, PI.POSITION_CODE
            FROM APPROVER_INFO AI
            JOIN PERSONNEL_INFORMATION PI ON AI.APPROVER_NO = PI.EMP_NO
            JOIN DEPARTMENT D ON D.DEPT_CODE = PI.DEPT_CODE
            JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE APPR_LINE_NO = #{apprLineNo}
            ORDER BY AI.SEQ
            """)
    List<ApproverVo> getApproverList(int apprLineNo);


    // 결재 작성 (임시저장일 경우 기안날짜가 null / 기안 올리면 기안날짜 sysdate)
    @Insert("<script>" +
            "INSERT INTO DOCUMENT (DOC_NO, WRITER_NO, TEMPLATE_NO, STATUS, TITLE, CONTENT, URGENT, ENROLL_DATE" +
            "<if test='status == 2'>, CREDIT_DATE</if>) " +
            "VALUES (SEQ_DOCUMENT.NEXTVAL, #{writerNo}, #{templateNo}, #{status}, #{title}, #{content}, #{urgent}, SYSDATE" +
            "<if test='status == 2'>, SYSDATE</if>)" +
            "</script>")
    // 결재 시쿼스 번호 가져오기
    @SelectKey(statement = "SELECT SEQ_DOCUMENT.CURRVAL FROM dual", keyProperty = "docNo", before = false, resultType = int.class)
    int writeDocument(DocumentVo vo);
    // 결재 작성 - 결재선 업로드
    @Insert("""
    <script>
    INSERT ALL
    <foreach item="vo" collection="list">
     INTO APPR_LINE
    (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, DEPT_CODE, POSITION_CODE, APPROVAL_DATE, "COMMENT", APPROVER_CLASSIFICATION_NO)
    VALUES
    (SEQ_APPR_LINE.NEXTVAL, #{vo.docNo}, #{vo.seq}, #{vo.approverNo}, #{vo.deptCode}, #{vo.positionCode}, SYSDATE, #{vo.comment}, #{vo.approverClassificationNo})
    </foreach>
    SELECT * FROM DUAL
    </script>
    """)
    int writeDocumentApprover(@Param("list") List<ApproverVo> approverList);
    // 결재 작성 - 참조자 업로드
    @Insert("INSERT INTO DOC_REFERENCE_LIST (REFERENCE_LIST_NO, DOC_NO, REFERRER_NO) " +
            "VALUES (SEQ_DOC_REFERENCE_LIST.NEXTVAL, #{docNo}, #{referrerNo})")
    int writeDocumentReferrer(List<ReferencerVo> vo);
    // 결재 작성 - 파일 업로드
    @Insert("INSERT INTO DOC_FILES( FILE_NO ,DOC_NO ,CHANGE_NAME ,ORIGIN_NAME ) " +
            "VALUES (SEQ_DOC_FILES.NEXTVAL,#{docNo}, #{changeName},#{originName})")
    int writeDocumentFile(List<DocFileVo> vo);


    // (기안 완) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @Select("""
            SELECT D.DOC_NO ,D.WRITER_NO ,D.STATUS ,D.TITLE ,D.CONTENT ,D.ENROLL_DATE,D.CREDIT_DATE,STATUS ,D.URGENT ,T.TITLE AS templateTitle
            ,TC.NAME AS categoryName ,D.STATUS, DSL.DOC_STATUS_NAME statusName,PI.NAME AS writerName ,DEPT.PARTNAME AS deptName
            ,P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE D.DEL_YN ='N' AND D.STATUS = 2 AND D.WRITER_NO = #{loginUserNo}
            ORDER BY D.CREDIT_DATE DESC
            """)
    List<DocumentVo> getDocumentList(String loginUserNo);

    // 결재선 목록 조회
    @Select("""
            SELECT AL.DOC_NO AS approvalDocNo
            ,AL.SEQ ,AL.APPROVAL_DATE ,AL.APPROVER_CLASSIFICATION_NO ,PI.NAME AS writerName ,DEPT.PARTNAME AS dept
            ,P.NAME_OF_POSITION AS positionName ,DRL.REFERRER_NO, AL.APPROVAL_STAGE, ASL.APPR_STAGE_NAME
            FROM APPR_LINE AL JOIN APPR_STAGE_LIST ASL ON AL.APPROVAL_STAGE = ASL.APPR_STAGE_NO
            JOIN PERSONNEL_INFORMATION PI ON AL.APPROVER_NO = PI.EMP_NO
            LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = AL.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = AL.POSITION_CODE
            WHERE AL.DOC_NO = #{docNo}" +
            ORDER BY AL.SEQ
            """)
    List<ApprovalLineVo> getApprovalLineList(int docNo);

    // (임시저장) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @Select("""
            SELECT D.DOC_NO ,D.WRITER_NO ,D.STATUS ,D.TITLE ,D.CONTENT ,D.ENROLL_DATE,D.CREDIT_DATE,STATUS ,D.URGENT ,T.TITLE AS templateTitle
            ,TC.NAME AS categoryName ,D.STATUS, DSL.DOC_STATUS_NAME statusName,PI.NAME AS writerName ,DEPT.PARTNAME AS deptName
            ,P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE D.DEL_YN ='N' AND D.STATUS = 1 AND D.WRITER_NO = #{loginUserNo}
            ORDER BY D.CREDIT_DATE DESC
            """)
    List<DocumentVo> getTempDocumentList(String loginUserNo);

    // (종결) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @Select("""
            SELECT D.DOC_NO ,D.WRITER_NO ,D.STATUS ,D.TITLE ,D.CONTENT ,D.ENROLL_DATE,D.CREDIT_DATE,STATUS ,D.URGENT ,T.TITLE AS templateTitle
            ,TC.NAME AS categoryName ,D.STATUS, DSL.DOC_STATUS_NAME statusName,PI.NAME AS writerName ,DEPT.PARTNAME AS deptName
            ,P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE D.DEL_YN ='N' AND D.STATUS = 3 AND D.WRITER_NO = #{loginUserNo}
            ORDER BY D.CREDIT_DATE DESC
            """)
    List<DocumentVo> getCloseDocumentList(String loginUserNo);

    // (반려) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @Select("""
            SELECT D.DOC_NO ,D.WRITER_NO ,D.STATUS ,D.TITLE ,D.CONTENT ,D.ENROLL_DATE,D.CREDIT_DATE,STATUS ,D.URGENT ,T.TITLE AS templateTitle
            ,TC.NAME AS categoryName ,D.STATUS, DSL.DOC_STATUS_NAME statusName,PI.NAME AS writerName ,DEPT.PARTNAME AS deptName
            ,P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE D.DEL_YN ='N' AND D.STATUS = 4 AND D.WRITER_NO = #{loginUserNo}
            ORDER BY D.CREDIT_DATE DESC
            """)
    List<DocumentVo> getRetrunDocumentList(String loginUserNo);

    // (결재취소) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @Select("""
            SELECT D.DOC_NO ,D.WRITER_NO ,D.STATUS ,D.TITLE ,D.CONTENT ,D.ENROLL_DATE,D.CREDIT_DATE,STATUS ,D.URGENT ,T.TITLE AS templateTitle
            ,TC.NAME AS categoryName ,D.STATUS, DSL.DOC_STATUS_NAME statusName,PI.NAME AS writerName ,DEPT.PARTNAME AS deptName
            ,P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE D.DEL_YN ='N' AND D.STATUS = 5 AND D.WRITER_NO = #{loginUserNo}
            ORDER BY D.CREDIT_DATE DESC
            """)
    List<DocumentVo> getCancelDocumentList(String loginUserNo);

    // (삭제함) 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @Select("""
            SELECT D.DOC_NO ,D.WRITER_NO ,D.STATUS ,D.TITLE ,D.CONTENT ,D.ENROLL_DATE,D.CREDIT_DATE,STATUS ,D.URGENT ,T.TITLE AS templateTitle
            ,TC.NAME AS categoryName ,D.STATUS, DSL.DOC_STATUS_NAME statusName,PI.NAME AS writerName ,DEPT.PARTNAME AS deptName
            ,P.NAME_OF_POSITION AS positionName
            FROM DOCUMENT D JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO
            JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO
            JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE D.DEL_YN ='Y' AND D.WRITER_NO = #{loginUserNo}
            ORDER BY D.CREDIT_DATE DESC
            """)
    List<DocumentVo> getDeleteDocumentList(String loginUserNo);


    // 내가 받은 결재
    @Select("""
            SELECT D.DOC_NO, D.WRITER_NO, D.STATUS, D.TITLE, D.CONTENT, D.ENROLL_DATE, D.CREDIT_DATE, D.STATUS,
                  D.URGENT, T.TITLE AS templateTitle, TC.NAME AS categoryName, D.STATUS, DSL.DOC_STATUS_NAME statusName,
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
             AND A.APPROVAL_STAGE = 1
             AND D.STATUS = 2
              AND ( A.SEQ = 1 OR
                   EXISTS (
                       SELECT 1
                           FROM APPR_LINE B
                           WHERE A.DOC_NO = B.DOC_NO
                               AND A.SEQ -1 = B.SEQ
                               AND B.APPROVAL_STAGE = 3
                       )
                )
           ORDER BY D.CREDIT_DATE DESC
           """)
    List<DocumentVo> getSendDocumentList(String loginUserNo);


    // 상세보기
    // 결재 문서 조회(카테고리, 양식, 기안자관련)
    @Select("""
            SELECT D.DOC_NO, D.WRITER_NO, D.STATUS, D.TITLE, D.CONTENT, D.ENROLL_DATE, D.CREDIT_DATE, D.STATUS,
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
            SELECT AL.DOC_NO AS approvalDocNo
            ,AL.SEQ ,AL.APPROVAL_DATE ,AL.APPROVER_CLASSIFICATION_NO ,PI.NAME approverName ,DEPT.PARTNAME deptName
            ,P.NAME_OF_POSITION positionName ,DRL.REFERRER_NO, AL.APPROVAL_STAGE, ASL.APPR_STAGE_NAME apprStageName
            FROM APPR_LINE AL JOIN PERSONNEL_INFORMATION PI ON AL.APPROVER_NO = PI.EMP_NO
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
            SELECT DL.DOC_NO,PI.NAME AS writerName ,DEPT.PARTNAME AS deptName
            ,P.NAME_OF_POSITION AS positionName ,DRL.REFERRER_NO references
            FROM DOC_REFERENCE_LIST DL
            JOIN PERSONNEL_INFORMATION PI ON DL.REFERRER_NO = PI.EMP_NO
            LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO
            LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE
            LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE DL.DOC_NO = #{docNo}
            """)
    List<ReferencerVo> getReferencerByNo(int docNo);

    // 파일 목록 조회
    @Select("SELECT * FROM DOC_FILES WHERE DOC_NO = #{docNo} AND DEL_YN ='N'")
    List<DocFileVo> getDocFileByNo(int docNo);


    // 결재 기안 철회(아무도 결재승인 안했을 경우 가능)
    @Update("""
            UPDATE DOCUMENT
            SET DEL_YN = 'Y'
            WHERE DOC_NO = #{docNo}
            AND WRITER_NO = #{loginUserNo}
            AND DOC_NO NOT
                IN (
                SELECT DOC_NO
                FROM APPR_LINE
                WHERE DOC_NO = #{docNo}
                AND APPROVAL_STAGE != 1
            )
            """)
    int deleteDocumentByNo(int docNo, String loginUserNo);

}
