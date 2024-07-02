package com.groupware.orca.calendar.controller;

import com.groupware.orca.calendar.service.CalendarService;
import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
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

    @PostMapping("writeCalendar")
    public String createCalendar(CalendarVo vo, HttpSession httpSession, Model model){
        String writerNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(writerNo);
        int result = service.createCalendar(vo);
        if(result != 1){
            model.addAttribute("message", "다시 작성해주세요.");
            return "redirect:/orca/calendar/showCalendar";
        }
        return "calendar/showCalendar";
    }

    @PostMapping("deleteCalendarEvent")
    @ResponseBody
    public String deleteCalendarEvent(@RequestParam("calendarNo") int calendarNo, Model model){
        System.out.println(calendarNo);
        int result = service.deleteCalendarEvent(calendarNo);
        if(result != 1){
            model.addAttribute("message", "다시 작성해주세요.");
            return "redirect:/orca/calendar/showCalendar";
        }
        return "1";
    }
}
