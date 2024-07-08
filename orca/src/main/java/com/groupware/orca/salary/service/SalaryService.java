package com.groupware.orca.salary.service;

import com.groupware.orca.salary.dao.SalaryDao;
import com.groupware.orca.salary.vo.ClientVo;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryService {

    private final SalaryDao dao;

    //소득세 배열
    private static final double[] TAX_BASES = {0,12000000,46000000,88000000,150000000,300000000};
    private static final double[] TAX_RATES = {0.06,0.15,0.24,0.35,0.38,0.4};

    // 국민연금(월급 * 0.045)
    // 건강보험 (월급 * 0.03545)
    // 장기요양보험 (건강보험료 * 0.1295)
    // 고용보험 (근로자는 실업급여만 -> 월급 * 0.009)

     //소득세 (단계 많음.. 1. 소득 공제 2. 공제 다 더하기== 과세표준 3. 소득세율 적용 4. 범위에 해당하는 소득세율 다 더하기 == 근로소득세)
    private double incomeTaxBASES(ClientVo clientVo,UserVo vo){

        //비과세 뺌 (인적공제 가격 화면에 보여주기-> 회계팀이 직접 입력)
        vo = dao.getUserVo(vo.getEmpNo());
        double incomTaxCalcul = (vo.getSalary() * 12) - (clientVo.getMeals() - clientVo.getPerson()); //연봉 - 비과세
// + svo.getEmploymentInsurance()
        //최종 소득세
        return calculateInComeTax(incomTaxCalcul);
    }

    private double calculateInComeTax(double incomTaxCalcul) {

        double tax = 0;

        for (int i = TAX_BASES.length - 1; i >= 0; i--) {
            if (incomTaxCalcul > TAX_BASES[i]) {
                tax += (incomTaxCalcul - TAX_BASES[i]) * TAX_RATES[i];
                incomTaxCalcul = TAX_BASES[i];
            }
        }
        return tax;

    }

    // 지방소득세 (소득세 * 0.10)
    //----------------------------------------------------------------------------------------------
    //휴일근로수당 (입력칸 있음 - 휴일에 몇시간 일한건지 입력 칸 만들기)
    //연장근로수당 (입력칸 있음 - 몇 시간 더 연장근무 한건지 )


    public int salaryWrite(ClientVo clientVo, UserVo vo, SalaryVo svo) {
        UserVo UserVo = dao.getUserVo(vo.getEmpNo());
        RatesVo rvo = dao.getRatesVo();

        System.out.println("rvo : "+rvo);

        // 공제 항목 계산
        double nationalPension = UserVo.getSalary() * rvo.getPensionPercentage();
        double healthInsurance = UserVo.getSalary() * rvo.getHealthInsurancePercentage();
        double longCare = UserVo.getSalary() * rvo.getHealthInsurancePercentage() * rvo.getLongCarePercentage();
        double employmentInsurance = UserVo.getSalary() * rvo.getEmploymentInsurancePercentage();
        double incomeTax = incomeTaxBASES(clientVo,UserVo)/12;  // 소득세 계산 메소드 호출
        double localIncomeTax = (incomeTaxBASES(clientVo,vo)/12) * rvo.getLocalIncomeTaxPercentage();

        // 수당 항목 계산
        double holiday =  9860 * 1.5 * clientVo.getHolidayTime();
        double overTimeWork = 9860 * 1.5 * clientVo.getOverTime();

        // 최종 급여 계산
        double totalSalary = UserVo.getSalary() - nationalPension - healthInsurance - longCare - employmentInsurance - incomeTax - localIncomeTax
                + clientVo.getPosition() + clientVo.getBonus() + holiday + overTimeWork + clientVo.getMeals();

        svo = new SalaryVo();
        svo.setNationalPension(nationalPension);
        svo.setHealthInsurance(healthInsurance);
        svo.setLongCare(longCare);
        svo.setEmploymentInsurance(employmentInsurance);
        svo.setIncomeTax(incomeTax);
        svo.setLocalIncomeTax(localIncomeTax);
        svo.setHoliday(holiday);
        svo.setOverTimeWork(overTimeWork);
        svo.setTotalSalary(totalSalary);
        svo.setBonus(clientVo.getBonus());
        svo.setPosition(clientVo.getPosition());
        svo.setMeals(clientVo.getMeals());

        System.out.println("localIncomTax : "+ localIncomeTax);
        System.out.println("incomeTaxBASES(clientVo,vo)/12 : "+incomeTaxBASES(clientVo,vo)/12);
        System.out.println("rvo.getLocalIncomeTaxPercentage() : "+rvo.getLocalIncomeTaxPercentage());
        System.out.println("rvo.getPensionPercentage() : "+rvo.getPensionPercentage());

        int result = dao.salaryWrite(vo, clientVo, svo);

        try {

            if (result > 0) {
                System.out.println("SalaryService.salaryWrite: 성공적으로 저장되었습니다.");
            } else {
                System.out.println("SalaryService.salaryWrite: 저장에 실패했습니다.");
            }
            return result;
        } catch (Exception e) {
            System.err.println("SalaryService.salaryWrite: 예외가 발생했습니다.");
            e.printStackTrace();
            return -1;
        }


    }

    //사대보험 insert
    public int ratesWrite(RatesVo vo) {
        return dao.ratesWrite(vo);
    }

    //급여 전체 목록조회
    public List<SalaryVo> getSalaryList() {
        return dao.getSalaryList();
    }



//    //급여 수정
//    public int salaryUpdate(UserVo vo,RatesVo rvo) {
//        ClientVo clientVo = new ClientVo();
//
//        double nationalPension = vo.getSalary() * rvo.getPensionPercentage();
//        double healthInsurance = vo.getSalary() * rvo.getHealthInsurancePercentage();
//        double longCare = vo.getSalary() * rvo.getHealthInsurancePercentage() * rvo.getLongCarePercentage();
//        double employmentInsurance = vo.getSalary() * rvo.getEmploymentInsurancePercentage();
//        double incomeTax = incomeTaxBASES(clientVo,vo)/12; // 소득세 계산 메소드 호출
//        double localIncomeTax = (incomeTaxBASES(clientVo,vo)/12) * rvo.getLocalIncomeTaxPercentage();
//        double holiday =  9860 * 1.5 * clientVo.getHolidayTime();
//        double overTimeWork = 9860 * 1.5 * clientVo.getOverTime();
//
//        double totalSalary = vo.getSalary() - nationalPension - healthInsurance - longCare - employmentInsurance - incomeTax - localIncomeTax
//                + clientVo.getPosition() + clientVo.getBonus() + holiday + overTimeWork + clientVo.getMeals();
//
//        SalaryVo svo = new SalaryVo();
//
//        svo.setNationalPension(nationalPension);
//        svo.setHealthInsurance(healthInsurance);
//        svo.setLongCare(longCare);
//        svo.setEmploymentInsurance(employmentInsurance);
//        svo.setIncomeTax(incomeTax);
//        svo.setLocalIncomeTax(localIncomeTax);
//        svo.setHoliday(holiday);
//        svo.setOverTimeWork(overTimeWork);
//        svo.setTotalSalary(totalSalary);
//        svo.setBonus(clientVo.getBonus());
//        svo.setPosition(clientVo.getPosition());
//        svo.setMeals(clientVo.getMeals());
//
//        return dao.salaryUpdate(clientVo,vo,rvo);
//    }

    //4대보험 요율 수정
    public Integer ratesUpdate(RatesVo rvo) {
        return dao.ratesEdit(rvo);
    }

    public List<RatesVo> getRatesList() {
        return dao.getRatesList();
    }

    public int delete(String ratesNo) {
        return dao.delete(ratesNo);
    }

    public SalaryVo getSalaryByNo(String empNo) {
        System.out.println("empNo = " + empNo);
        return dao.getSalaryByNo(empNo);
    }

    public int getSalaryDelete(String empNo) {
        return dao.getSalaryDelete(empNo);
    }
}












