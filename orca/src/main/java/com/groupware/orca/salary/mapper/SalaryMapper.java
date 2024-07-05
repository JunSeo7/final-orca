package com.groupware.orca.salary.mapper;

import com.groupware.orca.salary.vo.PayrollVo;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SalaryMapper {

    //급여계산 입력 쿼리문
    @Insert("INSERT INTO PAYROLL(\n" +
            "    PAYROLL_NO \n" +
            "    ,EMP_NO \n" +
            "    ,NATIONAL_PENSION \n" +
            "    ,HEALTH_INSURANCE \n" +
            "    ,LONG_CARE \n" +
            "    ,EMPLOYMENT_INSURANCE \n" +
            "    ,INCOME_TAX\n" +
            "    ,LOCAL_INCOME_TAX\n" +
            "    ,POSITION \n" +
            "    ,BONUS \n" +
            "    ,HOLIDAY \n" +
            "    ,OVERTIMEWORK \n" +
            "    ,MEALS \n" +
            "    ,TOTAL_SALARY \n" +
            "    ,PAYMENT_DATE\n" +
            ") \n" +
            "VALUES(\n" +
            "     SEQ_PAYROLL_NO.NEXTVAL \n" +
            "    ,#{vo.empNo} \n" +
            "    ,#{svo.nationalPension} \n" +
            "    ,#{svo.healthInsurance} \n" +
            "    ,#{svo.longCare} \n" +
            "    ,#{svo.employmentInsurance} \n" +
            "    ,#{svo.incomeTax}\n" +
            "    ,#{svo.localIncomeTax}\n" +
            "    ,#{svo.position} \n" +
            "    ,#{svo.bonus}\n" +
            "    ,#{svo.holiday}\n" +
            "    ,#{svo.overTimeWork} \n" +
            "    ,#{svo.meals} \n" +
            "    ,#{svo.totalSalary} \n" +
            "    ,SYSDATE\n" +
            ")")
    int salaryWrite(@Param("vo") UserVo vo, @Param("rvo") RatesVo rvo, double holidayTime, double overTime, double person, double position);

    //급여 4대보험 최종 값 가져오기
    @Select("SELECT \n" +
            "    P.EMP_NO\n" +
            "    ,S.NATIONAL_PENSION\n" +
            "    ,S.HEALTH_INSURANCE\n" +
            "    ,S.LONG_CARE\n" +
            "    ,S.EMPLOYMENT_INSURANCE\n" +
            "    ,S.INCOME_TAX\n" +
            "    ,S.LOCAL_INCOME_TAX\n" +
            "    ,S.POSITION \n" +
            "    ,S.BONUS \n" +
            "    ,S.HOLIDAY \n" +
            "    ,S.OVERTIMEWORK \n" +
            "    ,S.MEALS \n" +
            "    ,S.TOTAL_SALARY \n" +
            "    ,S.PAYMENT_DATE\n" +
            "FROM PAYROLL S\n" +
            "JOIN PERSONNEL_INFORMATION P ON P.EMP_NO = S.EMP_NO")
    SalaryVo getSalaryVo(String empNo);

    // 4대보험 요율 가져오기
    @Select("SELECT \n" +
            "    BASE_YEAR\n" +
            "    ,PENSION_PERCENTAGE\n" +
            "    ,HEALTH_INSURANCE_PERCENTAGE\n" +
            "    ,LONG_CARE_PERCENTAGE\n" +
            "    ,EMPLOYMENT_INSURANCE_PERCENTAGE\n" +
            "    ,LOCAL_INCOME_TAX_PERSENTAGE\n" +
            "FROM RATES")
    RatesVo getRatesVo();

    @Select("SELECT\n" +
            "    EMP_NO\n" +
            "    ,SALARY\n" +
            "FROM PERSONNEL_INFORMATION\n" +
            "WHERE EMP_NO = #{empNo}")
    UserVo getUserVo(String empNo);

    //-----------------------------------------------------------------------------------------------

    //4대보험 입력 쿼리문
    @Insert("INSERT INTO RATES(BASE_YEAR,PENSION_PERCENTAGE,HEALTH_INSURANCE_PERCENTAGE,LONG_CARE_PERCENTAGE,EMPLOYMENT_INSURANCE_PERCENTAGE,LOCAL_INCOME_TAX_PERSENTAGE) \n" +
            "VALUES(SYSDATE,#{pensionPercentage},#{healthPercentage},#{longCarePercentage},#{employmentPercentage},#{localIncomeTaxPercentage})")
    int ratesWrite(RatesVo vo);

    @Select("SELECT * FROM SALARY")
    List<SalaryVo> getSalaryList();


    //TODO swy 삭제

    @Select("SELECT * FROM RATES")
    RatesVo getRates();

    @Select("SELECT * FROM PERSONNEL_INFORMATION WHERE EMP_NO = #{empNo}")
    UserVo getPersonalInformation(String empNo);

    @Insert("""
            INSERT INTO PAYROLL
            (
                PAYROLL_NO
                ,EMP_NO
                ,NATIONAL_PENSION
                ,HEALTH_INSURANCE
                ,LONG_CARE
                ,EMPLOYMENT_INSURANCE
                ,INCOME_TAX
                ,LOCAL_INCOME_TAX
                ,POSITION
                ,BONUS
                ,HOLIDAY
                ,OVERTIMEWORK
                ,MEALS
                ,TOTAL_SALARY
                ,PAYMENT_DATE
            )
            VALUES
            (
               SEQ_PAYROLL_NO.NEXTVAL
              ,#{empNo}
              ,#{nationalPension}
              ,#{healthInsurance}
              ,#{longCare}
              ,#{employmentInsurance}
              ,#{incomeTax}
              ,#{localIncomeTax}
              ,#{position}
              ,#{bonus}
              ,#{holiday}
              ,#{overTimeWork}
              ,#{meals}
              ,#{totalSalary}
              ,SYSDATE
            )
            """)
    int insertPayroll(PayrollVo vo);
}
