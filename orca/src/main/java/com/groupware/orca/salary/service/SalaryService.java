//package com.groupware.orca.salary.service;
//
//import com.groupware.orca.salary.dao.SalaryDao;
//import com.groupware.orca.salary.vo.RatesVo;
//import com.groupware.orca.salary.vo.SalaryVo;
//import com.groupware.orca.user.vo.UserVo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class SalaryService {
//
//    private final SalaryDao dao;
//
//    // 국민연금(월급 * 0.045)
//    private double calculatePension(double Salary,double pensionPercentage) {
//        //월급 * pensionPercentage
//        return Salary * pensionPercentage;
//    }
//
//    // 건강보험 (월급 * 0.03545)
//    private double calculateHeath(double Salary, double healthPercentage){
//        return Salary * healthPercentage;
//    }
//
//    // 장기요양보험 (건강보험료 * 0.1295)
//    private double calculateLongCare (double Salary, double longCarePercentage){
//        return Salary * longCarePercentage;
//    }
//
//    // 고용보험 (근로자는 실업급여만 -> 월급 * 0.009)
//    private double calculateEmployement (double Salary, double employmentPercentage){
//        return Salary * employmentPercentage;
//    }

    // 소득세 (단계 많음.. 1. 소득 공제 2. 공제 다 더하기== 과세표준 3. 소득세율 적용 4. 범위에 해당하는 소득세율 다 더하기 == 근로소득세)
//    private double calculateIncomeTax (double incomTax, UserVo vo,SalaryVo svo){
//        double incomTaxCalcul = vo.getSalary()*12 - (svo.getMeals()+ svo.getEmployementInsurance());
//
//        if(incomTaxCalcul < 12000000){
//            incomTax = 12000000 * 0.06;
//        }else if(incomTaxCalcul <= 14000000 && incomTaxCalcul > 50000000 ){
//            incomTax = 12000000 * 0.06;
//            incomTax =
//
//
//        }


//
//
//
//    }
//
//    // 지방소득세 (소득세 * 0.10)
//    private double calculateLocalIncomeTax (double incomeTax,double localIncomeTaxPercentage){
//        return incomeTax * localIncomeTaxPercentage;
//    }
//
//    // 직급수당 (직급별 상이)
//    private double calculatePositionAllowance (double position){
//        return position;
//    }
//
//    //보너스 (입력칸 있어서 더하기만 해주기)
//    private double calculateBonusAllowance (double bonus){
//        return bonus;
//    }
//
//    //휴일근로수당 (입력칸 있음 - 휴일에 몇시간 일한건지 입력 칸 만들기)
//    private double calculateHolidayAllowance (double holidayTime){
//        double holidayRate = 9860 * 1.5;
//        return holidayRate + holidayTime;
//    }
//
//    //연장근로수당 (입력칸 있음)
//    private double calculateOverTimeWorkAllowance (double overTimeWork){
//        double overTimeRate = 9860 * 1.5;
//        return overTimeRate + overTimeWork;
//    }
//
//    //식대 (입력칸 있음)
//    private double calculateMealsAllowance (double meals){
//        return meals;
//    }
//
//    public double salaryWrite(UserVo vo, SalaryVo svo, RatesVo rvo, double holidayTime, double overTime) {
//
//        // 공제 항목 계산
//        double pension = calculatePension(vo.getSalary(), rvo.getPensionPercentage());
//        double healthInsurance = calculateHeath(vo.getSalary(), rvo.getHealthPercentage());
//        double longCare = calculateLongCare(healthInsurance, rvo.getLongCarePercentage());
//        double employmentInsurance = calculateEmployement(vo.getSalary(), rvo.getEmploymentPercentage());
//        double incomeTax = calculateIncomeTax(vo.getSalary());  // 소득세 계산 메소드 호출
//        double localIncomeTax = calculateLocalIncomeTax(incomeTax, rvo.getLocalIncomeTaxPercentage());
//
//        // 수당 항목 계산
//        \
//        double positionAllowance = calculatePositionAllowance(svo.getPosition());
//        double bonusAllowance = calculateBonusAllowance(svo.getBonus());
//        double holidayAllowance = calculateHolidayAllowance(holidayTime);
//        double overTimeWorkAllowance = calculateOverTimeWorkAllowance(overTime);
//        double mealsAllowance = calculateMealsAllowance(svo.getMeals());
//
//        // 최종 급여 계산
//        double finalSalary = vo.getSalary() - pension - healthInsurance - svo.getLongCare() - employmentInsurance - svo.getIncomeTax() - localIncomeTax
//                + positionAllowance + bonusAllowance + holidayAllowance + overTimeWorkAllowance + mealsAllowance;
//
//        svo.setTotalSalary((int) finalSalary);
//
//        return dao.salaryWrite(svo);
//
//
//
//
//
//        return dao.salaryWrite(UserVo vo,SalaryVo svo,RatesVo rvo,double holidayTime,double overTime);
//    }
//
//    public int ratesWrite(RatesVo vo) {
//        return dao.ratesWrite(vo);
//    }
//}
