package com.groupware.orca.department.humanResources.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("orca/humanResources")
public class HumanResourcesController {

    @GetMapping("main")
    public String showHumanResourcesDepartmentMain(){
        return "humanResources/main";
    }

    @GetMapping("writeCalendar")
    public String writeCalendarCompany(){
        return "humanResources/calendar/company/write";
    }
}
