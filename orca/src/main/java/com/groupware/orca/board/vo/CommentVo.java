package com.groupware.orca.board.vo;

import lombok.Data;

import java.util.List;

@Data
public class CommentVo {
    private int boardChatNo;
    private String content;
    private int boardNo;
    private int insertUserNo;
    private String enrollDate;
    private String employeeName;
    private char isAnonymous;
    private String teamName;
    private Integer replyCommentNo; // 부모 댓글 번호 (null일 수 있음)
    private List<CommentVo> replies; // 자식 댓글 리스트
}
