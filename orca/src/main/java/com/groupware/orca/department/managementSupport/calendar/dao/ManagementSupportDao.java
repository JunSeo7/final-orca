package com.groupware.orca.department.managementSupport.calendar.dao;

import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.department.managementSupport.calendar.mapper.ManagementSupportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ManagementSupportDao {

    private final ManagementSupportMapper mapper;

    public int createCalendarCompany(CalendarVo vo) {
        return mapper.createCalendarCompany(vo);
    }
}
