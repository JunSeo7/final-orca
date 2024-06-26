package com.groupware.orca.board.dao;

import com.groupware.orca.board.mapper.CommentMapper;
import com.groupware.orca.board.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDao {


    private final CommentMapper mapper;

    public List<CommentVo> getCommentsByBoardNo(Long boardNo) {
        return mapper.getCommentsByBoardNo(boardNo);
    }

    public List<CommentVo> getRepliesByCommentNo(Long previousCommentNo) {
        return mapper.getRepliesByCommentNo(previousCommentNo);
    }

    public int insertComment(CommentVo commentVo) {
        return mapper.insertComment(commentVo);
    }

    public int updateComment(CommentVo commentVo) {
        return mapper.updateComment(commentVo);
    }

    public int deleteComment(String boardChatNo) {
        return mapper.deleteComment(boardChatNo);
    }

}
