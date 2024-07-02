//package com.groupware.orca.salary.controller;
//
//import com.groupware.orca.salary.service.SalaryService;
//import com.groupware.orca.salary.vo.RatesVo;
//import com.groupware.orca.salary.vo.SalaryVo;
//import com.groupware.orca.user.vo.UserVo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/orca/salary")
//public class SalaryController {
//
//    private final SalaryService service;
//
//    //급여계산 입력
//    @GetMapping("write")
//    public double salaryInsert(@RequestParam("sal"+"holidayTime"+"overTime") UserVo vo, SalaryVo svo, RatesVo rvo, double holidayTime, double overTime){
//        double result = service.salaryWrite(vo,svo,rvo,holidayTime,overTime);
//        return result;
//
//    }
//
//    //4대보험 입력
//    @GetMapping("ratesInsert")
//    public int ratesInsert(RatesVo vo){
//        int result = service.ratesWrite(vo);
//        return result;
//    }
//
//    //급여계산 수정
//
//    //4대보험 수정
//
//    //목록조회
//
//    //상세조회
//
//    //검색
//
//    //퇴직금 지급
//
//    //목록조회
//
//    //상세조회
//
//}
