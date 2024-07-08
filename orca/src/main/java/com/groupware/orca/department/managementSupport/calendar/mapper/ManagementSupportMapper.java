package com.groupware.orca.department.managementSupport.calendar.mapper;


import com.groupware.orca.calendar.vo.CalendarVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagementSupportMapper {
    @Insert("INSERT INTO CALENDAR(CALENDAR_NO, WRITER_NO, TITLE, CONTENT, START_DATE, END_DATE, RANGE) \n" +
            "VALUES(SEQ_CALENDAR_NO.NEXTVAL, #{writerNo}, #{title}, #{content}, #{startDate}, #{endDate}, 'company')")
    int createCalendarCompany(CalendarVo vo);
}
