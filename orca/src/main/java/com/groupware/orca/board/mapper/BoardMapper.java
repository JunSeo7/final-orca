package com.groupware.orca.board.mapper;

import com.groupware.orca.board.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM BOARD WHERE CATEGORY_NO=#{categoryNo}")
    List<BoardVo> getBoardList(int categoryNo);

    @Select("SELECT B.*, TO_CHAR(B.ENROLL_DATE, 'YYYY-MM-DD HH24:MI:SS') AS ENROLL_DATE, " +
            "CASE WHEN B.IS_ANONYMOUS = 'Y' THEN '***' ELSE E.NAME END AS EMPLOYEE_NAME, " +
            "D.PARTNAME AS DEPARTMENT_NAME, " +
            "CASE WHEN B.IS_ANONYMOUS = 'Y' THEN '***' ELSE T.TEAM_NAME END AS TEAM_NAME " +
            "FROM BOARD B " +
            "JOIN PERSONNEL_INFORMATION E ON B.INSERT_USER_NO = E.EMP_NO " +
            "JOIN DEPARTMENT_TEAM T ON E.TEAM_CODE = T.TEAM_CODE " +
            "JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_CODE " +
            "WHERE B.BOARD_NO = #{boardNo}")
    BoardVo getBoardDetail(@Param("boardNo") int boardNo);


    @Insert("INSERT INTO BOARD (BOARD_NO, TITLE, CONTENT, CATEGORY_NO, INSERT_USER_NO, LATITUDE, LONGITUDE, IS_ANONYMOUS) " +
            "VALUES (SEQ_BOARD.NEXTVAL, #{title}, #{content}, #{categoryNo}, #{insertUserNo}, #{latitude}, #{longitude}, #{isAnonymous})")
    int boardInsert(BoardVo vo);

    @Update("UPDATE BOARD SET TITLE = #{title}, CONTENT = #{content}, IS_ANONYMOUS = #{isAnonymous} WHERE BOARD_NO = #{boardNo}")
    int boardUpdate(BoardVo vo);

    @Delete("DELETE FROM BOARD WHERE BOARD_NO = #{boardNo}")
    int boardDelete(int boardNo);

    @Select("SELECT * FROM BOARD WHERE TITLE LIKE '%' || #{title} || '%' AND CATEGORY_NO = #{categoryNo}")
    List<BoardVo> searchBoard(@Param("title") String title, @Param("categoryNo") int categoryNo);

    @Update("UPDATE BOARD SET HIT = HIT + 1 WHERE BOARD_NO = #{boardNo}")
    void hit(@Param("boardNo") int boardNo);

    @Select("SELECT TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE_STR, COUNT(*) AS POST_COUNT, SUM(HIT) AS VIEWS " +
            "FROM BOARD " +
            "GROUP BY TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD') " +
            "ORDER BY TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD')")
    List<Map<String, Object>> getStatsByDate();
}
