package com.groupware.orca.salary.vo;

import lombok.Data;

@Data
public class ClientVo {
    private String payrollNo;
    private int empNo;
    private double holidayTime;
    private double overTime;
    private int person;
    private double position;
    private double bonus;
    private double meals;
}
