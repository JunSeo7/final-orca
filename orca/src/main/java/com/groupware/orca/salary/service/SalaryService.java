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

    // 국민연금(월급 * 0.045)
    // 건강보험 (월급 * 0.03545)
    // 장기요양보험 (건강보험료 * 0.1295)
    // 고용보험 (근로자는 실업급여만 -> 월급 * 0.009)


    //(전)소득세 계산(단계 많음.. 1. 소득 공제 2. 공제 다 더하기== 과세표준 3. 소득세율 적용 4. 범위에 해당하는 소득세율 다 더하기 == 근로소득세) -> 얼렁뚱땅
    //(후)연말 정산 (연말에 전년도에 냈던 세금과 월급을 계산해서 최종 세금을 계산함. 이때 가족공제(인적공제)를 해서 정확한 세금계산을 할 수 있음.)
    //(후)원천징수(근로소득에서 세금을 공제함. 따라서 기본 공제액 150만원)
    private double calculateDeduction(UserVo vo,ClientVo clientVo,SalaryVo svo){

        //비과세 뺌 (인적공제 가격 화면에 보여주기-> 회계팀이 직접 입력)
        vo = dao.getUserVo(vo.getEmpNo());
        double totalIncome = (vo.getSalary() * 12) - (svo.getMeals() * 12); //총 급여
        System.out.println("totalIncome = " + totalIncome);

        double basicDeduction = 1500000; // 인당 기본 공제액 150만원 (원천징수라서 기본 공제액이 150만원)
        double additionalDeduction = 0;

        if (totalIncome <= 30000000) {
            additionalDeduction = 0;
        } else if (totalIncome <= 45000000) {
            additionalDeduction = (totalIncome - 30000000) * 0.09;
        } else if (totalIncome <= 70000000) {
            additionalDeduction = 1200000 + (totalIncome - 45000000) * 0.045;
        } else if (totalIncome <= 120000000) {
            additionalDeduction = 2350000 + (totalIncome - 70000000) * 0.03;
        } else {
            additionalDeduction = 3650000 + (totalIncome - 120000000) * 0.015;
        }

        // 가족 공제 추가
        double familyDeduction = clientVo.getPerson();
        if (clientVo.getPerson() == 1) {
            familyDeduction = 12500;
        } else if (clientVo.getPerson() == 2) {
            familyDeduction = 29160;
        } else if (clientVo.getPerson() >= 3) {
            familyDeduction = 29160 + (clientVo.getPerson() - 2) * 25000;
        }

        //공제 금액
        double totalDeduction = totalIncome-(basicDeduction + additionalDeduction + familyDeduction);
        System.out.println("totalDeduction = " + totalDeduction);
        return totalDeduction;
    }

    private double calculateInComeTax(double totalDeduction) {
        //소득세 배열
        double[] TAX_BASES = {0,12000000,46000000,88000000,150000000,300000000};
        double[] TAX_RATES = {0.06,0.15,0.24,0.35,0.38,0.4};

        double tax = 0;

        for (int i = TAX_BASES.length - 1; i >= 0; i--) {
            if (totalDeduction > TAX_BASES[i]) {
                tax += (totalDeduction - TAX_BASES[i]) * TAX_RATES[i];
                totalDeduction = TAX_BASES[i];
            }
        }
        return tax;

    }

    // 지방소득세 (소득세 * 0.10)
    //----------------------------------------------------------------------------------------------
    //휴일근로수당 (입력칸 있음 - 휴일에 몇시간 일한건지 입력 칸 만들기)
    //연장근로수당 (입력칸 있음 - 몇 시간 더 연장근무 한건지 )


    public int salaryWrite(ClientVo clientVo, UserVo vo, SalaryVo svo) {
        double totalDeduction = calculateDeduction(vo,clientVo,svo);
//        double tax = calculateInComeTax(totalDeduction);

        vo = dao.getUserVo(vo.getEmpNo());
        RatesVo rvo = dao.getRatesVo();

        System.out.println("vo.getSalary() - svo.getMeals() = " + (vo.getSalary() - svo.getMeals()));

        System.out.println("vo = " + vo);
        double realSalary = vo.getSalary()- svo.getMeals(); //월급에서 비과세 빼고 공제사항 계산
        System.out.println("realSalary = " + realSalary);
        System.out.println("vo.getSalary() = " + vo.getSalary());
        System.out.println(" svo.getMeals() = " +svo.getMeals());

        // 공제 항목 계산
        double nationalPension = realSalary * rvo.getPensionPercentage();
        double healthInsurance = realSalary * rvo.getHealthInsurancePercentage();
        double longCare = realSalary * rvo.getHealthInsurancePercentage() * rvo.getLongCarePercentage();
        double employmentInsurance = realSalary * rvo.getEmploymentInsurancePercentage();
        double incomeTax = calculateInComeTax(totalDeduction)/12;  // 소득세 계산 메소드 호출
        double localIncomeTax = incomeTax * rvo.getLocalIncomeTaxPersentage();

        // 수당 항목 계산
        double holiday =  9860 * 1.5 * clientVo.getHolidayTime();
        double overTimeWork = 9860 * 1.5 * clientVo.getOverTime();

        // 최종 급여 계산
        double totalSalary = realSalary - nationalPension - healthInsurance - longCare - employmentInsurance - incomeTax - localIncomeTax
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
        System.out.println("incomeTaxBASES(clientVo,vo)/12 : "+calculateInComeTax(totalDeduction)/12);
        System.out.println("rvo.getLocalIncomeTaxPercentage() : "+rvo.getLocalIncomeTaxPersentage());
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


    //급여 수정
    public int salaryUpdate(UserVo vo,ClientVo clientVo, SalaryVo svo) {
        double totalDeduction = calculateDeduction(vo,clientVo,svo);
        RatesVo rvo = dao.getRatesVo();
        vo = dao.getUserVo(vo.getEmpNo());

        double realSalary =  vo.getSalary()- svo.getMeals();

        System.out.println("rvo : "+rvo);

        // 공제 항목 계산
        double nationalPension = realSalary * rvo.getPensionPercentage();
        double healthInsurance = realSalary * rvo.getHealthInsurancePercentage();
        double longCare = realSalary * rvo.getHealthInsurancePercentage() * rvo.getLongCarePercentage();
        double employmentInsurance = realSalary * rvo.getEmploymentInsurancePercentage();
        double incomeTax = calculateInComeTax(totalDeduction)/12;  // 소득세 계산 메소드 호출
        double localIncomeTax = incomeTax * rvo.getLocalIncomeTaxPersentage();

        // 수당 항목 계산
        double holiday =  9860 * 1.5 * clientVo.getHolidayTime();
        double overTimeWork = 9860 * 1.5 * clientVo.getOverTime();

        // 최종 급여 계산
        double totalSalary = realSalary - nationalPension - healthInsurance - longCare - employmentInsurance - incomeTax - localIncomeTax
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


        return dao.salaryUpdate(clientVo,vo,svo);

    }

    //4대보험 요율 수정
    public Integer ratesUpdate(RatesVo rvo) {
        return dao.ratesEdit(rvo);
    }

    //요율 전체 보기
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

    public SalaryVo searchSalary(String empNo) {
        return dao.searchSalary(empNo);
    }
}












