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

    // 결재선 삭제
    public void deleteApprLine(int apprLineNo) {
        dao.deleteApprLine(apprLineNo);
    }

    // 결재선 - 승인처리, 반려처리
    public int updateStatusApprLine(ApproverVo vo) {
        int result = dao.updateStatusApprLine(vo);

        // 결재선 업데이트가 성공했을 경우 문서 상태를 확인 후 업데이트
        if (result > 0) {
            // 해당 문서의 모든 결재 상태를 가져옴
            List<ApproverVo> apprLines = dao.selectApprLineByDocNo(vo.getDocNo());

            boolean allApproved = true;
            boolean anyRejected = false;

            for (ApproverVo apprLine : apprLines) {
                if (apprLine.getApprovalStage() == 3) { // 반려
                    anyRejected = true;
                    System.out.println("반려 1개 있어서 반려로 넘어가여 = " + apprLine);
                    break;
                } else if (apprLine.getApprovalStage() != 2) { // 승인되지 않은 결재선이 있으면
                    allApproved = false;
                    System.out.println("승인안된거 있어서 넘어가여 안녕 = " + apprLine);
                }
            }

            // 문서 - 승인처리, 반려처리
            if (anyRejected) {
                // 한명이라도 반려했으면 문서 상태를 반려로 변경
                dao.updateDocumentStatus(vo.getDocNo(), 4); // 4: 반려
                System.out.println("종결 반려 anyRejected = " + anyRejected);
            } else if (allApproved) {
                // 모두 승인했으면 문서 상태를 승인으로 변경
                dao.updateDocumentStatus(vo.getDocNo(), 3); // 3: 종결
                System.out.println("종결 승인 anyRejected = " + anyRejected);
            }
        }

        return result;
    }



}
