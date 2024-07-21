package com.groupware.orca.docTemplate.vo;

import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import lombok.Data;

import java.util.List;

@Data
public class TemplateVo {

    // 카테고리 정보
    private int categoryNo;                    // 카테고리 번호
    private String categoryName;               // 카테고리 이름

    // 템플릿 정보
    private int templateNo;                    // 템플릿 번호
    private String title;                      // 템플릿 제목
    private String content;                    // 템플릿 내용
    private String enrollDate;                 // 템플릿 생성 날짜

    // 결재자 관련
    private int apprLineNo;                     // 결재선 번호
    private String apprLineName;                // 결재선 이름
    private List<ApprovalLineVo> ApprLineList; // 결재자 목록
}
