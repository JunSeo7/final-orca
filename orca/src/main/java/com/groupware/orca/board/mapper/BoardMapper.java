package com.groupware.orca.board.mapper;

import com.groupware.orca.board.vo.BoardVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM BOARD WHERE CATEGORY_NO=#{categoryNo}")
    List<BoardVo> getBoardList(int categoryNo);

    @Select("SELECT * FROM BOARD WHERE BOARD_NO = #{boardNo}")
    BoardVo getBoardDetail(int boardNo);

    @Insert("INSERT INTO BOARD (BOARD_NO, TITLE, CONTENT, CATEGORY_NO, INSERT_USER_NO, LATITUDE, LONGITUDE) " +
            "VALUES (SEQ_BOARD.NEXTVAL, #{title}, #{content}, #{categoryNo}, #{insertUserNo}, #{latitude}, #{longitude})")
    int boardInsert(BoardVo vo);

    @Update("UPDATE BOARD SET TITLE = #{title}, CONTENT = #{content} WHERE BOARD_NO = #{boardNo}")
    int boardUpdate(BoardVo vo);

    @Delete("DELETE FROM BOARD WHERE BOARD_NO = #{boardNo}")
    int boardDelete(int boardNo);

    @Select("SELECT * FROM BOARD WHERE TITLE LIKE '%' || #{title} || '%' AND CATEGORY_NO = #{categoryNo}")
    List<BoardVo> searchBoard(@Param("title") String title, @Param("categoryNo") int categoryNo);

    @Update("UPDATE BOARD SET HIT = HIT + 1 WHERE BOARD_NO = #{boardNo}")
    void hit(@Param("boardNo") int boardNo);
}
