package com.groupware.orca.document.vo;

import com.groupware.orca.user.vo.UserVo;
import lombok.Data;

import java.util.List;

@Data
public class ApprovalLineVo {

    private int apprLineNo;         // 결재선 번호
    private int userNo;             // 작성자 번호
    private String apprLineName;    // 결재선 이름
    private String createdDate;     // 생성 날짜
    private List<ApproverVo> approverVoList; // 결재자 목록

}