//package com.groupware.orca.salary.controller;
//
//import com.groupware.orca.salary.service.SalaryService;
//import com.groupware.orca.salary.vo.RatesVo;
//import com.groupware.orca.salary.vo.SalaryVo;
//import com.groupware.orca.user.vo.UserVo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/orca/salary")
//public class SalaryController {
//
//    private final SalaryService service;
//
//    @GetMapping
//    public void aa(){
//        System.out.println("SalaryController.aa");
//    }
//
//    //급여계산 입력
//    @PostMapping("write")
//    public double salaryWrite(
//            UserVo vo,
//            SalaryVo svo,
//            RatesVo rvo,
//            @RequestParam (value = "holidayTime" , required = false) double holidayTime,
//            @RequestParam (value = "overTime", required = false) double overTime,
//            @RequestParam (value = "person",required = false) double person,
//            @RequestParam (value = "position", required = false) double position,
//            @RequestParam (value = "bonus", required = false) double bonus,
//            @RequestParam(value = "meals", required = false) double meals){
//        System.out.println("SalaryController.salaryWrite");
//
//        double result = service.salaryWrite(vo,svo,rvo,holidayTime,overTime,person,position,bonus,meals);
//        return result;
//                //TODO 화면에서 자녀수에 따른 공제내역 사진 띄우고 적게 하기, param은 다 금액으로 입력받게 하기
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
//    @GetMapping("list")
//    public List<SalaryVo> getSalaryList(){
//
//        System.out.println("SalaryController.getSalaryList");
//
//        List<SalaryVo> voList = service.getSalaryList();
//
//
//        return voList;
//    }
//
//    //상세조회
//
//    //급여- 검색
//
//    //퇴직 - 검색
//
//    //퇴직금 지급
//
//    //목록조회
//
//    //상세조회
//
//}
