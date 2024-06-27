package com.groupware.orca.document.mapper;

import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.TemplateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApprovalLineMapper {
//    @Select("SELECT AT.APPR_LINE_NO ,AT.APPR_LINE_NAME ,AI.APPROVER_INFO_NO ,AI.SEQ ,AI.APPROVER_CLASSIFICATION_NO , D.PARTNAME , P.NAME_OF_POSITION , PI.NAME FROM APPR_LINE_TEMPLATE AT JOIN APPROVER_INFO AI ON AI.APPR_LINE_NO=AT.APPR_LINE_NO JOIN PERSONNEL_INFORMATION PI ON AI.APPROVER_NO = PI.EMP_NO JOIN DEPARTMENT D ON D.DEPT_CODE = PI.DEPT_CODE JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE ORDER BY AT.CREATED_DATE, AI.SEQ")
//    List<ApprovalLineVo> list();

    @Select("SELECT AT.APPR_LINE_NO, AT.APPR_LINE_NAME, AT.CREATED_DATE, DT.TEMPLATE_NO, DT.TITLE AS TEMPLATE_TITLE, DT.CONTENT, DT.ENROLL_DATE, DTC.CATEGORY_NO, DTC.NAME AS CATEGORY_NAME FROM APPR_LINE_TEMPLATE AT LEFT JOIN DOC_TEMPLATE DT ON AT.APPR_LINE_NO = DT.APPR_LINE_NO LEFT JOIN DOC_TEMPLATE_CATEGORY DTC ON DT.CATEGORY_NO = DTC.CATEGORY_NO")
    List<TemplateVo> getTemplateList();

    @Select("SELECT AI.APPROVER_INFO_NO, AI.APPR_LINE_NO, AI.SEQ, AI.APPROVER_CLASSIFICATION_NO, PI.EMP_NO AS APPROVER_NO, PI.NAME AS APPROVER_NAME, D.DEPT_CODE, D.PARTNAME AS DEPT_NAME, P.POSITION_CODE, P.NAME_OF_POSITION AS POSITION_NAME FROM APPROVER_INFO AI JOIN PERSONNEL_INFORMATION PI ON AI.APPROVER_NO = PI.EMP_NO JOIN DEPARTMENT D ON D.DEPT_CODE = PI.DEPT_CODE JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE ORDER BY AI.APPR_LINE_NO, AI.SEQ")
    List<ApprovalLineVo> getApprovalLineList();
}