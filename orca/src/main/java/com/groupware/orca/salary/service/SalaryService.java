//package com.groupware.orca.salary.service;
//
//import com.groupware.orca.salary.dao.SalaryDao;
//import com.groupware.orca.salary.vo.RatesVo;
//import com.groupware.orca.salary.vo.SalaryVo;
//import com.groupware.orca.user.vo.UserVo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class SalaryService {
//
//    private final SalaryDao dao;
//
//    //소득세 배열
//    private static final double[] TAX_BASES = {0,12000000,46000000,88000000,150000000,300000000};
//    private static final double[] TAX_RATES = {0.06,0.15,0.24,0.35,0.38,0.4};
//
//    // 국민연금(월급 * 0.045)
//    // 건강보험 (월급 * 0.03545)
//    // 장기요양보험 (건강보험료 * 0.1295)
//    // 고용보험 (근로자는 실업급여만 -> 월급 * 0.009)
//
//     //소득세 (단계 많음.. 1. 소득 공제 2. 공제 다 더하기== 과세표준 3. 소득세율 적용 4. 범위에 해당하는 소득세율 다 더하기 == 근로소득세)
//    private double IncomeTaxBASES (UserVo vo, SalaryVo svo){
//
//        //비과세 뺌 (인적공제 가격 화면에 보여주기-> 회계팀이 직접 입력)
//        double incomTaxCalcul = (vo.getSalary() * 12) - (svo.getMeals() + svo.getEmploymentInsurance()); //연봉 - 비과세
//
//        //최종 소득세
//        return calculateInComeTax(incomTaxCalcul);
//    }
//
//    private double calculateInComeTax(double incomTaxCalcul) {
//
//        double tax = 0;
//
//        for (int i = TAX_BASES.length - 1; i >= 0; i--) {
//            if (incomTaxCalcul > TAX_BASES[i]) {
//                tax += (incomTaxCalcul - TAX_BASES[i]) * TAX_RATES[i];
//                incomTaxCalcul = TAX_BASES[i];
//            }
//        }
//        return tax;
//
//    }
//
//    // 지방소득세 (소득세 * 0.10)
//    private double calculateLocalIncomeTax (double incomeTax,RatesVo rvo){
//        return incomeTax * rvo.getLocalIncomeTaxPercentage();
//    }
//
//    //----------------------------------------------------------------------------------------------
//    //휴일근로수당 (입력칸 있음 - 휴일에 몇시간 일한건지 입력 칸 만들기)
//    //연장근로수당 (입력칸 있음 - 몇 시간 더 연장근무 한건지 )
//
//
//    public double salaryWrite(UserVo vo, double holidayTime, double overTime, double person, double position) {
//        SalaryVo svo = dao.getSalaryVo();
//        RatesVo rvo = dao.getRatesVo();
//        UserVo vo = dao.getUserVo();
//
//        // 공제 항목 계산
//        double incomeTax = IncomeTaxBASES(vo,svo) - person;  // 소득세 계산 메소드 호출
//        double localIncomeTax = calculateLocalIncomeTax(incomeTax, rvo);
//
//        // 수당 항목 계산
//        double holiday =  9860 * 1.5 * holidayTime;
//        double overTimeWork = 9860 * 1.5 * overTime;
//
//        // 최종 급여 계산
//        double finalSalary = vo.getSalary() - svo.getNationalPension() - svo.getHealthInsurance() - svo.getLongCare() - svo.getEmploymentInsurance() - incomeTax - localIncomeTax
//                + svo.getPosition() + svo.getBonus() + holiday + overTimeWork + svo.getMeals();
//
//        System.out.println("SalaryService.salaryWrite");
//        System.out.println("vo.getSalary : "+ vo.getSalary());
//        System.out.println("nationalPension "+svo.getNationalPension());
//        System.out.println("healthInsurance "+ svo.getHealthInsurance() );
//        System.out.println("longCare "+svo.getLongCare());
//        System.out.println("employmentInsurance "+ svo.getEmploymentInsurance());
//        System.out.println("incomeTax "+incomeTax);
//        System.out.println("localIncomeTax "+localIncomeTax);
//        System.out.println("svo.getPosition() "+svo.getPosition());
//        System.out.println("svo.getBonus() "+svo.getBonus());
//        System.out.println("holiday "+holiday);
//        System.out.println("overTimeWork "+overTimeWork);
//        System.out.println("svo.getMeals() "+svo.getMeals());
//        System.out.println("finalSalary : "+ finalSalary);
//        System.out.println("rvo.getPensionPercentage() "+rvo.getPensionPercentage());
//        System.out.println("rvo.getHealthPercentage() "+rvo.getHealthPercentage());
//        System.out.println("rvo.getLongCarePercentage() "+rvo.getLongCarePercentage());
//        System.out.println("rvo.getEmploymentPercentage() "+rvo.getEmploymentPercentage());
//        System.out.println("rvo.getEmploymentPercentage() "+rvo.getLocalIncomeTaxPercentage());
//        System.out.println("person "+person);
//
//        try {
//            int result = (int) dao.salaryWrite(vo, svo, rvo, holidayTime, overTime, person, position);
//            if (result > 0) {
//                System.out.println("SalaryService.salaryWrite: 성공적으로 저장되었습니다.");
//            } else {
//                System.out.println("SalaryService.salaryWrite: 저장에 실패했습니다.");
//            }
//            return result;
//        } catch (Exception e) {
//            System.err.println("SalaryService.salaryWrite: 예외가 발생했습니다.");
//            e.printStackTrace();
//            return -1;
//        }
//
//    }
//
//    public int ratesWrite(RatesVo vo) {
//        return dao.ratesWrite(vo);
//    }
//
//    public List<SalaryVo> getSalaryList() {
//
//        return dao.getSalaryList();
//    }
//
//
//
//}
