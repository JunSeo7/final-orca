package com.groupware.orca.document.vo;

import com.groupware.orca.user.vo.UserVo;
import lombok.Data;

import java.util.List;

@Data
public class ApprovalLineVo {

    // Category Information (카테고리 정보)
    private int categoryNo;                    // 카테고리 번호
    private String categoryName;               // 카테고리 이름

    // Template Information (템플릿 정보)
    private int templateNo;                    // 템플릿 번호
    private String title;                      // 템플릿 제목

    private int apprLineNo;         // 결재선 번호
    private int writerNo;             // 작성자 번호
    private String apprLineName;    // 결재선 이름
    private String createdDate;     // 생성 날짜
    private List<ApproverVo> approverVoList; // 결재자 목록

}