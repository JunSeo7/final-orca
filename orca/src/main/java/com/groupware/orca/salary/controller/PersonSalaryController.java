package com.groupware.orca.salary.controller;

import com.groupware.orca.salary.service.PersonSalaryService;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
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
    private final HttpSession httpSession;

    //급여 목록조회
    @GetMapping("person/list")
    @ResponseBody
    public List<SalaryVo> getPersonSalaryList(@RequestParam("empNo") String empNo){
        UserVo vo = (UserVo) httpSession.getAttribute("loginUserVo");
        List<SalaryVo> voList = service.getPersonSalaryList(empNo,vo);
        return voList;
    }
    //급여 상세조회
    @GetMapping("person/detail")
    @ResponseBody
    public SalaryVo getPersonSalaryList(@RequestParam("payrollNo") String payrollNo,@RequestParam("empNo") String empNo){
        UserVo vo = (UserVo) httpSession.getAttribute("loginUserVo");
        SalaryVo svo = service.getPersonSalaryByOne(payrollNo,empNo,vo);
        return svo;
    }


}
