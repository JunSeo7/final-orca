package com.groupware.orca.salary.controller;

import com.groupware.orca.salary.service.PersonSalaryService;
import com.groupware.orca.salary.vo.SalaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/salary")
public class PersonSalaryController {

    private final PersonSalaryService service;

    @GetMapping("person/detail")
    @ResponseBody
    public SalaryVo getPersonSalaryList(@RequestParam("payrollNo") String payrollNo,@RequestParam("empNo") String empNo){
        SalaryVo vo = service.getPersonSalary(payrollNo,empNo);
        return vo;
    }

    //급여 목록조회
    @GetMapping("person/list")
    @ResponseBody
    public List<SalaryVo> getPersonSalaryList(@RequestParam("empNo") String empNo){
        List<SalaryVo> voList = service.getPersonSalaryList(empNo);
        return voList;
    }


}
