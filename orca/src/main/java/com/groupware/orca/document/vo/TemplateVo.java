package com.groupware.orca.document.vo;

import lombok.Data;

@Data
public class TemplateVo {

    // Category Information (카테고리 정보)
    private int categoryNo;         // 카테고리 번호
    private String categoryName;       // 카테고리 이름

    // Template Information (템플릿 정보)
    private int templateNo;         // 템플릿 번호
    private String title;              // 템플릿 제목
    private String content;            // 템플릿 내용
    private String enrollDate;         // 템플릿 생성 날짜

    private String writerNo;           // 기안자 번호
    private String writerName;         // 기안자 이름

    // 결재자 관련 APPR_LINE_TEMPLATE
    private int approverNo;           // 결재자 번호
    private String empName;              // 결재자 이름
    private int deptCode;             // 부서 코드
    private String partName;             // 부서 이름
    private int teamCode;             // 팀 코드
    private String teamName;             // 팀 이름
    private int positionCode;         // 직위 코드
    private String nameOfPosition;       // 직위 이름
}
