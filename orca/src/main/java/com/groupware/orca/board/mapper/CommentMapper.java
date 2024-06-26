package com.groupware.orca.board.mapper;

import com.groupware.orca.board.vo.CommentVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM BOARD_COMMENTS WHERE BOARD_NO = #{boardNo} AND PREVIOUS_COMMENT_NO IS NULL")
    List<CommentVo> getCommentsByBoardNo(@Param("boardNo") Long boardNo);

    @Select("SELECT * FROM BOARD_COMMENTS WHERE PREVIOUS_COMMENT_NO = #{previousCommentNo}")
    List<CommentVo> getRepliesByCommentNo(@Param("previousCommentNo") Long previousCommentNo);

    @Insert("INSERT INTO BOARD_COMMENTS(BOARD_CHAT_NO, CONTENT, TEAM , BOARD_NO, INSERT_USER_NO, PREVIOUS_COMMENT_NO, NOTICE_NO) " +
            "VALUES (SEQ_BOARD_COMMENTS.NEXTVAL, #{content}, #{team}, #{boardNo}, #{insertUserNo}, #{previousCommentNo}, #{noticeNo})")
    int insertComment(CommentVo commentVo);

    @Update("UPDATE BOARD_COMMENTS SET CONTENT = #{content}, HATE_COUNT = #{hateCount}, LIKE_COUNT = #{likeCount} WHERE BOARD_CHAT_NO = #{boardChatNo}")
    int updateComment(CommentVo commentVo);

    @Delete("DELETE FROM BOARD_COMMENTS WHERE BOARD_CHAT_NO = #{boardChatNo}")
    int deleteComment(@Param("boardChatNo") String boardChatNo);

}
