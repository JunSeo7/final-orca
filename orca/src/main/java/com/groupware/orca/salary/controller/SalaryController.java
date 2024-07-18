package com.groupware.orca.salary.controller;

import com.groupware.orca.common.vo.PageVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.salary.service.SalaryService;
import com.groupware.orca.salary.vo.ClientVo;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orca/salary")
public class SalaryController {

    private final SalaryService service;


    //급여계산 입력
    @PostMapping("write")
    public int salaryWrite(ClientVo clientVo, UserVo vo, SalaryVo svo) {
        int result = service.salaryWrite(clientVo,vo,svo);
        return result;
        //TODO 화면에서 자녀수에 따른 공제내역 사진 띄우고 적게 하기, param은 다 금액으로 입력받게 하기
    }

    //급여계산 수정
    @PostMapping("edit")
    public int salaryUpdate(ClientVo clientVo,UserVo vo,SalaryVo svo){
        int result = service.salaryUpdate(vo,clientVo,svo);

        return result;
    }



    //급여 목록조회
    @GetMapping("list")
    public List<SalaryVo> getSalaryList(){
        List<SalaryVo> voList = service.getSalaryList();
        return voList;
    }



    //상세조회
    @GetMapping("detail")
    public SalaryVo getSalaryByNo(@RequestParam("payrollNo") String payrollNo){
        SalaryVo vo = service.getSalaryByNo(payrollNo);
        System.out.println("vo = " + vo);
        return vo;
    }


    //급여 삭제
    @DeleteMapping("delete")
    public int getSalaryDelete( @RequestParam("payrollNo")String payrollNo){
        int result = service.getSalaryDelete(payrollNo);
        return result;
    }

    //급여 검색
    @GetMapping("search")
    public List<SalaryVo> searchSalary(@RequestParam("empNo") String empNo){
        List<SalaryVo> voList = service.searchSalary(empNo);

        return voList;

    }

    //------------------------------------------------------------------------------------


    //4대보험 입력
    @PostMapping("ratesInsert")
    public int ratesInsert(RatesVo vo){
        int result = service.ratesWrite(vo);

        return result;
    }

    //4대보험 싱세조회
    @GetMapping("ratesByOne")
    public RatesVo getRatesByOne(){
        RatesVo vo = service.getRatesByOne();

        return vo;
    }

    //4대보험 요율 수정
    @PostMapping("ratesEdit")
    public int ratesEdit(RatesVo rvo){
        System.out.println("rvo = " + rvo);
        int result = service.ratesEdit(rvo);
        return result;
    }

    //4대보험 요율 삭제
    @PostMapping("ratesDelete")
    public int ratesDelete(@RequestParam("ratesNo") String ratesNo){
        int result = service.delete(ratesNo);

        return result;
    }

}
