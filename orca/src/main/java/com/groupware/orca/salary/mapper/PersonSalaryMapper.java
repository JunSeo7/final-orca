package com.groupware.orca.salary.mapper;

import com.groupware.orca.salary.vo.SalaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;
@Mapper
public interface PersonSalaryMapper {



    @Select("""
            SELECT
                S.PAYROLL_NO
                  ,P.EMP_NO
                  ,P.NAME
                  ,FLOOR(ROUND(NATIONAL_PENSION, 1)) AS NATIONAL_PENSION
                  ,FLOOR(ROUND(HEALTH_INSURANCE, 1)) AS HEALTH_INSURANCE
                  ,FLOOR(ROUND(LONG_CARE, 1)) AS LONG_CARE
                  ,FLOOR(ROUND(EMPLOYMENT_INSURANCE, 1)) AS EMPLOYMENT_INSURANCE
                  ,FLOOR(ROUND(INCOME_TAX, 1)) AS INCOME_TAX
                  ,FLOOR(ROUND(LOCAL_INCOME_TAX, 1)) AS LOCAL_INCOME_TAX
                  ,S.POSITION
                  ,FLOOR(ROUND(BONUS, 1)) AS BONUS
                  ,FLOOR(ROUND(HOLIDAY, 1)) AS HOLIDAY
                  ,FLOOR(ROUND(OVERTIMEWORK, 1)) AS OVERTIMEWORK
                  ,FLOOR(ROUND(MEALS, 1)) AS MEALS
                  ,FLOOR(ROUND(TOTAL_SALARY, 1)) AS TOTAL_SALARY
                  ,S.PAYMENT_DATE
              FROM PAYROLL S
              JOIN PERSONNEL_INFORMATION P ON P.EMP_NO = S.EMP_NO
              WHERE P.EMP_NO = #{empNo}
                AND S.PAYROLL_NO = #{payrollNo}
            
            """)
    SalaryVo getPersonSalary(@Param("payrollNo") String payrollNo,@Param("empNo") String empNo);

    @Select("""
            SELECT\s
                   S.PAYROLL_NO
                  ,P.EMP_NO
                  ,P.NAME
                  ,FLOOR(ROUND(NATIONAL_PENSION, 1)) AS NATIONAL_PENSION
                  ,FLOOR(ROUND(HEALTH_INSURANCE, 1)) AS HEALTH_INSURANCE
                  ,FLOOR(ROUND(LONG_CARE, 1)) AS LONG_CARE
                  ,FLOOR(ROUND(EMPLOYMENT_INSURANCE, 1)) AS EMPLOYMENT_INSURANCE
                  ,FLOOR(ROUND(INCOME_TAX, 1)) AS INCOME_TAX
                  ,FLOOR(ROUND(LOCAL_INCOME_TAX, 1)) AS LOCAL_INCOME_TAX
                  ,S.POSITION
                  ,FLOOR(ROUND(BONUS, 1)) AS BONUS
                  ,FLOOR(ROUND(HOLIDAY, 1)) AS HOLIDAY
                  ,FLOOR(ROUND(OVERTIMEWORK, 1)) AS OVERTIMEWORK
                  ,FLOOR(ROUND(MEALS, 1)) AS MEALS
                  ,FLOOR(ROUND(TOTAL_SALARY, 1)) AS TOTAL_SALARY
                  ,S.PAYMENT_DATE
              FROM PAYROLL S
              JOIN PERSONNEL_INFORMATION P ON P.EMP_NO = S.EMP_NO
              WHERE P.EMP_NO = #{empNo}
            """)
    List<SalaryVo> getPersonSalaryList(@Param("empNo") String empNo);
}
