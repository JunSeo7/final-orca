package com.groupware.orca.calendar.dao;

import com.groupware.orca.calendar.mapper.CalendarMapper;
import com.groupware.orca.calendar.vo.CalendarVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CalendarDao {
    private final CalendarMapper mapper;

    public int createCalendar(CalendarVo vo) {
        return mapper.createCalendar(vo);
    }

    public List<CalendarVo> showCalendarBarContent(String range) {
        List<CalendarVo> voList = mapper.showCalendarBarContent(range);
        return voList;
    }


    public int deleteCalendarEvent(int calendarNo) {
        return mapper.deleteCalendarEvent(calendarNo);
    }
}
;