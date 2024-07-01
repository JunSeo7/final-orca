package com.groupware.orca.board.dao;

import com.groupware.orca.board.mapper.CommentMapper;
import com.groupware.orca.board.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDao {

    private final CommentMapper mapper;

    public List<CommentVo> getCommentsByBoardNo(int boardNo) {
        return mapper.getCommentsByBoardNo(boardNo);
    }

    public int insertComment(CommentVo commentVo) {
        return mapper.insertComment(commentVo);
    }

    public int updateComment(CommentVo commentVo) {
        return mapper.updateComment(commentVo);
    }

    public int deleteComment(int boardChatNo) {
        return mapper.deleteComment(boardChatNo);
    }

    public int deleteCommentsByBoardNo(int boardNo) {
        return mapper.deleteCommentsByBoardNo(boardNo);
    }

    public int getCategoryNoByBoardNo(int boardNo) {
        return mapper.getCategoryNoByBoardNo(boardNo);
    }
}
