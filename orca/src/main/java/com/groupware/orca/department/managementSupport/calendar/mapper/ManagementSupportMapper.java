package com.groupware.orca.department.managementSupport.calendar.mapper;


import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.common.vo.Pagination;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManagementSupportMapper {
    @Insert("INSERT INTO CALENDAR(CALENDAR_NO, WRITER_NO, TITLE, CONTENT, START_DATE, END_DATE, RANGE) \n" +
            "VALUES(SEQ_CALENDAR_NO.NEXTVAL, #{writerNo}, #{title}, #{content}, #{startDate}, #{endDate}, 'company')")
    int createCalendarCompany(CalendarVo vo);

    @Select("SELECT COUNT(CALENDAR_NO) FROM CALENDAR WHERE DEL_DATE IS NULL AND RANGE = 'company' ")
    int getCalendarCnt();

    @Select("SELECT \n" +
            "    *\n" +
            "FROM\n" +
            "(\n" +
            "    SELECT \n" +
            "        ROWNUM AS RNUM,\n" +
            "        C.*\n" +
            "    FROM \n" +
            "    (\n" +
            "        SELECT \n" +
            "            C.CALENDAR_NO,\n" +
            "            C.TITLE,\n" +
            "            SUBSTR(C.CONTENT, 1, 30) AS CONTENT,\n" +
            "            TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE,\n" +
            "            C.START_DATE,\n" +
            "            C.END_DATE,\n" +
            "            P.NAME AS WRITER\n" +
            "        FROM CALENDAR C\n" +
            "        JOIN PERSONNEL_INFORMATION P ON C.WRITER_NO = P.EMP_NO\n" +
            "        WHERE \n" +
            "        C.RANGE = 'company' \n" +
            "        AND C.DEL_DATE IS NULL\n" +
            "        ORDER BY ENROLL_DATE DESC, CALENDAR_NO DESC\n" +
            "    ) C\n" +
            ")\n" +
            "WHERE RNUM BETWEEN #{startNum} AND #{endNum}")
    List<CalendarVo> listCalendarData(@Param("startNum") int startNum, @Param("endNum") int endNum);
}
