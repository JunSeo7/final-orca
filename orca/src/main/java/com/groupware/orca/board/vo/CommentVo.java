package com.groupware.orca.board.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {
    private String boardChatNo;
    private String content;
    private String boardNo;
    private String insertUserNo;
    private String previousCommentNo;
    private String noticeNo;
}
