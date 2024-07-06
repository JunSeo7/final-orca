package com.groupware.orca.document.service;

import com.groupware.orca.document.dao.ApprovalLineDao;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.ApproverVo;
import com.groupware.orca.document.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApprovalLineService {

    private final ApprovalLineDao dao;

    // 결재선 등록 조직도 가져오기
    public List<UserVo> getUsers() {
        return dao.getUsers();
    }

    // 결재선 등록 템플릿 카테고리 가져오기
    public List<TemplateVo> getCategory() {
        return dao.getCategory();
    }

    // 결재선 등록 카테고리번호 - 결재양식 제목 가져오기
    public List<TemplateVo> getTemplateByCategoryNo(int categoryNo) {
        return dao.getTemplateByCategoryNo(categoryNo);
    }

    //기본 결재선 등록
    @Transactional
    public int addApprovalLine(ApprovalLineVo approvalLineVo) {
        int result = 0;

        // 기본 결재선 등록 전 - 기본 결재선 존재 여부 확인 - 0이여야 insert 될 수 있도록 함.
        int count = dao.countBasicApprovalLine(approvalLineVo.getTemplateNo());
        if (count > 0) {
            throw new IllegalStateException("해당 양식에는 이미 기본 결재선이 존재합니다.");
        }

        result += dao.insertApprovalLine(approvalLineVo);
        List<ApproverVo> approverList = approvalLineVo.getApproverVoList();
        if (approverList != null) {
            for (ApproverVo approver : approverList) {
                approver.setApprLineNo(approvalLineVo.getApprLineNo()); // 방금 만든 결재선 번호 사용
                result += dao.insertApprover(approver);
            }
        } return result;
    }

    // 결재선 전체목록 (결재선/결재자)
    public List<ApprovalLineVo> getApprovalLines() {
        List<ApprovalLineVo> approvalLines = dao.getApprovalLineList();
        for (ApprovalLineVo line : approvalLines) {
            List<ApproverVo> approvers = dao.getApproverList(line.getApprLineNo());
            line.setApproverVoList(approvers);
        }
        return approvalLines;
    }

    // 결재선 - 승인처리, 반려처리
    public int updateStatusApprLine(ApproverVo vo) {
        return dao.updateStatusApprLine(vo);
    }
    public List<ApproverVo> getApprovalLinesByDocNo(int docNo) {
        return dao.getApprovalLinesByDocNo(docNo);
    }
    // 문서 - 승인처리, 반려처리
//    public int updateStatusDocument(int docNo, int status) {
//        return dao.updateStatusDocument(docNo, status);
//    }
    public void finalizeDocumentStatus(int docNo) {
        List<ApproverVo> approvers = getApprovalLinesByDocNo(docNo);

        // 모든 결재자 승인 확인
        boolean allApproved = true;
        // 1명이라도 반려 여부
        boolean anyRejected = false;

        // 결재선 상태 확인
        for (ApproverVo approver : approvers) {
            if (approver.getApprovalStage() == 2) {  // 반려(2)여부
                anyRejected = true;
                break;
            } else if (approver.getApprovalStage() != 3) {  // 모든 결재자 승인(3) 확인
                allApproved = false;
            }
        }

        if (anyRejected) {
            // 결재자 중 한 명이라도 반려했을 경우 문서 상태를 반려 상태(4)로 업데이트합니다.
            dao.updateStatusDocument(docNo, 4);  // Assuming 4 is the status for rejection
        } else if (allApproved) {
            // 모든 결재자가 승인했을 경우 문서 상태를 종결 상태(3)로 업데이트합니다.
            dao.updateStatusDocument(docNo, 3);  // Assuming 3 is the status for approval
        }
    }

    // 결재선 삭제
    public void deleteApprLine(int apprLineNo) {
        dao.deleteApprLine(apprLineNo);
    }
}
