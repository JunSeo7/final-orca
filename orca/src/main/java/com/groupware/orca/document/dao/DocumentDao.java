package com.groupware.orca.document.dao;

import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import com.groupware.orca.approvalLine.vo.ApproverVo;
import com.groupware.orca.document.mapper.DocumentMapper;
import com.groupware.orca.document.vo.*;
import com.groupware.orca.docTemplate.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DocumentDao {

    private final DocumentMapper mapper;

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
    // 결재 작성 결재선 가져오기
    // 결재선 전체목록 (결재선)
    public ApprovalLineVo getTemplateApprLine(int templateNo) {
        ApprovalLineVo apprline = mapper.getTemplateApprLine(templateNo);
        return apprline;
    }
    // 결재선 전체목록 (결재자 여러명)
    public List<ApproverVo> getApproverList(int apprLineNo) {
        List<ApproverVo> approvers = mapper.getApproverList(apprLineNo);
        if(approvers ==null){
            System.out.println("approverLineVoList= " + approvers);
        } return approvers;
    }

    // 결재 작성
    @Transactional
    public int writeDocument(DocumentVo vo) {
        return mapper.writeDocument(vo);
    }

    // 결재 작성 - 결재선 업로드
    public int writeDocumentApprover(List<ApproverVo> voList){
        int result = 0 ;
        for (ApproverVo approver : voList) {
           result += mapper.writeDocumentApprover(approver);
        }
        return result;
    }
    // 결재 작성 - 참조인 업로드
    public int writeDocumentReferrer(List<ReferencerVo> voList){
        int result = 0 ;
        for (ReferencerVo referencer : voList) {
            result += mapper.writeDocumentReferrer(referencer);
        }
        return result;
    }
    // 결재 작성 - 파일 업로드
    public int writeDocumentFile(List<DocFileVo> voList){
        int result = 0 ;
        for (DocFileVo file : voList) {
            result += mapper.writeDocumentFile(file);
        }
        return result;
    }


    //전체목록
    // 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
    public List<DocumentVo> getDocumentList(String loginUserNo, int status) {
        return mapper.getDocumentList(loginUserNo, status);
    }

    //받은 결재
    public List<DocumentVo> getSendDocumentList(String loginUserNo) {
        return mapper.getSendDocumentList(loginUserNo);
    }

    // (공람) - 종결된 결재 중 참조인에 해당하는 사람에게 보임
    public List<DocumentVo> getPublicDocumentList(String loginUserNo) {
        return mapper.getPublicDocumentList(loginUserNo);
    }

    // 문서 상세보기
    // 결재 문서 조회(카테고리, 양식, 기안자관련) - 기안자 no 추가 (params)
    public DocumentVo getDocumentByNo(int docNo) {
        return mapper.getDocumentByNo(docNo);
    }
    // 문서 - 결재선 목록 조회
    public List<ApproverVo> getApprovalLineByNo(int docNo) {
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


    // 기안서 수정 (임시저장 상태일 경우만) // 제목, 내용, 상태(기안)만 수정가능
    public int editDocument(DocumentVo vo) {
        return mapper.editDocument(vo);

    }
    // 기안서 상태 수정 (임시저장 상태일 경우 - 기안으로 )
    public int updateStatusDocument(DocumentVo vo) {
        return mapper.updateStatusDocument(vo);
    }

    // 결재 기안 철회(아무도 결재승인 안했을 경우 가능)
    public int deleteDocumentByNo(int docNo, String loginUserNo) {
        return mapper.deleteDocumentByNo(docNo, loginUserNo);
    }



}