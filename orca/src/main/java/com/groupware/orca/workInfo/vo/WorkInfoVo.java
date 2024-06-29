package com.groupware.orca.workInfo.vo;

import lombok.Data;

@Data
public class WorkInfoVo {

    private String workNo;
    private String empNo;
    private String workDate;
    private String startTime;
    private String endTime;
    private double overtimeWork;
    private double holidayWork;

}
