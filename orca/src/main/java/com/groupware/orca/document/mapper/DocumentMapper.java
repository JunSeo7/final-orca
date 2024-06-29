package com.groupware.orca.document.mapper;

import com.groupware.orca.document.vo.DocumentVo;
import com.groupware.orca.document.vo.TemplateVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DocumentMapper {

    // 결재 양식 카테고리와 이름 보여주기
    @Select("SELECT DT.TEMPLATE_NO, DT.TITLE, DT.CATEGORY_NO, DTC.NAME " +
            "FROM DOC_TEMPLATE DT " +
            "JOIN DOC_TEMPLATE_CATEGORY DTC ON DT.CATEGORY_NO = DTC.CATEGORY_NO")
    List<TemplateVo> getTemplateList();

    // 템플릿 내용 가져오기
    @Select("SELECT TITLE, CONTENT FROM DOC_TEMPLATE WHERE TEMPLATE_NO = #{templateNo}")
    TemplateVo getTemplateContent(@Param("templateNo") int templateNo);

    // 결재 작성
    @Insert("INSERT INTO DOCUMENT (DOC_NO, WRITER_NO, TEMPLATE_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) " +
            "VALUES (SEQ_DOCUMENT.NEXTVAL, #{userNo}, #{templateNo}, #{status}, #{title}, #{content}, SYSDATE, " +
            "CASE WHEN #{status} = 2 THEN SYSDATE ELSE NULL END)")
    int writeDocument(DocumentVo vo);

    @Select("SELECT D.DOC_NO ,D.WRITER_NO ,D.STATUS ,D.TITLE ,D.CONTENT ,D.ENROLL_DATE ,D.CREDIT_DATE " +
            ",D.URGENT ,T.TITLE AS templateTitle ,TC.NAME AS categoryName ,AL.DOC_NO AS approvalDocNo " +
            ",AL.SEQ ,AL.APPROVAL_DATE ,AL.APPROVER_CLASSIFICATION_NO ,PI.NAME AS writerName ,DEPT.PARTNAME AS dept " +
            ",P.NAME_OF_POSITION AS position ,DRL.REFERRER_NO FROM DOCUMENT D JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO " +
            "JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO LEFT JOIN APPR_LINE AL ON D.DOC_NO = AL.DOC_NO " +
            "LEFT JOIN PERSONNEL_INFORMATION PI ON AL.APPROVER_NO = PI.EMP_NO LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO " +
            "LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = AL.DEPT_CODE LEFT JOIN POSITION P ON P.POSITION_CODE = AL.POSITION_CODE " +
            "WHERE D.DEL_YN = 'N' ORDER BY D.ENROLL_DATE DESC")
    List<DocumentVo> getDocumentList();


}
