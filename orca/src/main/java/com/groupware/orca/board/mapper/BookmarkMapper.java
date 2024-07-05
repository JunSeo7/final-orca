package com.groupware.orca.board.mapper;

import com.groupware.orca.board.vo.BookmarkVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookmarkMapper {

    @Select("SELECT BM.*, B.TITLE " +
            "FROM BOARD_BOOKMARK BM " +
            "JOIN BOARD B ON B.BOARD_NO = BM.BOARD_NO " +
            "WHERE BM.EMP_NO = #{empNo}")
    List<BookmarkVo> getBookmarksByEmpNo(int empNo);


    @Insert("INSERT INTO BOARD_BOOKMARK (BOOKMARK_NO, BOARD_NO, EMP_NO) " +
            "VALUES (SEQ_BOARD_BOOKMARK.NEXTVAL, #{boardNo}, #{empNo})")
    @Options(useGeneratedKeys = true, keyProperty = "bookmarkNo", keyColumn = "BOOKMARK_NO")
    int insertBookmark(BookmarkVo bookmarkVo);

    @Delete("DELETE FROM BOARD_BOOKMARK WHERE BOARD_NO = #{boardNo} AND EMP_NO = #{empNo}")
    int deleteBookmarkByBoardNoAndEmpNo(@Param("boardNo") int boardNo, @Param("empNo") int empNo);
}
