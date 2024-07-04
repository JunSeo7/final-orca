package com.groupware.orca.calendar.service;

import com.groupware.orca.calendar.dao.CalendarDao;
import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.user.vo.UserVo;
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

    public List<CalendarVo> showCalendarBarContent(String range, UserVo userVo) {
       return dao.showCalendarBarContent(range, userVo);
    }

    public int deleteCalendar(int calendarNo, String writerNo) {
        return dao.deleteCalendar(calendarNo, writerNo);
    }

    public int editCalendar(CalendarVo vo, String writerNo) {
        return dao.editCalendar(vo, writerNo);
    }
}
