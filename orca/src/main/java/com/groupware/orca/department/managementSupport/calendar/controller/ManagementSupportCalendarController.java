package com.groupware.orca.department.managementSupport.calendar.controller;

import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.common.vo.PageVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.department.managementSupport.calendar.service.ManagementSupportCalendarService;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("orca/managementSupport")
@RequiredArgsConstructor
public class ManagementSupportCalendarController {

    private final ManagementSupportCalendarService service;

    @GetMapping("createCalendar")
    public String createCalendar() {
        return "managementSupport/calendar/company/create";
    }

    @GetMapping("listCalendar")
    public String listCalendar() {
        return "managementSupport/calendar/company/list";
    }

    @GetMapping("detailCalendar")
    public String detailCalendar() {
        return "managementSupport/calendar/company/detail";
    }

    @GetMapping("searchListCalendar")
    public String searchListCalendar() {
        return "managementSupport/calendar/company/list";
    }


}
