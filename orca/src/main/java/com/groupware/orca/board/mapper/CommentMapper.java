package com.groupware.orca.board.mapper;

import com.groupware.orca.board.vo.CommentVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT c.*, e.NAME AS EMPLOYEE_NAME, TO_CHAR(c.ENROLL_DATE, 'YYYY-MM-DD HH24:MI:SS') AS ENROLL_DATE " +
            "FROM BOARD_COMMENTS c " +
            "JOIN PERSONNEL_INFORMATION e ON c.INSERT_USER_NO = e.EMP_NO " +
            "WHERE c.BOARD_NO = #{boardNo}")
    List<CommentVo> getCommentsByBoardNo(@Param("boardNo") int boardNo);

    @Insert("INSERT INTO BOARD_COMMENTS(BOARD_CHAT_NO, CONTENT, BOARD_NO, INSERT_USER_NO, ENROLL_DATE) " +
            "VALUES (SEQ_BOARD_COMMENTS.NEXTVAL, #{content}, #{boardNo}, #{insertUserNo}, SYSDATE)")
    int insertComment(CommentVo commentVo);

    @Update("UPDATE BOARD_COMMENTS SET CONTENT = #{content}, MODIFY_DATE = SYSDATE WHERE BOARD_CHAT_NO = #{boardChatNo}")
    int updateComment(CommentVo commentVo);

    @Delete("DELETE FROM BOARD_COMMENTS WHERE BOARD_CHAT_NO = #{boardChatNo}")
    int deleteComment(@Param("boardChatNo") int boardChatNo);

    @Delete("DELETE FROM BOARD_COMMENTS WHERE BOARD_NO = #{boardNo}")
    int deleteCommentsByBoardNo(int boardNo);
}
