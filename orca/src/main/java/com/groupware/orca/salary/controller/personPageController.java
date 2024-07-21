package com.groupware.orca.salary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("orca")
public class personPageController {



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
