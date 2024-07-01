package com.groupware.orca.board.service;

import com.groupware.orca.board.dao.CommentDao;
import com.groupware.orca.board.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao dao;

    public List<CommentVo> getCommentsByBoardNo(int boardNo) {
        List<CommentVo> comments = dao.getCommentsByBoardNo(boardNo);
        for (CommentVo comment : comments) {
            if (comment.getIsAnonymous() == 'Y') {
                comment.setEmployeeName("***");
                comment.setTeamName(""); // 익명일 때 팀 이름은 표시하지 않음
            }
        }
        return comments;
    }

    public int insertComment(CommentVo commentVo) {
        return dao.insertComment(commentVo);
    }

    public int updateComment(CommentVo commentVo) {
        return dao.updateComment(commentVo);
    }

    public int deleteComment(int boardChatNo) {
        return dao.deleteComment(boardChatNo);
    }

    public int deleteCommentsByBoardNo(int boardNo) {
        return dao.deleteCommentsByBoardNo(boardNo);
    }

    public int getCategoryNoByBoardNo(int boardNo) {
        return dao.getCategoryNoByBoardNo(boardNo);
    }
}
