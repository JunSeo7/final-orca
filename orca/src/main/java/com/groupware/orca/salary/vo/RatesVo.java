package com.groupware.orca.salary.vo;

import lombok.Data;

@Data
public class RatesVo {

    private String ratesNo;
    private String baseYear;            //기준년도 (4대보험 요율 해당 연도)
    private double pensionPercentage;      //국민연금
    private double healthInsurancePercentage; //HEALTH_INSURANCE_PERCENTAGE;       //건강보험
    private double longCarePercentage;               //장기요양보험
    private double employmentInsurancePercentage; //EMPLOYMENT_INSURANCE_PERCENTAGE;   //고용보험 (실업급여)
    private double localIncomeTaxPersentage; //지방 소득세 /LOCAL_INCOME_TAX_PERSENTAGE
}

