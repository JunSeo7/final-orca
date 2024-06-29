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
    public void addApprovalLine(ApprovalLineVo approvalLineVo) {
        System.out.println("ApprovalLineService.addApprovalLine");
        dao.insertApprovalLine(approvalLineVo);

        List<ApproverVo> approverList = approvalLineVo.getApproverVoList();
        System.out.println("approverList = " + approverList);
        if (approverList != null) {
            for (ApproverVo approver : approverList) {
                System.out.println("approver = " + approver);
                approver.setApprLineNo(approvalLineVo.getApprLineNo()); // 방금 만든 결재선 번호 사용
                System.out.println("approvalLineVo.getApprLineNo() = " + approvalLineVo.getApprLineNo());
                dao.insertApprover(approver);
            }
        }
    }

//    // 결재선 전체목록 (결재선/결재자)
//    public List<ApprovalLineVo> getApprovalLines() {
//        List<ApprovalLineVo> approvalLines = dao.getApprovalLineList();
//        for (ApprovalLineVo line : approvalLines) {
//            List<ApproverVo> approvers = dao.getApproverList(line.getApprLineNo());
//            line.setApproverVoList(approvers);
//        }
//        return approvalLines;
//    }


    // 결재선 삭제
    public void deleteApprLine(int apprLineNo) {
        dao.deleteApprLine(apprLineNo);
    }


}
