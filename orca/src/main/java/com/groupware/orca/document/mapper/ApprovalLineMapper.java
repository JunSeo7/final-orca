package com.groupware.orca.document.mapper;

import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.ApproverVo;
import com.groupware.orca.document.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApprovalLineMapper {

    // 결재선 등록 조직도 가져오기
    @Select("""
            SELECT PI.EMP_NO, PI.NAME, PI.POSITION_CODE, P.NAME_OF_POSITION, PI.DEPT_CODE, D.PARTNAME, PI.TEAM_CODE, DT.TEAM_NAME
            FROM PERSONNEL_INFORMATION PI
            JOIN POSITION P ON PI.POSITION_CODE = P.POSITION_CODE
            JOIN DEPARTMENT D ON PI.DEPT_CODE = D.DEPT_CODE
            JOIN DEPARTMENT_TEAM DT ON PI.TEAM_CODE = DT.TEAM_CODE
            WHERE LEAVING_DATE IS NULL
            ORDER BY P.POSITION_CODE, D.DEPT_CODE, DT.TEAM_CODE, PI.NAME
            """)
    List<UserVo> getUsers();

    // 결재선 등록 템플릿 카테고리 가져오기
    @Select("SELECT CATEGORY_NO, NAME AS categoryName FROM DOC_TEMPLATE_CATEGORY WHERE DEL_YN = 'N'")
    List<TemplateVo> getCategory();

    // 결재선 등록 카테고리번호 - 결재양식 제목 가져오기
    @Select("SELECT CATEGORY_NO, TEMPLATE_NO, TITLE FROM DOC_TEMPLATE WHERE DEL_YN = 'N' AND CATEGORY_NO = #{categoryNo}")
    List<TemplateVo> getTemplateByCategoryNo(int categoryNo);

    // 기본 결재선 등록 전 - 기본 결재선 존재 여부 확인 - 0이여야 insert 될 수 있도록 함.
    @Select("SELECT COUNT(*) FROM APPR_LINE_TEMPLATE WHERE TEMPLATE_NO = #{templateNo} AND WRITER_NO IS NULL AND DEL_YN = 'N'")
    int countBasicApprovalLine(int templateNo);

    // 기본 결재선 등록
    @Insert("""
            INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE)
            VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, #{templateNo}, #{apprLineName}, SYSDATE)
            """)
    @Options(useGeneratedKeys = true, keyProperty = "apprLineNo", keyColumn = "APPR_LINE_NO")
    int insertApprovalLine(ApprovalLineVo approvalLine);

    // 결재자 등록
    @Insert("""
            INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO)
            VALUES (SEQ_APPROVER_INFO.NEXTVAL, #{approverNo}, #{apprLineNo}, #{seq}, #{approverClassificationNo})
            """)
    int insertApprover(ApproverVo approver);

    // 결재선 전체목록 (결재선)
    @Select("""
            SELECT AT.APPR_LINE_NO, AT.APPR_LINE_NAME, AT.CREATED_DATE, DT.TEMPLATE_NO, DT.TITLE, DT.CONTENT,
                   DTC.CATEGORY_NO, DTC.NAME AS CATEGORY_NAME
            FROM APPR_LINE_TEMPLATE AT
            JOIN DOC_TEMPLATE DT ON AT.TEMPLATE_NO = DT.TEMPLATE_NO
            JOIN DOC_TEMPLATE_CATEGORY DTC ON DT.CATEGORY_NO = DTC.CATEGORY_NO
            WHERE WRITER_NO IS NULL AND AT.DEL_YN = 'N'
            ORDER BY DTC.CATEGORY_NO, CREATED_DATE DESC
            """)
    List<ApprovalLineVo> getApprovalLineList();

    // 결재선 전체목록 (결재자 여러명)
    @Select("""
            SELECT AI.APPROVER_INFO_NO, AI.APPR_LINE_NO, AI.SEQ, AI.APPROVER_CLASSIFICATION_NO, PI.NAME AS approverName,
                   D.DEPT_CODE, D.PARTNAME AS DEPT_NAME, P.NAME_OF_POSITION AS positionName
            FROM APPROVER_INFO AI
            JOIN PERSONNEL_INFORMATION PI ON AI.APPROVER_NO = PI.EMP_NO
            JOIN DEPARTMENT D ON D.DEPT_CODE = PI.DEPT_CODE
            JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE
            WHERE APPR_LINE_NO = #{apprLineNo}
            ORDER BY AI.APPR_LINE_NO, AI.SEQ
            """)
    List<ApproverVo> getApproverList(int apprLineNo);

    // 기본 결재선 삭제
    @Delete("""
            UPDATE APPR_LINE_TEMPLATE
            SET DEL_YN = 'Y'
            WHERE APPR_LINE_NO = #{apprLineNo} AND WRITER_NO IS NULL
            """)
    int deleteApprLine(int apprLineNo);

    // 결재자, 합의자
    // 결재선 - 승인처리, 반려처리
    @Update("""
            UPDATE APPR_LINE
            SET APPROVAL_STAGE = #{approvalStage}, "COMMENT" = #{comment}, APPROVAL_DATE = SYSDATE
            WHERE DOC_NO = #{docNo} AND APPROVER_NO = #{approverNo}
            """)
    int updateStatusApprLine(ApproverVo vo);


    // 문서 - 승인처리, 반려처리
    // 문서 상태 확인
    @Select("SELECT * FROM APPR_LINE WHERE DOC_NO = #{docNo}")
    List<ApproverVo> selectApprLineByDocNo(int docNo);

    // 처리중....
    // 결재자가 모두 승인했을 경우 결재 문서 승인 (종결처리)
    // 결재자중 한명이라도 반려했을 경우 문서 반려 (종결처리)
    @Update("UPDATE DOCUMENT SET STATUS = #{status} WHERE DOC_NO = #{docNo}")
    int updateDocumentStatus(int docNo, int status);

}