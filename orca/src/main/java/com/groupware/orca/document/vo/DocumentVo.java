package com.groupware.orca.document.vo;

import lombok.Data;

@Data
public class DocumentVo {
    // Category Information (카테고리 정보)
    private String categoryNo;         // 카테고리 번호
    private String categoryName;       // 카테고리 이름

    // Template Information (템플릿 정보)
    private String templateNo;         // 템플릿 번호
    private String templateTitle;      // 템플릿 제목
    private String templateContent;    // 템플릿 내용

    // Document Information (문서 정보)
    private String docNo;              // 문서 번호
    private String title;              // 문서 제목
    private String content;            // 문서 내용
    private String fileNo;             // 문서 파일 번호
    private String fileName;           // 문서 파일 이름
    private String enrollDate;         // 등록 날짜
    private String creditDate;         // 생성 날짜
    private String status;             // 문서 상태
    private String urgent;             // 긴급 여부

    // Writer Information (작성자 정보)
    private String writerNo;           // 작성자 번호
    private String writerName;         // 작성자 이름

    // Approval Information (결재 정보)
    private String seq;                //  결재 순서
    private String approverNo;         //   결재자 번호
    private String approvalStage;      //   결재 단계
    private String approvalDate;       //   결재 날짜
    private String comment;            //   결재 의견
    private String approverClassificationNo; // 결재자 분류 번호

    // Department and Position Information 부서 및 직위 정보
    private String dept;           //   부서 코드
    private String position;       //   직위 코드

    // Reference Information (참조 정보)
    private String referrerNo;         //   참조자 번호
    private String apprLineName;       //   결재선 이름
}
