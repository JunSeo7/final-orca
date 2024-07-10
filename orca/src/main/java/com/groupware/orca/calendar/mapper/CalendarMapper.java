package com.groupware.orca.calendar.mapper;

import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CalendarMapper {

    @Insert("INSERT INTO CALENDAR(CALENDAR_NO, WRITER_NO, TITLE, CONTENT, START_DATE, END_DATE, RANGE) \n" +
            "VALUES(SEQ_CALENDAR_NO.NEXTVAL, #{writerNo}, #{title}, #{content}, #{startDate}, #{endDate}, #{range})")
    int createCalendar(CalendarVo vo);

    @Select(
            """
                            <script>
                            SELECT 
                                C.CALENDAR_NO
                                , C.TITLE
                                , C.CONTENT
                                , TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE
                                , C.START_DATE
                                , C.END_DATE
                                , C.RANGE
                                , P.NAME AS WRITER
                                , C.WRITER_NO
                                , D.PARTNAME
                            FROM CALENDAR C
                            JOIN PERSONNEL_INFORMATION P ON C.WRITER_NO = P.EMP_NO
                            JOIN DEPARTMENT D ON D.DEPT_CODE = P.DEPT_CODE
                            WHERE RANGE = #{range}
                            AND DEL_DATE IS NULL
                            <if test='range == "individual"'>
                                AND C.WRITER_NO = #{vo.empNo}
                            </if>
                            <if test='range == "department"'>
                                AND D.PARTNAME = #{vo.partName}
                            </if>
                            ORDER BY ENROLL_DATE
                            </script>
                    """
    )
    List<CalendarVo> showCalendarBarContent(@Param("range") String range, @Param("vo") UserVo userVo);

    @Update("UPDATE CALENDAR SET DEL_DATE = SYSDATE WHERE CALENDAR_NO = #{calendarNo} AND WRITER_NO = #{writerNo}")
    int deleteCalendar(@Param("calendarNo") int calendarNo, @Param("writerNo") String writerNo);

    @Update({
            "<script>",
            "UPDATE CALENDAR",
            "<set>",
            "<if test='vo.title != null'>TITLE = #{vo.title},</if>",
            "<if test='vo.content != null'>CONTENT = #{vo.content},</if>",
            "<if test='vo.startDate != null'>START_DATE = #{vo.startDate},</if>",
            "<if test='vo.endDate != null'>END_DATE = #{vo.endDate},</if>",
            "<if test='vo.range != null'>RANGE = #{vo.range},</if>",
            "</set>",
            "WHERE CALENDAR_NO = #{vo.calendarNo}",
            "AND WRITER_NO = #{vo.writerNo}",
            "</script>"
    })
    int editCalendar(@Param("vo") CalendarVo vo);

    @Select("SELECT COUNT(C.CALENDAR_NO) " +
            "FROM CALENDAR C " +
            "JOIN PERSONNEL_INFORMATION P ON C.WRITER_NO = P.EMP_NO " +
            "WHERE C.DEL_DATE IS NULL " +
            "AND ( " +
            "    LOWER(REPLACE(C.TITLE ,' ','')) LIKE '%' || #{keyword} || '%' " +
            "    OR LOWER(REPLACE(C.CONTENT ,' ','')) LIKE '%' || #{keyword} || '%' " +
            "    OR LOWER(REPLACE(P.NAME ,' ','')) LIKE '%' || #{keyword} || '%' " +
            ")")
    int getSearchCalendarCnt(@Param("keyword") String keyword);

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
            "            C.RANGE,\n" +
            "            P.NAME AS WRITER\n" +
            "        FROM CALENDAR C\n" +
            "        JOIN PERSONNEL_INFORMATION P ON C.WRITER_NO = P.EMP_NO\n" +
            "        WHERE C.DEL_DATE IS NULL \n" +
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
}
