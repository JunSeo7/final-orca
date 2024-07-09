package com.groupware.orca.board.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVo {
    private Integer boardNo;
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
    private String isAnonymous = "N";  // 기본값을 "N"으로 설정
}
