package com.groupware.orca.board.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVo {
    private int boardNo;
    private String title;
    private String content;
    private int hit;
    private Date enrollDate;
    private Date modifyDate;
    private String delYn;
    private int categoryNo;
    private int insertUserNo;
    private int otp;
    private String secretKey;
    private String latitude;
    private String longitude;
    private String uploadImageName; // 이미지 파일 이름 필드 추가
    private String employeeName; // 작성자 이름 필드 추가
}
