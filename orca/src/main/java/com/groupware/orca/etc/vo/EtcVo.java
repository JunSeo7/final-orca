package com.groupware.orca.etc.vo;

import lombok.Data;

@Data
public class EtcVo {

    private String etcNo;
    private String etcCode;
    private int empNo;
    private int docNo;
    private String regDate;
    private String startDate;
    private String expiryDate;
    private String reason;
    private String status;
}
