package com.groupware.orca.document.service;

import com.groupware.orca.document.dao.DocumentDao;
import com.groupware.orca.document.vo.*;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 결재 작성 결재선 가져오기
    public ApprovalLineVo getTemplateApprLine(int templateNo) {
        ApprovalLineVo apprline = dao.getTemplateApprLine(templateNo);
        List<ApproverVo> approvers = dao.getApproverList(apprline.getApprLineNo());
        apprline.setApproverVoList(approvers);
        return apprline;
    }

    // 결재 작성
    @Transactional
    public int writeDocument(DocumentVo vo)  {
//          throws Exception
//        if (vo.getTitle().length() > 30) {
//            throw new InvalidInputException("글자수가 최대입니다. (제목은 30자 이내)");
//        }
//        if (vo.getContent().length() > 1000) {
//            throw new InvalidInputException("글자수가 최대입니다. (내용은 1000자 이내)");
//        }
        int result = 0;
        dao.writeDocument(vo);
        int docNo = vo.getDocNo();

        List<ApproverVo> approverList = vo.getApproverVoList();
        List<ReferencerVo> referencerList = vo.getReferencerVoList();
        List<DocFileVo> fileList = vo.getFileVoList();


        // 방금 만든 문서 번호 사용해서 결재선, 참조인, 파일 등록
        if (approverList != null) {
            for (ApproverVo approver : approverList) {
                approver.setDocNo(docNo);
            }
            int approverResult = dao.writeDocumentApprover(approverList);
            result += approverResult;
            System.out.println("결재선 등록 결과 = " + approverResult);
        }
        if (referencerList != null) {
            for (ReferencerVo referencer : referencerList) {
                referencer.setDocNo(docNo);
            }
            int referencerResult = dao.writeDocumentReferrer(referencerList);
            result += referencerResult;
            System.out.println("참조인 등록 결과 = " + referencerResult);
        }
        if (fileList != null) {
            for (DocFileVo file : fileList) {
                file.setDocNo(docNo);
            }
            int fileResult = dao.writeDocumentFile(fileList);
            result += fileResult;
            System.out.println("파일 등록 결과 = " + fileResult);
        }
        System.out.println("최종 결과 = " + result);
        return result;
    }


    //전체목록
    public List<DocumentVo> getDocumentList(String loginUserNo) {
        // 내가 작성한 결재 문서 목록 조회(카테고리, 양식, 기안자관련)
        List<DocumentVo> documentList = dao.getDocumentList(loginUserNo);
        for (DocumentVo document : documentList) {
            int docNo = document.getDocNo();
            // 문서목록 - 결재선 목록 넣기
            List<ApproverVo> approverList = dao.getApprovalLineByNo(docNo);
            document.setApproverVoList(approverList);
        }
        return documentList;
    }

    // (임시저장)
    public List<DocumentVo> getTempDocumentList(String loginUserNo) {
        // (임시저장) 내가 작성한 결재 문서 목록 조회
        List<DocumentVo> documentList = dao.getTempDocumentList(loginUserNo);
        for (DocumentVo document : documentList) {
            int docNo = document.getDocNo();
            // 문서목록 - 결재선 목록 넣기
            List<ApproverVo> approverList = dao.getApprovalLineByNo(docNo);
            document.setApproverVoList(approverList);
         }
        return documentList;
    }
    // (종결)
    public List<DocumentVo> getCloseDocumentList(String loginUserNo) {
        // (종결) 내가 작성한 결재 문서 목록 조회
        List<DocumentVo> documentList = dao.getCloseDocumentList(loginUserNo);
        for (DocumentVo document : documentList) {
            int docNo = document.getDocNo();
            // 문서목록 - 결재선 목록 넣기
            List<ApproverVo> approverList = dao.getApprovalLineByNo(docNo);
            document.setApproverVoList(approverList);
        }
        return documentList;
    }
    // (반려)
    public List<DocumentVo> getRetrunDocumentList(String loginUserNo) {
        // (반려) 내가 작성한 결재 문서 목록 조회
        List<DocumentVo> documentList = dao.getRetrunDocumentList(loginUserNo);
        for (DocumentVo document : documentList) {
            int docNo = document.getDocNo();
            // 문서목록 - 결재선 목록 넣기
            List<ApproverVo> approverList = dao.getApprovalLineByNo(docNo);
            document.setApproverVoList(approverList);
        }
        return documentList;
    }

    // (결재취소)
    public List<DocumentVo> getCancelDocumentList(String loginUserNo) {
            // (결재취소) 내가 작성한 결재 문서 목록 조회
            List<DocumentVo> documentList = dao.getCancelDocumentList(loginUserNo);
            for (DocumentVo document : documentList) {
                int docNo = document.getDocNo();
                // 문서목록 - 결재선 목록 넣기
                List<ApproverVo> approverList = dao.getApprovalLineByNo(docNo);
                document.setApproverVoList(approverList);
        }
        return documentList;
    }

    // (삭제함)
    public List<DocumentVo> getDeleteDocumentList(String loginUserNo) {
        // (삭제함) 내가 작성한 결재 문서 목록 조회
        List<DocumentVo> documentList = dao.getDeleteDocumentList(loginUserNo);
        for (DocumentVo document : documentList) {
            int docNo = document.getDocNo();
            // 문서목록 - 결재선 목록 넣기
            List<ApproverVo> approverList = dao.getApprovalLineByNo(docNo);
            document.setApproverVoList(approverList);
        }
        return documentList;
    }

    // 받은 결재
    public List<DocumentVo> getSendDocumentList(String loginUserNo) {
        List<DocumentVo> documentList = dao.getSendDocumentList(loginUserNo);
        for (DocumentVo document : documentList) {
            int docNo = document.getDocNo();
            // 문서목록 - 결재선 목록 넣기
            List<ApproverVo> approverList = dao.getApprovalLineByNo(docNo);
            document.setApproverVoList(approverList);
        }
        return documentList;
    }
    // (공람) - 종결된 결재 중 참조인에 해당하는 사람에게 보임
    public List<DocumentVo> getPublicDocumentList(String loginUserNo) {
        List<DocumentVo> documentList = dao.getPublicDocumentList(loginUserNo);
        for (DocumentVo document : documentList) {
            int docNo = document.getDocNo();
            // 문서목록 - 결재선 목록 넣기
            List<ApproverVo> approverList = dao.getApprovalLineByNo(docNo);
            document.setApproverVoList(approverList);
        }
        return documentList;
    }

    // 결재 상세보기 - 기안자 no 추가 (params)
    public DocumentVo getDocumentByNo(int docNo) {
        // 결재 문서 조회(카테고리, 양식, 기안자관련)
        DocumentVo documentVo = dao.getDocumentByNo(docNo);
        // 문서 - 결재선 목록 넣기
        List<ApproverVo> apprLineList = dao.getApprovalLineByNo(docNo);
        documentVo.setApproverVoList(apprLineList);
        // 문서 - 참조인 목록 넣기
        List<ReferencerVo> references = dao.getReferencerByNo(docNo);
        documentVo.setReferencerVoList(references);
        // 문서 - 파일 목록 넣기
        List<DocFileVo> DocFiles = dao.getDocFileByNo(docNo);
        documentVo.setFileVoList(DocFiles);
        return documentVo;
    }

    // 기안서 수정 (임시저장 상태일 경우만) // 제목, 내용, 상태(기안)만 수정가능
    public int editDocument(DocumentVo vo) {
        return dao.editDocument(vo);
    }
    // 기안서 상태 수정 (임시저장 상태일 경우 - 기안으로 )
    public int updateStatusDocument(DocumentVo vo) {
        return dao.updateStatusDocument(vo);
    }

    // 결재 기안 철회(아무도 결재승인 안했을 경우 가능)
    public int deleteDocumentByNo(int docNo,  String loginUserNo) {
        return dao.deleteDocumentByNo(docNo, loginUserNo);
    }
}