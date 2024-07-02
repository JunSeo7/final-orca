package com.groupware.orca.document.service;

import com.groupware.orca.document.dao.DocumentDao;
import com.groupware.orca.document.vo.*;
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
    public List<DocumentVo> getDocumentList(String loginUserNo) {
        // 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
        List<DocumentVo> documentList = dao.getDocumentList(loginUserNo);
        System.out.println("documentList = " + documentList);
        for (DocumentVo document : documentList) {
            int docNo = document.getDocNo();
            // 문서목록 - 결재선 목록 넣기
            List<ApprovalLineVo> apprLineList = dao.getApprovalLineList(docNo);
            System.out.println("apprLineList = " + apprLineList);
            document.setApprovalLineVoList(apprLineList);
            // 문서목록 - 참조인 목록 조회
            List<ReferencerVo> references = dao.getReferencerList(docNo);
            System.out.println("references = " + references);
            document.setReferencerVoList(references);
        }
        return documentList;
    }

    // 결재 상세보기 - 기안자 no 추가 (params)
    public DocumentVo getDocumentByNo(int docNo) {
        System.out.println("docNo = " + docNo);
        // 결재 문서 조회(카테고리, 양식, 기안자관련)
        DocumentVo documentVo = dao.getDocumentByNo(docNo);
        System.out.println("documentVo = " + documentVo);
        // 문서 - 결재선 목록 넣기
        List<ApprovalLineVo> apprLineList = dao.getApprovalLineByNo(docNo);
        System.out.println("apprLineList = " + apprLineList);
        documentVo.setApprovalLineVoList(apprLineList);
        // 문서 - 참조인 목록 넣기
        List<ReferencerVo> references = dao.getReferencerByNo(docNo);
        System.out.println("references = " + references);
        documentVo.setReferencerVoList(references);
        // 문서 - 파일 목록 넣기
        List<DocFileVo> DocFiles = dao.getDocFileByNo( docNo);
        documentVo.setFiles(DocFiles);
        return documentVo;
    }

    // 결재 기안 철회(아무도 결재승인 안했을 경우 가능)
    public int deleteDocumentByNo(int docNo, String String loginUserNo) {
        return dao.deleteDocumentByNo(docNo, loginUserNo);
    }
}