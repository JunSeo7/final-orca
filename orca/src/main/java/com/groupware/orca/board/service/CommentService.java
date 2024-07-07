package com.groupware.orca.board.service;

import com.groupware.orca.board.mapper.CommentMapper;
import com.groupware.orca.board.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper mapper;

    public List<CommentVo> getCommentsByBoardNo(int boardNo) {
        List<CommentVo> comments = mapper.getCommentsByBoardNo(boardNo);
        for (CommentVo comment : comments) {
            if (comment.getIsAnonymous() == 'Y') {
                comment.setEmployeeName("***");
                comment.setTeamName(""); // 익명일 때 팀 이름은 표시하지 않음
            }
        }
        return comments;
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
