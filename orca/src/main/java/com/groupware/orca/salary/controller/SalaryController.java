package com.groupware.orca.salary.controller;


import com.groupware.orca.salary.service.SalaryService;
import com.groupware.orca.salary.vo.ClientVo;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orca/salary")
public class SalaryController {

    private final SalaryService service;
    private final HttpSession httpSession;


    //급여계산 입력
    @PostMapping("write")
    public int salaryWrite(UserVo vo, ClientVo clientVo, SalaryVo svo){
        vo = (UserVo) httpSession.getAttribute("loginUserVo");
        int result = service.salaryWrite(vo,clientVo,svo);
        return result;
        //TODO 화면에서 자녀수에 따른 공제내역 사진 띄우고 적게 하기, param은 다 금액으로 입력받게 하기
    }

    //급여계산 수정
    @PostMapping("edit")
    public int salaryUpdate(ClientVo clientVo,UserVo vo,SalaryVo svo){
        vo = (UserVo) httpSession.getAttribute("loginUserVo");
        int result = service.salaryUpdate(vo,clientVo,svo);
        return result;
    }



    //급여 목록조회
    @GetMapping("list")
    public List<SalaryVo> getSalaryList(){
        UserVo vo = (UserVo) httpSession.getAttribute("loginUserVo");
        List<SalaryVo> voList = service.getSalaryList(vo);
        return voList;
    }



    //상세조회
    @GetMapping("detail")
    public SalaryVo getSalaryByNo(@RequestParam("payrollNo") String payrollNo){
        UserVo vo = (UserVo) httpSession.getAttribute("loginUserVo");
        SalaryVo svo = service.getSalaryByNo(payrollNo,vo);
        System.out.println("vo = " + vo);
        return svo;
    }


    //급여 삭제
    @DeleteMapping("delete")
    public int getSalaryDelete( @RequestParam("payrollNo")String payrollNo){
        UserVo vo = (UserVo) httpSession.getAttribute("loginUserVo");
        int result = service.getSalaryDelete(payrollNo,vo);
        return result;
    }

    //급여 검색
    @GetMapping("search")
    public List<SalaryVo> searchSalary(@RequestParam("empNo") String empNo){
        UserVo vo = (UserVo) httpSession.getAttribute("loginUserVo");
        List<SalaryVo> voList = service.searchSalary(empNo,vo);

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
    public RatesVo ratesByOne(){
        UserVo vo = (UserVo) httpSession.getAttribute("loginUserVo");
        RatesVo rvo = service.getRatesByOne(vo);

        return rvo;
    }

    //4대보험 요율 수정
    @PostMapping("ratesEdit")
    public int ratesEdit(RatesVo rvo){
        UserVo vo = (UserVo) httpSession.getAttribute("loginUserVo");
        System.out.println("rvo = " + rvo);
        int result = service.ratesEdit(rvo,vo);
        return result;
    }

    //4대보험 요율 삭제
    @PostMapping("ratesDelete")
    public int ratesDelete(@RequestParam("ratesNo") String ratesNo){
        int result = service.delete(ratesNo);

        return result;
    }

}
