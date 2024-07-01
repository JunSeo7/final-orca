package com.groupware.orca.salary.vo;

import lombok.Data;

@Data
public class SalaryVo {

    private String payrollNo;           //식별자 번호
    private String empNo;               //사원번호
    private int nationalPersion;     //국민연금
    private int heathInsurance;      //건강보험
    private int longCare;            //장기요양보험
    private int employementInsurance;//고용보험
    private int incomeTax;           //소득세
    private int localIncomeTax;      //지방소득세
    private int position;            //직급수당
    private int bonus;               //보너스
    private int holiday;             //휴일근로수당
    private int overTimeWork;        //연장근로수당
    private int meals;               //식대
    private int totalSalary;           //최종급여
    private String paymentDate;         //지급날짜



}
