package com.groupware.orca.document.mapper;

import com.groupware.orca.document.vo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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


    // 결재 작성
    @Insert("INSERT INTO DOCUMENT (DOC_NO, WRITER_NO, TEMPLATE_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) " +
            "VALUES (SEQ_DOCUMENT.NEXTVAL, #{userNo}, #{templateNo}, #{status}, #{title}, #{content}, SYSDATE, " +
            "CASE WHEN #{status} = 2 THEN SYSDATE ELSE NULL END)")
    int writeDocument(DocumentVo vo);


    // 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    @Select("SELECT D.DOC_NO ,D.WRITER_NO ,D.STATUS ,D.TITLE ,D.CONTENT ,D.ENROLL_DATE,D.CREDIT_DATE,STATUS\n" +
            "    ,D.URGENT ,T.TITLE AS templateTitle ,TC.NAME AS categoryName ,D.STATUS, DSL.DOC_STATUS_NAME statusName,PI.NAME AS writerName ,DEPT.PARTNAME AS dept \n" +
            "   ,P.NAME_OF_POSITION AS position ,DRL.REFERRER_NO \n" +
            "    FROM DOCUMENT D JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO\n" +
            "    JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO \n" +
            "    JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO \n" +
            "    JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE \n" +
            "    LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE \n" +
            "    WHERE D.DEL_YN ='N'\n" +
            "    ORDER BY CREDIT_DATE")
    List<DocumentVo> getDocumentList(String loginUserNo);

    // 결재선 목록 조회
    @Select("SELECT AL.DOC_NO AS approvalDocNo \n" +
            "    ,AL.SEQ ,AL.APPROVAL_DATE ,AL.APPROVER_CLASSIFICATION_NO ,PI.NAME AS writerName ,DEPT.PARTNAME AS dept \n" +
            "    ,P.NAME_OF_POSITION AS position ,DRL.REFERRER_NO, AL.APPROVAL_STAGE, ASL.APPR_STAGE_NAME\n" +
            "    FROM APPR_LINE AL JOIN APPR_STAGE_LIST ASL ON AL.APPROVAL_STAGE = ASL.APPR_STAGE_NO\n" +
            "    JOIN PERSONNEL_INFORMATION PI ON AL.APPROVER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = AL.DEPT_CODE \n" +
            "    LEFT JOIN POSITION P ON P.POSITION_CODE = AL.POSITION_CODE \n" +
            "    WHERE AL.DOC_NO = #{docNo}")
    List<ApprovalLineVo> getApprovalLineList(int docNo);

    // 참조인 목록 조회
    @Select("SELECT DL.DOC_NO,PI.NAME AS writerName ,DEPT.PARTNAME AS dept \n" +
            "   ,P.NAME_OF_POSITION AS position ,DRL.REFERRER_NO references\n" +
            "    FROM DOC_REFERENCE_LIST DL\n" +
            "    JOIN PERSONNEL_INFORMATION PI ON DL.REFERRER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE \n" +
            "    LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE \n" +
            "   WHERE DL.DOC_NO = #{docNo}")
    List<ReferencerVo> getReferencerList(int docNo);


    // 결재 문서 조회(카테고리, 양식, 기안자관련) - 기안자 no 추가 (params)
    @Select("SELECT D.DOC_NO ,D.WRITER_NO ,D.STATUS ,D.TITLE ,D.CONTENT ,D.ENROLL_DATE,D.CREDIT_DATE,STATUS\n" +
            "    ,D.URGENT ,T.TITLE AS templateTitle ,TC.NAME AS categoryName ,D.STATUS, DSL.DOC_STATUS_NAME statusName,PI.NAME AS writerName ,DEPT.PARTNAME AS dept \n" +
            "   ,P.NAME_OF_POSITION AS position ,DRL.REFERRER_NO \n" +
            "    FROM DOCUMENT D JOIN DOC_STATUS_LIST DSL ON D.STATUS = DSL.DOC_STATUS_NO\n" +
            "    JOIN DOC_TEMPLATE T ON D.TEMPLATE_NO = T.TEMPLATE_NO \n" +
            "    JOIN DOC_TEMPLATE_CATEGORY TC ON T.CATEGORY_NO = TC.CATEGORY_NO \n" +
            "    JOIN PERSONNEL_INFORMATION PI ON D.WRITER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE \n" +
            "    LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE \n" +
            "    WHERE D.DEL_YN ='N' AND D.DOC_NO = #{docNo}\n" +
            "    ORDER BY CREDIT_DATE")
    DocumentVo getDocumentByNo(int docNo);

    // 결재선 목록 조회
    @Select("SELECT AL.DOC_NO AS approvalDocNo \n" +
            "    ,AL.SEQ ,AL.APPROVAL_DATE ,AL.APPROVER_CLASSIFICATION_NO ,PI.NAME AS writerName ,DEPT.PARTNAME AS dept \n" +
            "    ,P.NAME_OF_POSITION AS position ,DRL.REFERRER_NO, AL.APPROVAL_STAGE, ASL.APPR_STAGE_NAME\n" +
            "    FROM APPR_LINE AL JOIN APPR_STAGE_LIST ASL ON AL.APPROVAL_STAGE = ASL.APPR_STAGE_NO\n" +
            "    JOIN PERSONNEL_INFORMATION PI ON AL.APPROVER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = AL.DEPT_CODE \n" +
            "    LEFT JOIN POSITION P ON P.POSITION_CODE = AL.POSITION_CODE \n" +
            "    WHERE AL.DOC_NO = #{docNo}")
    List<ApprovalLineVo> getApprovalLineByNo(int docNo);

    // 참조인 목록 조회
    @Select("SELECT DL.DOC_NO,PI.NAME AS writerName ,DEPT.PARTNAME AS dept \n" +
            "   ,P.NAME_OF_POSITION AS position ,DRL.REFERRER_NO references\n" +
            "    FROM DOC_REFERENCE_LIST DL\n" +
            "    JOIN PERSONNEL_INFORMATION PI ON DL.REFERRER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DOC_REFERENCE_LIST DRL ON DRL.REFERRER_NO = PI.EMP_NO \n" +
            "    LEFT JOIN DEPARTMENT DEPT ON DEPT.DEPT_CODE = PI.DEPT_CODE \n" +
            "    LEFT JOIN POSITION P ON P.POSITION_CODE = PI.POSITION_CODE \n" +
            "   WHERE DL.DOC_NO = #{docNo}")
    List<ReferencerVo> getReferencerByNo(int docNo);
    // 파일 목록 조회
    @Select("SELECT * FROM DOC_FILES WHERE DOC_NO = #{docNo} AND DEL_YN ='N'")
    List<DocFileVo> getDocFileByNo(int docNo);

    // 결재 기안 철회(아무도 결재승인 안했을 경우 가능)
    @Update("UPDATE DOCUMENT SET DEL_YN ='Y' WHERE DOC_NO = #{docNo} AND WRITER_NO=#{loginUserNo}")
    int deleteDocumentByNo(int docNo, String  loginUserNo);
}
