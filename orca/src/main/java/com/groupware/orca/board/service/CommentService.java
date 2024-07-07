package com.groupware.orca.board.service;

import com.groupware.orca.board.dao.CommentDao;
import com.groupware.orca.board.mapper.CommentMapper;
import com.groupware.orca.board.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;

    public List<CommentVo> getCommentsByBoardNo(int boardNo) {
        List<CommentVo> comments = commentDao.getCommentsByBoardNo(boardNo);
        for (CommentVo comment : comments) {
            if (comment.getIsAnonymous() == 'Y') {
                comment.setEmployeeName("***");
                comment.setTeamName(""); // 익명일 때 팀 이름은 표시하지 않음
            }
        }
        return comments;
    }


    public int insertComment(CommentVo commentVo) {
        return commentDao.insertComment(commentVo);
    }


    public int updateComment(CommentVo commentVo) {
        return commentDao.updateComment(commentVo);
    }


    public int deleteComment(int boardChatNo) {
        return commentDao.deleteComment(boardChatNo);
    }


    public int deleteCommentsByBoardNo(int boardNo) {
        return commentDao.deleteCommentsByBoardNo(boardNo);
    }

    public int getCategoryNoByBoardNo(int boardNo) {
        return commentDao.getCategoryNoByBoardNo(boardNo);
    }
}
