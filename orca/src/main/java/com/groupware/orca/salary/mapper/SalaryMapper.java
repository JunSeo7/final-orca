package com.groupware.orca.salary.mapper;

import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalaryMapper {

    //급여계산 입력 쿼리문
    @Insert("INSERT INTO PAYROLL(PAYROLL_NO ,EMP_NO ,NATIONAL_PENSION ,HEALTH_INSURANCE ,LONG_CARE ,EMPLOYMENT_INSURANCE ,INCOME_TAX ,LOCAL_INCOME_TAX ,POSITION ,BONUS ,HOLIDAY ,OVERTIMEWORK ,MEALS ,PAYMENT_DATE) \n" +
            "VALUES(SEQ_PAYROLL_NO.NEXTVAL,#{empNo},?,?,?,?,?,?,?,?,?,?,?,?)")
    int salarywrite(SalaryVo vo);

    //4대보험 입력 쿼리문
    @Insert("INSERT INTO RATES(BASE_YEAR,PENSION_PERCENTAGE,HEALTH_INSURANCE_PERCENTAGE,LONG_CARE,EMPLOYMENT_INSURANCE_PERCENTAGE) \n" +
            "VALUES(?,?,?,?,?)")
    int ratesWrite(RatesVo vo);
}
