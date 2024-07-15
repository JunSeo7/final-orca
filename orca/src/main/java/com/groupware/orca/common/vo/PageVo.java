package com.groupware.orca.common.vo;

import lombok.Data;

@Data
public class PageVo {
    private int page;             // 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈

    public PageVo(){
        this.page = 1;
    }

    public int getOffset() {
        return (page - 1) * recordSize;
    }

}
