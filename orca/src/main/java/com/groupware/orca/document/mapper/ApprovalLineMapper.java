package com.groupware.orca.document.mapper;

import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.ApproverVo;
import com.groupware.orca.document.vo.TemplateVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApprovalLineMapper {

    // 기본 결재선 등록
    @Insert("INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, WRITER_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, #{userNo}, #{apprLineName}, SYSDATE)")
    @Options(useGeneratedKeys = true, keyProperty = "apprLineNo", keyColumn = "APPR_LINE_NO")
    void addApprLineTemplate(ApprovalLineVo approvalLineVo);
    // 결재자 등록
    @Insert("INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, #{approverNo}, #{apprLineNo}, #{seq}, #{approverClassificationNo})")
    void addApproverInfo(ApproverVo approverVo);

    //나만의 결재선 등록- userNo입력
//    @Insert("INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, WRITER_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, #{writerNo}, #{apprLineName}, SYSDATE)")
//    void insertApprLineTemplate(ApprovalLineVo approvalLineVo);
//
//    @Insert("INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, #{approverNo}, #{apprLineNo}, #{seq}, #{approverClassificationNo})")
//    void insertApproverInfo(ApprovalLineVo approvalLineVo);

//    @Select("SELECT AT.APPR_LINE_NO ,AT.APPR_LINE_NAME ,AI.APPROVER_INFO_NO ,AI.SEQ ,AI.APPROVER_CLASSIFICATION_NO , D.PARTNAME , P.NAME_OF_POSITION , PI.NAME FROM APPR_LINE_TEMPLATE AT JOIN APPROVER_INFO AI ON AI.APPR_LINE_NO=AT.APPR_LINE_NO JOIN PERSONNEL_INFORMATION PI ON AI.APPROVER_NO = PI.EMP_NO JOIN DEPARTMENT D ON D.DEPT_CODE = PI.DEPT_CODE JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE ORDER BY AT.CREATED_DATE, AI.SEQ")
//    List<ApprovalLineVo> list();

    // 결재선 전체목록 (양식)
    @Select("SELECT AT.APPR_LINE_NO, AT.APPR_LINE_NAME, AT.CREATED_DATE, DT.TEMPLATE_NO, DT.TITLE , DT.CONTENT, DT.ENROLL_DATE, DTC.CATEGORY_NO, DTC.NAME AS CATEGORY_NAME FROM APPR_LINE_TEMPLATE AT LEFT JOIN DOC_TEMPLATE DT ON AT.APPR_LINE_NO = DT.APPR_LINE_NO LEFT JOIN DOC_TEMPLATE_CATEGORY DTC ON DT.CATEGORY_NO = DTC.CATEGORY_NO")
    List<TemplateVo> getTemplateList();
    // 결재선 전체목록 (양식-결재라인)
    @Select("SELECT AI.APPROVER_INFO_NO, AI.APPR_LINE_NO, AI.SEQ, AI.APPROVER_CLASSIFICATION_NO, PI.NAME approverName, D.DEPT_CODE, D.PARTNAME AS DEPT_NAME, P.NAME_OF_POSITION positionName FROM APPROVER_INFO AI JOIN PERSONNEL_INFORMATION PI ON AI.APPROVER_NO = PI.EMP_NO JOIN DEPARTMENT D ON D.DEPT_CODE = PI.DEPT_CODE JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE ORDER BY AI.APPR_LINE_NO, AI.SEQ")
    List<ApprovalLineVo> getApprovalLineList();

    // 결재선 삭제
    @Delete("UPDATE SET APPROVER_INFO WHERE APPR_LINE_NO = #{apprLineNo}")
    void deleteApprLine(int apprLineNo);

}


