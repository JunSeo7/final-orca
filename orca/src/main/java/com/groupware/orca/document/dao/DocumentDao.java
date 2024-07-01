package com.groupware.orca.document.dao;

import com.groupware.orca.document.mapper.DocumentMapper;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.DocumentVo;
import com.groupware.orca.document.vo.ReferencerVo;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DocumentDao {

    private final DocumentMapper mapper;

//    // 결재 작성 화면
//    public List<TemplateVo> getTemplateList() {
//        return mapper.getTemplateList();
//    }

    // 결재 작성 템플릿 카테고리 가져오기
    public List<TemplateVo> getCategory() {
        return mapper.getCategory();
    }

    // 결재 작성 카테고리번호 - 결재양식 제목 가져오기
    public List<TemplateVo> getTemplateByCategoryNo(int categoryNo) {
        return mapper.getTemplateByCategoryNo(categoryNo);
    }
    //결재 작성 템플릿 내용 가져오기
    public TemplateVo getTemplateContent(int templateNo) {
        return mapper.getTemplateContent(templateNo);
    }

    // 결재 작성
    @Transactional
    public int writeDocument(DocumentVo vo) {
        return mapper.writeDocument(vo);
    }
    //전체목록
    public List<DocumentVo> getDocumentList() {
        return mapper.getDocumentList();
    }
    public List<ApprovalLineVo> getApprovalLineList(int docNo) {
        return mapper.getApprovalLineList(docNo);
    }
    public List<ReferencerVo> getReferencerList(int docNo) {
        return mapper.getReferencerList(docNo);
    }
}