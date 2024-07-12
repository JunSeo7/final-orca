package com.groupware.orca.board.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BoardPenaltyVo {
    private int penaltyNo;
    private int penaltyCategoryNo;
    private String penaltyContent;
    private String isPenalty;
    private int boardNo;
    private int empNo;
    private Date penaltyTime;



}
