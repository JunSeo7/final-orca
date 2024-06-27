package com.groupware.orca.calendar.dao;

import com.groupware.orca.calendar.mapper.CalendarMapper;
import com.groupware.orca.calendar.vo.CalendarVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CalendarDao {
    private final CalendarMapper mapper;

    public int createCalendar(CalendarVo vo) {
        return mapper.createCalendar(vo);
    }
}
