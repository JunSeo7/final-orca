package com.groupware.orca.salary.mapper;

import com.groupware.orca.salary.vo.ClientVo;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
    @Insert("INSERT INTO RATES(BASE_YEAR,PENSION_PERCENTAGE,HEALTH_INSURANCE_PERCENTAGE,LONG_CARE_PERCENTAGE,EMPLOYMENT_INSURANCE_PERCENTAGE,LOCAL_INCOME_TAX_PERSENTAGE) \n" +
            "VALUES(SYSDATE,#{pensionPercentage},#{healthInsurancePercentage},#{longCarePercentage},#{employmentInsurancePercentage},#{localIncomeTaxPercentage})")
    int ratesWrite(RatesVo vo);

    // 4대보험 요율 가져오기
    @Select("SELECT * FROM RATES")
    RatesVo getRatesVo();

    //사원 정보 가져오기 (salary, empNo)
    @Select("SELECT * FROM PERSONNEL_INFORMATION WHERE EMP_NO = #{empNo}")
    UserVo getUserVo(String empNo);

    //급여관리 목록조회
    @Select("SELECT * FROM PAYROLL")
    List<SalaryVo> getSalaryList();

    //급여 4대보험 목록조회 --- 수정하기!!
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

    @Select("""
            SELECT *\s
            FROM PAYROLL S\s
            JOIN PERSONNEL_INFORMATION P ON P.EMP_NO = S.EMP_NO
            WHERE P.EMP_NO = #{empNo}
            """)
    SalaryVo getSalaryByNo(@Param("empNo") String empNo);

    //급여관리 수정
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
            DELETE PAYROLL WHERE EMP_NO = #{empNo}
            """)
    int getSalaryDelete(@Param("empNo") String empNo);

    //----------------------------------------------------------------------------------------------

    // 4대보험 목록조회
    @Select("""
            SELECT * FROM RATES
            """)
    List<RatesVo> getRatesList();

    //4대보험 삭제
    @Delete("""
            DELETE RATES WHERE RATES_NO = #{ratesNo}
            """)
    int delete(String ratesNo);

    // 4대 보험 수정
    @Select("""
            UPDATE RATES
                SET BASE_YEAR = SYSDATE
                    ,PENSION_PERCENTAGE = #{pensionPercentage}
                    ,HEALTH_INSURANCE_PERCENTAGE = #{healthInsurancePercentage}
                    ,LONG_CARE_PERCENTAGE = #{longCarePercentage}
                    ,EMPLOYMENT_INSURANCE_PERCENTAGE = #{employmentInsurancePercentage}
                    ,LOCAL_INCOME_TAX_PERSENTAGE = #{localIncomeTaxPercentage}
                WHERE RATES_NO = #{ratesNo}
            """)
    Integer ratesEdit(RatesVo rvo);


}
