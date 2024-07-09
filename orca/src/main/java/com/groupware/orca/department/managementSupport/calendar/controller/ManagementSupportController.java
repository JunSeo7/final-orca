package com.groupware.orca.department.managementSupport.calendar.controller;

import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.common.vo.PageVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.department.managementSupport.calendar.service.ManagementSupportService;
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
public class ManagementSupportController {

    private final ManagementSupportService serivce;

    @GetMapping("createCalendar")
    public String createCalendar(){
        return "managementSupport/calendar/company/create";
    }

    @PostMapping("createCalendarCompany")
    @ResponseBody
    public int createCalendarCompany(CalendarVo vo, HttpSession httpSession) throws InvalidInputException {
        String writerNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(writerNo);
        System.out.println(vo);
        int result = serivce.createCalendarCompany(vo);

        return result;
    }

    @GetMapping("listCalendar")
    public String listCalendar() {
        return "managementSupport/calendar/company/list";
    }

    @GetMapping("listCalendarPage")
    @ResponseBody
    public Pagination listCalendarPage(@RequestParam("page") int page) {
        System.out.println(page);
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setPageSize(10);
        pageVo.setRecordSize(10);
        int totalRecordCount = serivce.getCalendarCnt();
        Pagination pagination = new Pagination(totalRecordCount, pageVo);
        System.out.println("pageVo = " + pageVo);
        System.out.println("pagination = " + pagination);
        return pagination;
    }

    @GetMapping("listCalendarData")
    @ResponseBody
    public List<CalendarVo> listCalendarData(@RequestParam("startNum") int startNum, @RequestParam("endNum") int endNum ) {
        System.out.println("요청 넘어옴");
        System.out.println("startNum : " + startNum);
        System.out.println("endNum : " + endNum);
        List<CalendarVo> calendarVoList = serivce.listCalendarData(startNum, endNum);
        return calendarVoList;
    }


}
