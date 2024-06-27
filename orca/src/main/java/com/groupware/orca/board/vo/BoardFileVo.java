package com.groupware.orca.board.vo;

import lombok.Data;

@Data
public class BoardFileVo {
    private int  fileNo;
    private String fileOriginName;
    private String fileSaveName;
    private String fileDelYn;
    private int boardNo;
}
