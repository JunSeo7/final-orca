package com.groupware.orca.department.managementSupport.calendar.dao;

import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.department.managementSupport.calendar.mapper.ManagementSupportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ManagementSupportDao {

    private final ManagementSupportMapper mapper;

    public int createCalendarCompany(CalendarVo vo) {
        return mapper.createCalendarCompany(vo);
    }

    public int getCalendarCnt() {
        return mapper.getCalendarCnt();
    }

    public List<CalendarVo> listCalendarData(int startNum, int endNum) {
        return mapper.listCalendarData(startNum, endNum);
    }

    public CalendarVo getCalendarByOne(int calendarNo) {
        return mapper.getCalendarByOne(calendarNo);
    }

    public int editCalendar(CalendarVo vo) {
        return mapper.editCalendar(vo);
    }

    public int deleteCalendar(int calendarNo) {
        return mapper.deleteCalendar(calendarNo);
    }
}
