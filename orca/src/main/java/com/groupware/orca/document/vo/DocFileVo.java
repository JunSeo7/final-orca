package com.groupware.orca.document.vo;

import lombok.Data;

import java.net.URL;

@Data
public class DocFileVo {
    private int fileNo;             // 파일 번호
    private int docNo;              // 문서 번호
    private String changeName;      // 변경된 파일 이름
    private String originName;      // 원본 파일 이름
    private URL fileUrl;         // 파일 경로
}
