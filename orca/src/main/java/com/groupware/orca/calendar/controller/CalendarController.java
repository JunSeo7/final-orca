package com.groupware.orca.calendar.controller;

import com.groupware.orca.calendar.service.CalendarService;
import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/calendar")
public class CalendarController {
    private final CalendarService service;

    @GetMapping("showCalendar")
    public String showCalendar(){
        return "calendar/showCalendar";
    }

    @GetMapping("showCalendarBarContent")
    @ResponseBody
    public List<CalendarVo> showCalendarBarContent(@RequestParam("range") String range){
        List<CalendarVo> calendarBarlist = service.showCalendarBarContent(range);
        return calendarBarlist;
    }

    @PostMapping("createCalendar")
    @ResponseBody
    public int createCalendar(CalendarVo vo, HttpSession httpSession){
        System.out.println(vo);
        String writerNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(writerNo);
        int result = service.createCalendar(vo);

        return result;
    }

    @PostMapping("deleteCalendar")
    @ResponseBody
    public int deleteCalendar(@RequestParam("calendarNo") int calendarNo, Model model){
        int result = service.deleteCalendar(calendarNo);
        return result;
    }

    @PostMapping("editCalendar")
    @ResponseBody
    public int editCalendar(CalendarVo vo, HttpSession httpSession){
        int result = service.editCalendar(vo);
        return result;
    }
}
