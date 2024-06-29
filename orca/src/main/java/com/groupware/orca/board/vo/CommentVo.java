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
    private List<CommentVo> replies;
}
