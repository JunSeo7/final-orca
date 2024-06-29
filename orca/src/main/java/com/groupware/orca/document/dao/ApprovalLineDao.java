package com.groupware.orca.document.dao;

import com.groupware.orca.document.mapper.ApprovalLineMapper;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.ApproverVo;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApprovalLineDao {

    private final ApprovalLineMapper mapper;

    // 기본 결재선 등록
    public void insertApprovalLine(ApprovalLineVo approvalLine) {
        System.out.println("ApprovalLineDao.insertApprovalLine");
        mapper.insertApprovalLine(approvalLine);
    }
    // 결재선 - 결재자 여러명 등록
    public void insertApprover(ApproverVo approver) {
        System.out.println("ApprovalLineDao.insertApprover");
        mapper.insertApprover(approver);
    }

    // 결재선 전체목록 (결재선)
    public List<ApprovalLineVo> getApprovalLineList() {
        List<ApprovalLineVo> approvalLines = mapper.getApprovalLineList();
        System.out.println("templates = " + approvalLines);
        return approvalLines;
    }

    // 결재선 전체목록 (결재자 여러명)
    public List<ApproverVo> getApproverList() {
        List<ApproverVo> approvers = mapper.getApproverList();
        System.out.println("approvalLines = " + approvers);
        if(approvers ==null){
            System.out.println("approverLineVoList nulllllllllll= " + approvers);
    } return approvers;
  }

    // 결재선 삭제
    public void deleteApprLine(int apprLineNo) {
        mapper.deleteApprLine(apprLineNo);
    }



}
