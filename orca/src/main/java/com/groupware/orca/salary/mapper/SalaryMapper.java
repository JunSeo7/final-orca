package com.groupware.orca.salary.mapper;

import com.groupware.orca.salary.vo.ClientVo;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SalaryMapper {

    //급여계산 입력 쿼리문
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
              ,#{vo.empNo}
              ,#{svo.nationalPension}
              ,#{svo.healthInsurance}
              ,#{svo.longCare}
              ,#{svo.employmentInsurance}
              ,#{svo.incomeTax}
              ,#{svo.localIncomeTax}
              ,#{svo.position}
              ,#{svo.bonus}
              ,#{svo.holiday}
              ,#{svo.overTimeWork}
              ,#{svo.meals}
              ,#{svo.totalSalary}
              ,SYSDATE
            )
            """)
    int salaryWrite(@Param("vo") UserVo vo, ClientVo clientVo,@Param("svo") SalaryVo svo);

    //4대보험 입력 쿼리문
    @Insert("INSERT INTO RATES(RATES_NO,BASE_YEAR,PENSION_PERCENTAGE,HEALTH_INSURANCE_PERCENTAGE,LONG_CARE_PERCENTAGE,EMPLOYMENT_INSURANCE_PERCENTAGE,LOCAL_INCOME_TAX_PERSENTAGE) \n" +
            "VALUES(SEQ_RATES_NO.NEXTVAL,TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD'),#{pensionPercentage},#{healthInsurancePercentage},#{longCarePercentage},#{employmentInsurancePercentage},#{localIncomeTaxPersentage})")
    int ratesWrite(RatesVo vo);

    // 4대보험 요율 가져오기
    @Select("SELECT * FROM RATES")
    RatesVo getRatesVo();

    //사원 정보 가져오기 (salary, empNo)
    @Select("SELECT * FROM PERSONNEL_INFORMATION WHERE EMP_NO = #{empNo}")
    UserVo getUserVo(int empNo);

    //화면에 보여줄 때는 이 쿼리문 사용하기!!
    //            SELECT
//                S.PAYROLL_NO
//                ,P.EMP_NO
//                ,P.NAME
//                ,FLOOR(ROUND(TOTAL_SALARY, 1)) AS TOTAL_SALARY
//                ,S.PAYMENT_DATE
//            FROM PAYROLL S
//            JOIN PERSONNEL_INFORMATION P ON P.EMP_NO = S.EMP_NO

    //급여관리 목록조회
    @Select("""
               SELECT
                   S.PAYROLL_NO
                   ,P.EMP_NO
                   ,P.NAME
                   ,FLOOR(ROUND(NATIONAL_PENSION+HEALTH_INSURANCE+LONG_CARE+EMPLOYMENT_INSURANCE+INCOME_TAX+LOCAL_INCOME_TAX, 1)) AS TOTAL_DEDUCTION_ITEMS
                   ,FLOOR(ROUND(BONUS+POSITION+HOLIDAY+OVERTIMEWORK+MEALS, 1)) AS TOTAL_ALLOWANCE_ITEMS
                   ,FLOOR(ROUND(TOTAL_SALARY, 1)) AS TOTAL_SALARY
                   ,S.PAYMENT_DATE
                   FROM PAYROLL S
                   JOIN PERSONNEL_INFORMATION P ON P.EMP_NO = S.EMP_NO
                   ORDER BY PAYROLL_NO DESC
            """)
    List<SalaryVo> getSalaryList();


    //급여상세조회
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
              WHERE  PAYROLL_NO = #{payrollNo}
            """)
    SalaryVo getSalaryByNo(@Param("payrollNo") String payrollNo);

    //급여관리 수정
    @Update("""
            UPDATE PAYROLL
                SET NATIONAL_PENSION = #{svo.nationalPension}
                   ,HEALTH_INSURANCE = #{svo.healthInsurance}
                   ,LONG_CARE = #{svo.longCare}
                   ,EMPLOYMENT_INSURANCE = #{svo.employmentInsurance}
                   ,INCOME_TAX = #{svo.incomeTax}
                   ,POSITION = #{svo.position}
                   ,BONUS = #{svo.bonus}
                   ,HOLIDAY = #{svo.holiday}
                   ,OVERTIMEWORK = #{svo.overTimeWork}
                   ,MEALS = #{svo.meals}
                   ,TOTAL_SALARY = #{svo.totalSalary}
                   ,PAYMENT_DATE = SYSDATE
            WHERE PAYROLL_NO = #{svo.payrollNo}
            """)
    int salaryUpdate(@Param("clientVo") ClientVo clientVo,@Param("vo") UserVo vo,@Param("svo") SalaryVo svo);
    //    @Update("UPDATE PAYROLL\n" +
//            "    SET HOLIDAY = #{clientVo.holidayTime}\n" +
//            "    , POSITION = #{clientVo.position}\n" +
//            "    , BONUS = #{clientVo.bonus}\n" +
//            "    , OVERTIMEWORK = #{clientVo.overTime}\n" +
//            "    , MEALS = #{clientVo.meals}\n" +
//            "    , INCOMTAX = #{svo.person}\n" +
//            "WHERE EMP_NO = #{vo.empNo}")
//    int salaryUpdate(@Param("clientVo") ClientVo clientVo,@Param("vo")UserVo vo, RatesVo rvo,@Param("svo") SalaryVo svo);

    //급여관리 삭제
    @Delete("""
            DELETE PAYROLL WHERE PAYROLL_NO = #{payrollNo}
            """)
    int getSalaryDelete(@Param("payrollNo") String payrollNo);

    //급여 사원 검색
    @Select("""
            SELECT *\s
            FROM PAYROLL S\s
            JOIN PERSONNEL_INFORMATION P ON P.EMP_NO = S.EMP_NO
            WHERE P.EMP_NO = #{empNo}
            """)
    List<SalaryVo> searchSalary(@Param("empNo") String empNo);

    //----------------------------------------------------------------------------------------------

    // 4대보험 목록조회
    @Select("""
            SELECT
                RATES_NO
                 ,TO_CHAR(BASE_YEAR, 'YYYY-MM-DD') AS BASE_YEAR
                ,PENSION_PERCENTAGE
                ,HEALTH_INSURANCE_PERCENTAGE
                ,LONG_CARE_PERCENTAGE
                ,EMPLOYMENT_INSURANCE_PERCENTAGE
                ,LOCAL_INCOME_TAX_PERSENTAGE
            FROM RATES
            """)
    RatesVo getRatesByOne();

    //4대보험 삭제
    @Delete("""
            DELETE RATES WHERE RATES_NO = #{ratesNo}
            """)
    int delete(@Param("ratesNo") String ratesNo);

    // 4대 보험 수정
    @Update("""
            UPDATE RATES
                SET BASE_YEAR = TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD')
                    ,PENSION_PERCENTAGE = #{rvo.pensionPercentage}
                    ,HEALTH_INSURANCE_PERCENTAGE = #{rvo.healthInsurancePercentage}
                    ,LONG_CARE_PERCENTAGE = #{rvo.longCarePercentage}
                    ,EMPLOYMENT_INSURANCE_PERCENTAGE = #{rvo.employmentInsurancePercentage}
                    ,LOCAL_INCOME_TAX_PERSENTAGE = #{rvo.localIncomeTaxPersentage}
                WHERE RATES_NO = #{rvo.ratesNo}
            """)
    int ratesEdit(@Param("rvo") RatesVo rvo);



    //LOCAL_INCOME_TAX_PERSENTAGE
}
