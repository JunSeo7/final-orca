package com.groupware.orca.board.mapper;


import com.groupware.orca.board.vo.BoardFileVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardFileMapper {

    @Select("SELECT * FROM BOARD_FILE WHERE BOARD_NO = #{boardNo}")
    List<BoardFileVo> getFilesByBoardNo(@Param("boardNo") Long boardNo);

    @Insert("INSERT INTO BOARD_FILE(FILE_NO, FILE_ORIGIN_NAME, FILE_SAVE_NAME, FILE_DEL_YN, BOARD_NO) " +
            "VALUES (SEQ_BOARD_FILE.NEXTVAL, #{fileOriginName}, #{fileSaveName}, #{fileDelYn}, #{boardNo})")
    @Options(useGeneratedKeys = true, keyProperty = "fileNo")
    int insertFile(BoardFileVo file);

    @Delete("DELETE FROM BOARD_FILE WHERE FILE_NO = #{fileNo}")
    int deleteFile(@Param("fileNo") Long fileNo);
}
