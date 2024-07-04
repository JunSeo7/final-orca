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
    public String showCalendar(HttpSession httpSession){
        if(httpSession.getAttribute("loginUserVo") == null){
            return "redirect:/orca/user/login";
        }
        return "calendar/showCalendar";
    }

    @GetMapping("showCalendarBarContent")
    @ResponseBody
    public List<CalendarVo> showCalendarBarContent(@RequestParam("range") String range, HttpSession httpSession){
        UserVo userVo = (UserVo)httpSession.getAttribute("loginUserVo");
        System.out.println("userVo = " + userVo);
        List<CalendarVo> calendarBarlist = service.showCalendarBarContent(range, userVo);
        System.out.println("사이즈 : " + calendarBarlist.size());
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
    public int deleteCalendar(@RequestParam("calendarNo") int calendarNo, HttpSession httpSession){
        String writerNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        int result = service.deleteCalendar(calendarNo, writerNo);
        return result;
    }

    @PostMapping("editCalendar")
    @ResponseBody
    public int editCalendar(@RequestBody CalendarVo vo, HttpSession httpSession){
        System.out.println("vo = " + vo);
        String writerNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        int result = service.editCalendar(vo, writerNo);
        return result;
    }


}
