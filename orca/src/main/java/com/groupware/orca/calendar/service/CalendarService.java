package com.groupware.orca.calendar.service;

import com.groupware.orca.calendar.dao.CalendarDao;
import com.groupware.orca.calendar.vo.CalendarVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarService {
    private final CalendarDao dao;

    public int createCalendar(CalendarVo vo) {
        return dao.createCalendar(vo);
    }

    public List<CalendarVo> showCalendarBarContent(String range) {
       return dao.showCalendarBarContent(range);
    }

    public int deleteCalendar(int calendarNo) {
        return dao.deleteCalendar(calendarNo);
    }

    public int editCalendar(CalendarVo vo) {
        return dao.editCalendar(vo);
    }
}
