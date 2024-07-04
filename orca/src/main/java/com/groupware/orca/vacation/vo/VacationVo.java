package com.groupware.orca.vacation.vo;

import lombok.Data;

@Data
public class VacationVo {

    private String vacationNo;
    private String vacationCode;
    private String empNo;
    private int docNo;
    private String regDate;
    private String startDate;
    private String expiryDate;
    private String reason;
    private String status;

}
