package com.groupware.orca.salary.controller;

import com.groupware.orca.salary.service.SalaryService;
import com.groupware.orca.salary.vo.ClientVo;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca")
public class SalaryPageController {

    private final SalaryService service;

    //회계팀 메인 화면
    @GetMapping("salaryMain")
    public String main(){
        return "salary/main";
    }


    //급여계산 입력 (화면)
    @GetMapping("salaryWrite")
    public String write(){
        return "salary/write";
    }



    //급여 목록조회 (화면)
    @GetMapping("salaryList")
    public String list(){
        return "salary/list";
    }

    //급여 검색
    @GetMapping("salarySearch")
    public String searchSalary(){
        return "salary/search";

    }

    //급여 상세조회 (화면)
    @GetMapping("salaryDetail")
    public String detail(){

        return "salary/detail";
    }

    //급여 수정 (화면)
    @GetMapping("salaryEdit")
    public String edit(){
        return "salary/edit";
    }

    //급여 삭제 (화면)
    @PostMapping("salaryDelete")
    public String delete(){
        return "salary/delete";
    }


    //------------------------------------------

    //4대보험 상세조회
    @GetMapping("ratesByOne")
    public String ratesByOne(){

        return "salary/ratesByOne";
    }

    //4대보험 요율 수정
    @PutMapping("ratesEdit")
    public String ratesEdit(RatesVo rvo){

        return "salary/ratesEdit";
    }
//---------------------------------


    //개인 명세서 목록
    @GetMapping("personList")
    public String salaryPersonList(){
        return "salary/person/list";
    }

    //개인 명세서 상세
    @GetMapping("personDetail")
    public String salaryPersonDetail(){
        return "salary/person/detail";
    }



}
