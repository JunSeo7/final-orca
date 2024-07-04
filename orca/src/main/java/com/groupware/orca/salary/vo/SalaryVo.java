package com.groupware.orca.salary.vo;

import lombok.Data;

@Data
public class SalaryVo {

    private String payrollNo;           //식별자 번호
    private String empNo;               //사원번호
    private double nationalPension;     //국민연금
    private double healthInsurance;      //건강보험
    private double longCare;            //장기요양보험
    private double employmentInsurance;//고용보험
    private double incomeTax;           //소득세
    private double localIncomeTax;      //지방소득세
    private double position;            //직급수당
    private double bonus;               //보너스
    private double holiday;             //휴일근로수당
    private double overTimeWork;        //연장근로수당
    private double meals;               //식대
    private double totalSalary;           //최종급여
    private String paymentDate;         //지급날짜



}
