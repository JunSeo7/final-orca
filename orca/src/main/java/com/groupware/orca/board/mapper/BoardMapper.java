package com.groupware.orca.board.mapper;


import com.groupware.orca.board.vo.BoardVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM BOARD WHERE CATEGORY_NO=#{categoryNo}")
    List<BoardVo> getBoardList(String categoryNo);

    @Select("SELECT * FROM BOARD WHERE BOARD_NO = #{boardNo}")
    BoardVo getBoardDetail(String boardNo);

    @Insert("INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, CATEGORY_NO, INSERT_USER_NO, LATITUDE, LONGITUDE ) " +
            "VALUES (SEQ_BOARD.NEXTVAL, #{title}, #{content}, #{categoryNo}, #{insertUserNo}, #{latitude}, #{longitude})")
    int boardInsert(BoardVo vo);

    @Update("UPDATE BOARD SET TITLE = #{title}, CONTENT = #{content}, MODIFY_DATE = #{modifyDate}, DEL_YN = #{delYn}, " +
            "CATEGORY_NO = #{categoryNo}, INSERT_USER_NO = #{insertUserNo}, LATITUDE = #{latitude}, LONGITUDE = #{longitude} WHERE BOARD_NO = #{boardNo}")
    int boardUpdate(BoardVo vo);

    @Delete("DELETE FROM BOARD WHERE BOARD_NO = #{boardNo}")
    int boardDelete(String boardNo);

    @Select("SELECT * FROM BOARD WHERE TITLE LIKE CONCAT('%', #{keyword}, '%')")
    List<BoardVo> searchBoard(@Param("keyword") String keyword);

    @Update("UPDATE BOARD SET HIT = HIT + 1 WHERE BOARD_NO = #{boardNo}")
    void hit(@Param("boardNo") String boardNo);
}
