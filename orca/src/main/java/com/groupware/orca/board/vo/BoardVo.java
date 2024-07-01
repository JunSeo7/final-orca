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
    private Integer insertUserNo;
    private String latitude;
    private String longitude;
    private String uploadImageName;
    private String employeeName;
    private String departmentName;
    private String teamName;
    private char isAnonymous;  // 익명 여부 추가
}
