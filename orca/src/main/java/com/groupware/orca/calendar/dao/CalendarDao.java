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

    public List<CalendarVo> showCalendarBar(String range) {
        System.out.println("다오 넘어옴");
        System.out.println("rangeDao= " + range);
        List<CalendarVo> voList = mapper.showCalendarBar(range);
        System.out.println(voList.size());
        return voList;
    }
}
