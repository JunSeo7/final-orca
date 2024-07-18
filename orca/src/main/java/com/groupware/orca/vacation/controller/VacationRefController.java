package com.groupware.orca.vacation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("orca/vacationRef")
public class VacationRefController {

    @GetMapping("VCode")
    public String showVacationCode(){
        return "vacation/vacationCode/vacationCode";
    }

}
