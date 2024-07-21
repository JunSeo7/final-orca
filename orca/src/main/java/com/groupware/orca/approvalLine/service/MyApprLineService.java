package com.groupware.orca.approvalLine.service;

import com.groupware.orca.approvalLine.dao.MyApprLineDao;
import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import com.groupware.orca.approvalLine.vo.ApproverVo;
import com.groupware.orca.docTemplate.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyApprLineService {

    private final MyApprLineDao dao;

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

        int result = dao.insertApprovalLine(approvalLineVo);
        List<ApproverVo> approverList = approvalLineVo.getApproverVoList();
        if (approverList != null) {
            for (ApproverVo approver : approverList) {
                approver.setApprLineNo(approvalLineVo.getApprLineNo()); // 방금 만든 결재선 번호 사용
                result += dao.insertApprover(approver);
            }
        } return result;
    }

    // 결재선 전체목록 (결재선/결재자)
    public List<ApprovalLineVo> getApprovalLines(int loginUserNo) {
        List<ApprovalLineVo> approvalLines = dao.getApprovalLineList(loginUserNo);
        for (ApprovalLineVo line : approvalLines) {
            List<ApproverVo> approvers = dao.getApproverList(line.getApprLineNo());
            line.setApproverVoList(approvers);
        }
        return approvalLines;
    }
    // 결재선 삭제
    public void deleteApprLine(int apprLineNo, int loginUserNo) {
        dao.deleteApprLine(apprLineNo, loginUserNo);
    }

}
