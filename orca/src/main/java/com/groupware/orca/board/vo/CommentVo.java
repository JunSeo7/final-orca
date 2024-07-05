package com.groupware.orca.board.vo;

import lombok.Data;

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
    private Integer ReplyCommentNo; // 널처리 위해 인티저
}
