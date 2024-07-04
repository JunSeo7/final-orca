//package com.groupware.orca.salary.mapper;
//
//import com.groupware.orca.salary.vo.RatesVo;
//import com.groupware.orca.salary.vo.SalaryVo;
//import com.groupware.orca.user.vo.UserVo;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//
//@Mapper
//public interface SalaryMapper {
//
//    //급여계산 입력 쿼리문
//    @Insert("INSERT INTO PAYROLL(PAYROLL_NO, EMP_NO, NATIONAL_PENSION ,HEALTH_INSURANCE ,LONG_CARE ,EMPLOYMENT_INSURANCE,INCOME_TAX ,LOCAL_INCOME_TAX ,POSITION ,BONUS ,HOLIDAY ,OVERTIMEWORK ,MEALS ,TOTAL_SALARY,PAYMENT_DATE)\n" +
//            "SELECT \n" +
//            "    SEQ_PAYROLL_NO.NEXTVAL\n" +
//            "    , E.EMP_NO\n" +
//            "    , (E.SALARY * R.PENSION_PERCENTAGE)\n" +
//            "    , (E.SALARY * R.HEALTH_INSURANCE_PERCENTAGE)\n" +
//            "    , (E.SALARY * R.HEALTH_INSURANCE_PERCENTAGE * LONG_CARE_PERCENTAGE)\n" +
//            "    ,(E.SALARY*EMPLOYMENT_INSURANCE_PERCENTAGE) \n" +
//            "    ,#{svo.incomeTax}\n" +
//            "    ,#{svo.localIncomeTax}\n" +
//            "    ,#{svo.position}\n" +
//            "    ,#{svo.bonus}\n" +
//            "    ,#{svo.holiday}\n" +
//            "    ,#{svo.overTimeWork}\n" +
//            "    ,#{svo.meals}\n" +
//            "    ,(E.SALARY - (E.SALARY * R.PENSION_PERCENTAGE) -(E.SALARY * R.HEALTH_INSURANCE_PERCENTAGE) - (E.SALARY * R.HEALTH_INSURANCE_PERCENTAGE * LONG_CARE_PERCENTAGE) - (E.SALARY*EMPLOYMENT_INSURANCE_PERCENTAGE) -#{svo.incomeTax}- #{svo.localIncomeTax} + #{svo.position} + #{svo.bonus} + #{svo.holiday} + #{svo.overTimeWork} + #{svo.meals})\n" +
//            "    ,SYSDATE\n" +
//            "FROM PERSONNEL_INFORMATION E, RATES R\n" +
//            "WHERE E.EMP_NO = #{vo.empNo}")
//    int salaryWrite(@Param("vo") UserVo vo,@Param("svo") SalaryVo svo, @Param("rvo") RatesVo rvo, double holidayTime, double overTime, double person, double position);
//
//    //급여 4대보험 최종 값 가져오기
//    @Select("SELECT \n" +
//            "    EMP_NO\n" +
//            "    ,NATIONAL_PENSION\n" +
//            "    ,HEALTH_INSURANCE\n" +
//            "    ,LONG_CARE\n" +
//            "    ,EMPLOYMENT_INSURANCE\n" +
//            "    ,INCOME_TAX\n" +
//            "    ,LOCAL_INCOME_TAX\n" +
//            "FROM PAYROLL")
//    SalaryVo getSalaryVo();
//
//    // 4대보험 요율 가져오기
//    @Select("SELECT \n" +
//            "    PENSION_PERCENTAGE\n" +
//            "    ,HEALTH_INSURANCE_PERCENTAGE\n" +
//            "    ,LONG_CARE_PERCENTAGE\n" +
//            "    ,EMPLOYMENT_INSURANCE_PERCENTAGE\n" +
//            "    ,LOCAL_INCOME_TAX_PERSENTAGE\n" +
//            "FROM RATES")
//    RatesVo getRatesVo();
//
//    @Select("")
//    UserVo getUserVo();
//
//    //-----------------------------------------------------------------------------------------------
//
//    //4대보험 입력 쿼리문
//    @Insert("INSERT INTO RATES(BASE_YEAR,PENSION_PERCENTAGE,HEALTH_INSURANCE_PERCENTAGE,LONG_CARE_PERCENTAGE,EMPLOYMENT_INSURANCE_PERCENTAGE,LOCAL_INCOME_TAX_PERSENTAGE) \n" +
//            "VALUES(SYSDATE,#{pensionPercentage},#{healthPercentage},#{longCarePercentage},#{employmentPercentage},#{localIncomeTaxPercentage})")
//    int ratesWrite(RatesVo vo);
//
//    @Select("SELECT * FROM SALARY")
//    List<SalaryVo> getSalaryList();
//
//
//}
