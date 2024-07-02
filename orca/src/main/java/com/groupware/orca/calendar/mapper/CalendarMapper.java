package com.groupware.orca.calendar.mapper;

import com.groupware.orca.calendar.vo.CalendarVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CalendarMapper {

    @Insert("INSERT INTO CALENDAR(CALENDAR_NO, WRITER_NO, TITLE, CONTENT, START_DATE, END_DATE, RANGE) \n" +
            "VALUES(SEQ_CALENDAR_NO.NEXTVAL, #{writerNo}, #{title}, #{content}, #{startDate}, #{endDate}, #{range})")
    int createCalendar(CalendarVo vo);

    @Select("SELECT C.CALENDAR_NO, C.TITLE, C.CONTENT, TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, C.START_DATE, C.END_DATE, C.REPEAT_YN, C.RANGE, P.NAME AS WRITER, D.PARTNAME\n" +
            "FROM CALENDAR C\n" +
            "JOIN PERSONNEL_INFORMATION P ON C.WRITER_NO = P.EMP_NO\n" +
            "JOIN DEPARTMENT D ON D.DEPT_CODE = P.DEPT_CODE\n" +
            "WHERE RANGE = #{range}" +
            "ORDER BY ENROLL_DATE")
    List<CalendarVo> showCalendarBarContent(String range);

    @Delete("DELETE CALENDAR WHERE CALENDAR_NO = #{calendarNo}")
    int deleteCalendarEvent(int calendarNo);
}
