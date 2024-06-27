package com.groupware.orca.calendar.mapper;

import com.groupware.orca.calendar.vo.CalendarVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarMapper {

    @Insert("INSERT INTO CALENDAR(CALENDAR_NO, WRITER_NO, TITLE, CONTENT, START_DATE, END_DATE, REPEAT_YN, ENROLL_DATE, RANGE) \n" +
            "VALUES(SEQ_CALENDAR_NO.NEXTVAL, #{writerNo}, #{title}, #{content}, #{startDate}, #{endDate}, #{repeatYn}, SYSDATE, #{range})")
    int createCalendar(CalendarVo vo);
}
