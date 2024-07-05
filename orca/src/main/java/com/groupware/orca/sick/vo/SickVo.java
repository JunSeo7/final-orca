package com.groupware.orca.sick.vo;

import lombok.Data;

@Data
public class SickVo {

    private String sickNo;
    private String empNo;
    private int docNo;
    private String regDate;
    private String startDate;
    private String expiryDate;
    private String reason;
    private String status;
}
