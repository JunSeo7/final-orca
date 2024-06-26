package com.groupware.orca.board.service;

import com.groupware.orca.board.dao.CommentDao;
import com.groupware.orca.board.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentDao dao;

    public List<CommentVo> getCommentsByBoardNo(Long boardNo) {
        return dao.getCommentsByBoardNo(boardNo);
    }

    public List<CommentVo> getRepliesByCommentNo(Long previousCommentNo) {
        return dao.getRepliesByCommentNo(previousCommentNo);
    }

    public int insertComment(CommentVo commentVo) {
        return dao.insertComment(commentVo);
    }

    public int updateComment(CommentVo commentVo) {
        return dao.updateComment(commentVo);
    }

    public int deleteComment(String boardChatNo) {
        return dao.deleteComment(boardChatNo);
    }


}
