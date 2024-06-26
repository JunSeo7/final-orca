package com.groupware.orca.document.dao;

import com.groupware.orca.document.mapper.DocumentMapper;
import com.groupware.orca.document.vo.DocumentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DocumentDao {

    private final DocumentMapper mapper;

    //전체목록
    public List<DocumentVo> getDocumentList() {
        return mapper.getDocumentList();
    }
}
