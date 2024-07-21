package com.groupware.orca.approvalLine.vo;

import lombok.Data;

import java.util.List;

@Data
public class ApprovalLineVo {
    // 카테고리 정보
    private int categoryNo;                    // 카테고리 번호
    private String categoryName;               // 카테고리 이름

    // 템플릿 정보
    private int templateNo;                    // 템플릿 번호
    private String title;                      // 템플릿 제목

    // 결재선 정보
    private int docNo;
    private int apprLineNo;                   // 결재선 번호
    private int writerNo;                     // 작성자 번호
    private String apprLineName;              // 결재선 이름
    private String createdDate;              // 생성 날짜
    private List<ApproverVo> approverVoList; // 결재자 목록
}