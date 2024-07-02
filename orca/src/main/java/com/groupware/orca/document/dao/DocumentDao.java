package com.groupware.orca.document.dao;

import com.groupware.orca.document.mapper.DocumentMapper;
import com.groupware.orca.document.vo.*;
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
    // 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    public List<DocumentVo> getDocumentList(String loginUserNo) {
        return mapper.getDocumentList(loginUserNo);
    }
    // 문서목록 - 결재선 목록 넣기
    public List<ApprovalLineVo> getApprovalLineList(int docNo) {
        return mapper.getApprovalLineList(docNo);
    }
    // 문서목록 - 참조인 목록 조회
    public List<ReferencerVo> getReferencerList(int docNo) {
        return mapper.getReferencerList(docNo);
    }
    // 결재 문서 조회(카테고리, 양식, 기안자관련) - 기안자 no 추가 (params)
    public DocumentVo getDocumentByNo(int docNo) {
        return mapper.getDocumentByNo(docNo);
    }
    // 문서 - 결재선 목록 조회
    public List<ApprovalLineVo> getApprovalLineByNo(int docNo) {
        return mapper.getApprovalLineByNo(docNo);
    }
    // 문서 - 참조인 목록 조회
    public List<ReferencerVo> getReferencerByNo(int docNo) {
        return mapper.getReferencerByNo(docNo);
    }
    // 문서 - 파일 목록 조회
    public List<DocFileVo> getDocFileByNo (int docNo) {
        return mapper.getDocFileByNo(docNo);
    }

    // 결재 기안 철회(아무도 결재승인 안했을 경우 가능)
    public int deleteDocumentByNo(int docNo, String loginUserNo) {
        return mapper.deleteDocumentByNo(docNo, loginUserNo);
    }


}