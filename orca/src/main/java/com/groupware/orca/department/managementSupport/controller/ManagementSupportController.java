package com.groupware.orca.department.managementSupport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("orca/managementSupport")
public class ManagementSupportController {

    @GetMapping("main")
    public String showHumanResourcesDepartmentMain(){
        return "managementSupport/main";
    }

    @GetMapping("writeCalendar")
    public String writeCalendarCompany(){
        return "managementSupport/calendar/company/write";
    }
}
