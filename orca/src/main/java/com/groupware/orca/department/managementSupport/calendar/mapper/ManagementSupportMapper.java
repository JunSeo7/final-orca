package com.groupware.orca.department.managementSupport.calendar.mapper;


import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;

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

    @Select("SELECT \n" +
            "                        C.CALENDAR_NO\n" +
            "                        , C.TITLE\n" +
            "                        , C.CONTENT\n" +
            "                        , TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE\n" +
            "                        , C.START_DATE\n" +
            "                        , C.END_DATE\n" +
            "                        , C.RANGE\n" +
            "                        , P.NAME AS WRITER\n" +
            "                        , C.WRITER_NO\n" +
            "                        , D.PARTNAME\n" +
            "                    FROM CALENDAR C\n" +
            "                    JOIN PERSONNEL_INFORMATION P ON C.WRITER_NO = P.EMP_NO\n" +
            "                    JOIN DEPARTMENT D ON D.DEPT_CODE = P.DEPT_CODE\n" +
            "                    WHERE CALENDAR_NO = #{calendarNo}")
    CalendarVo getCalendarByOne(@Param("calendarNo") int calendarNo);

    @Update({
            "<script>",
            "UPDATE CALENDAR",
            "<set>",
            "<if test='vo.title != null'>TITLE = #{vo.title},</if>",
            "<if test='vo.content != null'>CONTENT = #{vo.content},</if>",
            "<if test='vo.startDate != null'>START_DATE = #{vo.startDate},</if>",
            "<if test='vo.endDate != null'>END_DATE = #{vo.endDate},</if>",
            "</set>",
            "WHERE CALENDAR_NO = #{vo.calendarNo}",
            "</script>"
    })
    int editCalendar(@Param("vo") CalendarVo vo);

    @Update("UPDATE CALENDAR SET DEL_DATE = SYSDATE WHERE CALENDAR_NO = #{calendarNo}")
    int deleteCalendar(@Param("calendarNo") int calendarNo);

    @Select("SELECT *\n" +
            "FROM (\n" +
            "    SELECT\n" +
            "        ROWNUM AS RNUM,\n" +
            "        C.*\n" +
            "    FROM (\n" +
            "        SELECT\n" +
            "            C.CALENDAR_NO,\n" +
            "            C.TITLE,\n" +
            "            SUBSTR(C.CONTENT, 1, 30) AS CONTENT,\n" +
            "            TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE,\n" +
            "            C.START_DATE,\n" +
            "            C.END_DATE,\n" +
            "            P.NAME AS WRITER\n" +
            "        FROM CALENDAR C\n" +
            "        JOIN PERSONNEL_INFORMATION P ON C.WRITER_NO = P.EMP_NO\n" +
            "        WHERE\n" +
            "            C.RANGE = 'company'\n" +
            "            AND C.DEL_DATE IS NULL\n" +
            "            AND (\n" +
            "                LOWER(REPLACE(C.TITLE ,' ','')) LIKE '%' || #{keyword} || '%'\n" +
            "                OR LOWER(REPLACE(C.CONTENT ,' ','')) LIKE '%' || #{keyword} || '%'\n" +
            "                OR LOWER(REPLACE(P.NAME ,' ','')) LIKE '%' || #{keyword} || '%'\n" +
            "            )\n" +
            "        ORDER BY ENROLL_DATE DESC, CALENDAR_NO DESC\n" +
            "    ) C\n" +
            ")\n" +
            "WHERE RNUM BETWEEN #{startNum} AND #{endNum}\n")
    List<CalendarVo> searchListCalendarData(@Param("keyword") String keyword, @Param("startNum") int startNum, @Param("endNum") int endNum);

    @Select("SELECT COUNT(C.CALENDAR_NO) " +
            "FROM CALENDAR C " +
            "JOIN PERSONNEL_INFORMATION P ON C.WRITER_NO = P.EMP_NO " +
            "WHERE C.DEL_DATE IS NULL AND C.RANGE = 'company' " +
            "AND ( " +
            "    LOWER(REPLACE(C.TITLE ,' ','')) LIKE '%' || #{keyword} || '%' " +
            "    OR LOWER(REPLACE(C.CONTENT ,' ','')) LIKE '%' || #{keyword} || '%' " +
            "    OR LOWER(REPLACE(P.NAME ,' ','')) LIKE '%' || #{keyword} || '%' " +
            ")")
    int getSearchCalendarCnt(@Param("keyword") String keyword);
}

