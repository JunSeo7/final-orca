package com.groupware.orca.salary.controller;

import com.groupware.orca.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca")
public class SalaryPageController {

    private final SalaryService service;

    //회계팀 메인 화면
    @GetMapping("main")
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



}
