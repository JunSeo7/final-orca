package com.groupware.orca.board.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVo {
    private String boardNo;
    private String title;
    private String content;
    private int hit;
    private Date enrollDate;
    private Date modifyDate;
    private String delYn;
    private Long categoryNo;
    private Long insertUserNo;
    private int otp;
    private String secretKey;
    private String latitude; // 필드명 수정
    private String longitude; // 필드명 수정
}
