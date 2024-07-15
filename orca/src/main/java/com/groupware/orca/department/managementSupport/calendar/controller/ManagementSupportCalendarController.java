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

    @PostMapping("createCalendarCompany")
    @ResponseBody
    public int createCalendarCompany(CalendarVo vo, HttpSession httpSession) throws InvalidInputException {
        int writerNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
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
    public List<CalendarVo> listCalendarData(@RequestParam("startNum") int startNum, @RequestParam("endNum") int endNum) {
        List<CalendarVo> calendarVoList = service.listCalendarData(startNum, endNum);
        return calendarVoList;
    }

    @GetMapping("detailCalendar")
    public String detailCalendar() {
        return "managementSupport/calendar/company/detail";
    }

    @GetMapping("getCalendarByOne")
    @ResponseBody
    public CalendarVo getCalendarByOne(@RequestParam("calendarNo") int calendarNo) {
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
    public int deleteCalendar(@RequestParam("calendarNo") int calendarNo) {
        int result = service.deleteCalendar(calendarNo);
        return result;
    }

    @GetMapping("searchListCalendar")
    public String searchListCalendar() {
        System.out.println("요청 넘어옴");
        return "managementSupport/calendar/company/list";
    }

    @GetMapping("searchListCalendarPage")
    @ResponseBody
    public Pagination searchListCalendarPage(@RequestParam("page") int page, @RequestParam("keyword") String keyword) {
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setPageSize(10);
        pageVo.setRecordSize(10);
        if (keyword != null) {
            keyword = keyword.replaceAll("\\s+", "");
        }
        int totalRecordCount = service.getSearchCalendarCnt(keyword);
        Pagination pagination = new Pagination(totalRecordCount, pageVo);
        return pagination;
    }

    @GetMapping("searchListCalendarData")
    @ResponseBody
    public List<CalendarVo> searchListCalendarData(@RequestParam("startNum") int startNum,
                                                   @RequestParam("endNum") int endNum,
                                                   @RequestParam("keyword") String keyword) {
        if (keyword != null) {
            keyword = keyword.replaceAll("\\s+", "");
        }
        
        List<CalendarVo> calendarVoList = service.searchListCalendarData(keyword, startNum, endNum);
        return calendarVoList;
    }
}
