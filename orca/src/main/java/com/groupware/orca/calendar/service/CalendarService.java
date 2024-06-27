package com.groupware.orca.calendar.service;

import com.groupware.orca.calendar.dao.CalendarDao;
import com.groupware.orca.calendar.vo.CalendarVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarDao dao;

    public int createCalendar(CalendarVo vo) {
        return dao.createCalendar(vo);
    }
}
