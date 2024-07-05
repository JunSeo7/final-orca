package com.groupware.orca.salary.controller;

import com.groupware.orca.salary.service.SalaryService;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.salary.vo.SalaryWriteVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Session;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orca/salary")
public class SalaryController {

    private final SalaryService service;

    @GetMapping
    public void aa(){
        System.out.println("SalaryController.aa");
    }

    //급여계산 입력
    @PostMapping("write")
    public double salaryWrite(
            SalaryWriteVo vo
//            @RequestParam (value = "holidayTime" , required = false) double holidayTime,
//            @RequestParam (value = "overTime", required = false) double overTime,
//            @RequestParam (value = "person",required = false) double person,
//            @RequestParam (value = "position", required = false) double position,
//            @RequestParam (value = "empNo" ,required = false) String empNo
    )
    {

//        System.out.println("SalaryController.salaryWrite");
//        System.out.println("holidayTime: " + holidayTime);
//        System.out.println("overTime:" + overTime);
//        System.out.println("person:" + person);
//        System.out.println("position: " + position);
//        System.out.println("empNo: " + empNo);
//        System.out.println("result : 1");
//        double result = service.salaryWrite(empNo,holidayTime,overTime,person,position);
//        System.out.println("result : 2");
//        System.out.println("result : " + result);

        System.out.println("vo = " + vo);
        int result = service.salaryWrite(vo);

        return 0;
                //TODO 화면에서 자녀수에 따른 공제내역 사진 띄우고 적게 하기, param은 다 금액으로 입력받게 하기
    }

    //4대보험 입력
    @GetMapping("ratesInsert")
    public int ratesInsert(RatesVo vo){
        int result = service.ratesWrite(vo);

        return result;
    }

    //급여계산 수정

    //4대보험 수정

    //목록조회
    @GetMapping("list")
    public List<SalaryVo> getSalaryList(){

        System.out.println("SalaryController.getSalaryList");

        List<SalaryVo> voList = service.getSalaryList();


        return voList;
    }

    //상세조회

    //급여- 검색

    //퇴직 - 검색

    //퇴직금 지급

    //목록조회

    //상세조회

}
