package com.groupware.orca.salary.controller;

import com.groupware.orca.salary.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/salary")
public class SalaryPageController {

    private final SalaryService service;

    //회계팀 메인 화면
    @GetMapping("main")
    public String main(){
        return "salary/main";
    }


    //급여계산 입력 (화면)
    @GetMapping("write")
    public String salaryWrite(){
        return "salary/write";
    }

    //급여 목록조회 (화면)
    @GetMapping("list")
    public String salaryList(){
        return "salary/list";
    }

//    //급여 상세조회 (화면)
//    @GetMapping
//    public String salaryDetail(){
//        return "salary/detail";
//    }
//
//    //급여 수정 (화면)
//    @GetMapping("edit")
//    public String salaryEdit(){
//        return "salary/edit";
//    }
//
//    //급여 삭제 (화면)
//    @PostMapping("delete")
//    public String salaryDelete(){
//        return "salary/delete";
//    }



}
