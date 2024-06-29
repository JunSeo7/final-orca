package com.groupware.orca.document.service;

import com.groupware.orca.document.dao.DocumentDao;
import com.groupware.orca.document.vo.DocumentVo;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentDao dao;

    // 결재 작성 화면
    public List<TemplateVo> getTemplateList() {
        return dao.getTemplateList();
    }
    //템플릿 가져오기
    public TemplateVo getTemplateContent(int templateNo) {
        return dao.getTemplateContent(templateNo);
    }
    // 결재 작성
    public int writeDocument(DocumentVo vo) {
        return dao.writeDocument(vo);
    }

    //전체목록
    public List<DocumentVo> getDocumentList() {
        return dao.getDocumentList();
    }


}
