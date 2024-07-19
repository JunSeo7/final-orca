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
    private double calculateDeduction(ClientVo clientVo){

        //비과세 뺌 (인적공제 가격 화면에 보여주기-> 회계팀이 직접 입력)

        UserVo vo = dao.getUserVo(clientVo.getEmpNo());
        System.out.println("calculateDeduction vo = " + vo);
        double totalIncome = vo.getSalary()- clientVo.getMeals(); //총 급여

        // 공제대상 가족 수
        int familyCount = clientVo.getPerson();

        // 간이세액표를 참고하여 공제 금액 계산
        double deduction = 0;
        if (totalIncome <= 3000000) {
            deduction = 310000 + (totalIncome * getRate(familyCount, 1));
        } else if (totalIncome <= 4500000) {
            deduction = 310000 + (3000000 * getRate(familyCount, 1)) + ((totalIncome - 3000000) * getRate(familyCount, 2));
        } else if (totalIncome <= 7000000) {
            deduction = 310000 + (3000000 * getRate(familyCount, 1)) + (1500000 * getRate(familyCount, 2)) + ((totalIncome - 4500000) * getRate(familyCount, 3));
        } else if (totalIncome <= 10000000) {
            deduction = 310000 + (3000000 * getRate(familyCount, 1)) + (1500000 * getRate(familyCount, 2)) + (2500000 * getRate(familyCount, 3)) + ((totalIncome - 7000000) * getRate(familyCount, 4));
        } else {
            deduction = 310000 + (3000000 * getRate(familyCount, 1)) + (1500000 * getRate(familyCount, 2)) + (2500000 * getRate(familyCount, 3)) + (3000000 * getRate(familyCount, 4)) + ((totalIncome - 10000000) * getRate(familyCount, 5));
        }

        System.out.println("deduction = " + deduction);
        return deduction;

//        // 가족 공제 추가
//        double familyDeduction = clientVo.getPerson();
//        if (clientVo.getPerson() == 1) {
//            familyDeduction = 12500;
//        } else if (clientVo.getPerson() == 2) {
//            familyDeduction = 29160;
//        } else if (clientVo.getPerson() >= 3) {
//            familyDeduction = 29160 + (clientVo.getPerson() - 2) * 25000;
//        }
//
//        //공제 금액
//        //double totalDeduction = totalIncome-(basicDeduction + additionalDeduction + familyDeduction);
//        double totalDeduction = totalIncome - familyDeduction;
//        System.out.println("totalDeduction = " + totalDeduction);
//        return totalDeduction;
    }

    private double getRate(int familyCount, int category) {
        if (familyCount == 1) {
            switch (category) {
                case 1: return 0.04;
                case 2: return 0.05;
                case 3: return 0.015;
                case 4: return 0.005;
                case 5: return 0.0;
            }
        } else if (familyCount == 2) {
            switch (category) {
                case 1: return 0.04;
                case 2: return 0.05;
                case 3: return 0.02;
                case 4: return 0.01;
                case 5: return 0.0;
            }
        } else {
            switch (category) {
                case 1: return 0.07;
                case 2: return 0.05;
                case 3: return 0.05;
                case 4: return 0.03;
                case 5: return 0.0;
            }
        }
        return 0.0; // 기본값
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
        System.out.println("tax = " + tax);
        return tax;




    }

    // 지방소득세 (소득세 * 0.10)
    //----------------------------------------------------------------------------------------------
    //휴일근로수당 (입력칸 있음 - 휴일에 몇시간 일한건지 입력 칸 만들기)
    //연장근로수당 (입력칸 있음 - 몇 시간 더 연장근무 한건지 )

    //급여 입력
    public int salaryWrite(ClientVo clientVo) {
        UserVo vo = dao.getUserVo(clientVo.getEmpNo());

        double totalDeduction = calculateDeduction(clientVo);

        RatesVo rvo = dao.getRatesVo();

        double realSalary = vo.getSalary()- clientVo.getMeals(); //월급에서 비과세 빼고 공제사항 계산

        // 공제 항목 계산
        double nationalPension = realSalary * rvo.getPensionPercentage();
        double healthInsurance = realSalary * rvo.getHealthInsurancePercentage();
        double longCare = realSalary * rvo.getHealthInsurancePercentage() * rvo.getLongCarePercentage();
        double employmentInsurance = realSalary * rvo.getEmploymentInsurancePercentage();
        double incomeTax = calculateInComeTax(totalDeduction);  // 소득세 계산 메소드 호출
        double localIncomeTax = calculateInComeTax(totalDeduction) * rvo.getLocalIncomeTaxPersentage();

        // 수당 항목 계산
        double holiday =  9860 * 1.5 * clientVo.getHolidayTime();
        double overTimeWork = 9860 * 1.5 * clientVo.getOverTime();

        // 최종 급여 계산
        double totalSalary = realSalary - nationalPension - healthInsurance - longCare - employmentInsurance - incomeTax - localIncomeTax
                + clientVo.getPosition() + clientVo.getBonus() + holiday + overTimeWork + clientVo.getMeals();

        SalaryVo svo = new SalaryVo();
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

        int result = dao.salaryWrite(clientVo, svo);

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
    public int salaryUpdate(ClientVo clientVo) {
        UserVo vo = dao.getUserVo(clientVo.getEmpNo());

        System.out.println("salaryUpdate vo = " + vo);

        double totalDeduction = calculateDeduction(clientVo);
        RatesVo rvo = dao.getRatesVo();

        double realSalary =  vo.getSalary() - clientVo.getMeals();

        // 공제 항목 계산
        double nationalPension = realSalary * rvo.getPensionPercentage();
        double healthInsurance = realSalary * rvo.getHealthInsurancePercentage();
        double longCare = realSalary * rvo.getHealthInsurancePercentage() * rvo.getLongCarePercentage();
        double employmentInsurance = realSalary * rvo.getEmploymentInsurancePercentage();
        double incomeTax = calculateInComeTax(totalDeduction);  // 소득세 계산 메소드 호출
        double localIncomeTax = calculateInComeTax(totalDeduction) * rvo.getLocalIncomeTaxPersentage();


        // 수당 항목 계산
        double holiday =  9860 * 1.5 * clientVo.getHolidayTime();
        double overTimeWork = 9860 * 1.5 * clientVo.getOverTime();

        // 최종 급여 계산
        double totalSalary = realSalary - nationalPension - healthInsurance - longCare - employmentInsurance - incomeTax - localIncomeTax
                + clientVo.getPosition() + clientVo.getBonus() + holiday + overTimeWork + clientVo.getMeals();

        SalaryVo svo = new SalaryVo();
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
        svo.setPayrollNo(clientVo.getPayrollNo());
        svo.setEmpNo(clientVo.getEmpNo());

        System.out.println("svo = " + svo);
        System.out.println("totalSalary = " + totalSalary);
        System.out.println("clientVo = " + clientVo);

        return dao.salaryUpdate(clientVo,svo);

    }

    //4대보험 요율 수정
    public int ratesEdit(RatesVo rvo) {
        return dao.ratesEdit(rvo);
    }

    //요율 전체 보기
    public RatesVo getRatesByOne() {
        return dao.getRatesByOne();
    }

    public int delete(String ratesNo) {
        return dao.delete(ratesNo);
    }

    public SalaryVo getSalaryByNo(String payrollNo) {
        return dao.getSalaryByNo(payrollNo);
    }

    //급여 삭제
    public int getSalaryDelete(String payrollNo) {
        return dao.getSalaryDelete(payrollNo);
    }

    //급여 검색
    public List<SalaryVo> searchSalary(String empNo) {
        return dao.searchSalary(empNo);
    }


}












