package com.groupware.orca.document.vo;

import lombok.Data;

import java.util.List;

@Data
public class TemplateVo {

    // Category Information (카테고리 정보)
    private int categoryNo;                    // 카테고리 번호
    private String categoryName;               // 카테고리 이름

    // Template Information (템플릿 정보)
    private int templateNo;                    // 템플릿 번호
    private String title;                      // 템플릿 제목
    private String content;                    // 템플릿 내용
    private String enrollDate;                 // 템플릿 생성 날짜

    // 결재자 관련 APPR_LINE_TEMPLATE
    private int apprLineNo;
    private List<ApprovalLineVo> apprLineList;  // 결재자 목록(결재선)
}
