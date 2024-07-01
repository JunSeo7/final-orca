package com.groupware.orca.document.service;

import com.groupware.orca.document.dao.DocumentDao;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.DocumentVo;
import com.groupware.orca.document.vo.ReferencerVo;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentDao dao;

//    // 결재 작성 화면
//    public List<TemplateVo> getTemplateList() {
//        return dao.getTemplateList();
//    }

    // 결재 작성 템플릿 카테고리 가져오기
    public List<TemplateVo> getCategory() {
        return dao.getCategory();
    }
    // 결재 작성 카테고리번호 - 결재양식 제목 가져오기
    public List<TemplateVo> getTemplateByCategoryNo(int categoryNo) {
        return dao.getTemplateByCategoryNo(categoryNo);
    }
    // 결재 작성 템플릿 내용 가져오기
    public TemplateVo getTemplateContent(int templateNo) {
        return dao.getTemplateContent(templateNo);
    }

    // 결재 작성
    public int writeDocument(DocumentVo vo) {
        return dao.writeDocument(vo);
    }

    //전체목록
    public List<DocumentVo> getDocumentList() {
        List<DocumentVo> documentList = dao.getDocumentList();
        System.out.println("documentList = " + documentList);
        for (DocumentVo document : documentList) {
            int docNo = document.getDocNo();
            List<ApprovalLineVo> apprLineList = dao.getApprovalLineList(docNo);
            System.out.println("apprLineList = " + apprLineList);
            List<ReferencerVo> references = dao.getReferencerList(docNo);
            System.out.println("references = " + references);
            document.setApprovalLineVoList(apprLineList);
            document.setReferencerVoList(references);
        }
        return documentList;
    }


}
