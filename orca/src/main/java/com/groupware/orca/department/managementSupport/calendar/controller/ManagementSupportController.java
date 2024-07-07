package com.groupware.orca.department.managementSupport.calendar.controller;

import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.department.managementSupport.calendar.service.ManagementSupportService;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("orca/managementSupport")
@RequiredArgsConstructor
public class ManagementSupportController {

    private final ManagementSupportService serivce;

    @GetMapping("createCalendar")
    public String writeCalendarCompany(){
        return "managementSupport/calendar/company/create";
    }

    @PostMapping("createCalendarCompany")
    @ResponseBody
    public int writeCalendarCompany(CalendarVo vo, HttpSession httpSession) throws InvalidInputException {
        String writerNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(writerNo);
        System.out.println(vo);
        int result = serivce.writeCalendarCompany(vo);

        return result;
    }
}
