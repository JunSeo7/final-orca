package com.groupware.orca.calendar.dao;

import com.groupware.orca.calendar.mapper.CalendarMapper;
import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.user.vo.UserVo;
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

    public List<CalendarVo> showCalendarBarContent(String range, UserVo userVo) {
        List<CalendarVo> voList = mapper.showCalendarBarContent(range, userVo);
        return voList;
    }

    public int deleteCalendar(int calendarNo, String writerNo) {
        return mapper.deleteCalendar(calendarNo, writerNo);
    }

    public int editCalendar(CalendarVo vo) {
        return mapper.editCalendar(vo);
    }

    public List<CalendarVo> searchListCalendarData(String keyword, int startNum, int endNum) {
        return mapper.searchListCalendarData(keyword, startNum, endNum);
    }

    public int getSearchCalendarCnt(String keyword) {
        return mapper.getSearchCalendarCnt(keyword);
    }
}
