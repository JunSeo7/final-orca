package com.groupware.orca.calendar.mapper;

import com.groupware.orca.calendar.vo.CalendarVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CalendarMapper {

    @Insert("INSERT INTO CALENDAR(CALENDAR_NO, WRITER_NO, TITLE, CONTENT, START_DATE, END_DATE, REPEAT_YN, RANGE) \n" +
            "VALUES(SEQ_CALENDAR_NO.NEXTVAL, #{writerNo}, #{title}, #{content}, #{startDate}, #{endDate}, #{repeatYn}, #{range})")
    int createCalendar(CalendarVo vo);

    @Select("SELECT TITLE, START_DATE, END_DATE, REPEAT_YN, RANGE FROM CALENDAR WHERE RANGE = #{range}")
    List<CalendarVo> showCalendarBar(String range);
}
