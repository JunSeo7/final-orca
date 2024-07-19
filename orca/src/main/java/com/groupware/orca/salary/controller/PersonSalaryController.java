package com.groupware.orca.salary.controller;

import com.groupware.orca.salary.service.PersonSalaryService;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/salary")
public class PersonSalaryController {

    private final PersonSalaryService service;
    private final HttpSession httpSession;

    //급여 상세조회
    @GetMapping("person/detail")
    public SalaryVo getPersonSalaryByNo(@RequestParam("payrollNo") String payrollNo, @RequestParam("empNo") String empNo) {
        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");
        System.out.println("userVo = " + userVo);
        SalaryVo vo = service.getPersonSalaryByNo(payrollNo, empNo, userVo);
        return vo;
    }


    //급여 목록조회
    @GetMapping("person/list")
    public List<SalaryVo> getPersonSalaryList(@RequestParam("empNo") String empNo) {

        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");
        System.out.println("userVo = " + userVo);
        List<SalaryVo> voList = service.getPersonSalaryList(empNo, userVo);
        return voList;
    }
}
