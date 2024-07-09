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

    private final ManagementSupportService service;

    @GetMapping("createCalendar")
    public String createCalendar(){
        return "managementSupport/calendar/company/create";
    }

    @PostMapping("createCalendarCompany")
    @ResponseBody
    public int createCalendarCompany(CalendarVo vo, HttpSession httpSession) throws InvalidInputException {
        String writerNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(writerNo);
        int result = service.createCalendarCompany(vo);

        return result;
    }

    @GetMapping("listCalendar")
    public String listCalendar() {
        return "managementSupport/calendar/company/list";
    }

    @GetMapping("listCalendarPage")
    @ResponseBody
    public Pagination listCalendarPage(@RequestParam("page") int page) {
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setPageSize(10);
        pageVo.setRecordSize(10);
        int totalRecordCount = service.getCalendarCnt();
        Pagination pagination = new Pagination(totalRecordCount, pageVo);
        return pagination;
    }

    @GetMapping("listCalendarData")
    @ResponseBody
    public List<CalendarVo> listCalendarData(@RequestParam("startNum") int startNum, @RequestParam("endNum") int endNum ) {
        List<CalendarVo> calendarVoList = service.listCalendarData(startNum, endNum);
        return calendarVoList;
    }

    @GetMapping("detailCalendar")
    public String detailCalendar(){
        return "managementSupport/calendar/company/detail";
    }

    @GetMapping("getCalendarByOne")
    @ResponseBody
    public CalendarVo getCalendarByOne(@RequestParam("calendarNo") int calendarNo){
        CalendarVo calendarVo = service.getCalendarByOne(calendarNo);

        return calendarVo;
    }

    @PostMapping("editCalendar")
    @ResponseBody
    public int editCalendar(@RequestBody CalendarVo vo) throws InvalidInputException {
        int result = service.editCalendar(vo);
        return result;
    }

    @PostMapping("deleteCalendar")
    @ResponseBody
    public int deleteCalendar(@RequestParam("calendarNo") int calendarNo){
        int result = service.deleteCalendar(calendarNo);
        return result;
    }
}
