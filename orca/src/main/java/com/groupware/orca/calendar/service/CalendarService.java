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

    public List<CalendarVo> showCalendarBar(String range) {
        System.out.println("서비스 넘어옴");
       return dao.showCalendarBar(range);
    }
}
