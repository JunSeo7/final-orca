package com.groupware.orca.document.dao;

import com.groupware.orca.document.mapper.ApprovalLineMapper;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.ApproverVo;
import com.groupware.orca.document.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApprovalLineDao {

    private final ApprovalLineMapper mapper;

    // 결재선 등록 조직도 가져오기
    public List<UserVo> getUsers() {
        return mapper.getUsers();
    }
    // 결재선 등록 템플릿 카테고리 가져오기
    public List<TemplateVo> getCategory() {
        return mapper.getCategory();
    }

    // 결재선 등록 카테고리번호 - 결재양식 제목 가져오기
    public List<TemplateVo> getTemplateByCategoryNo(int categoryNo) {
        return mapper.getTemplateByCategoryNo(categoryNo);
    }

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
        System.out.println("approvalLines = " + approvalLines);
        return approvalLines;
    }

    // 결재선 전체목록 (결재자 여러명)
    public List<ApproverVo> getApproverList(int apprLineNo) {
        List<ApproverVo> approvers = mapper.getApproverList(apprLineNo);
        System.out.println("approvers = " + approvers);
        if(approvers ==null){
            System.out.println("approverLineVoList= " + approvers);
    } return approvers;
  }

    // 결재선 - 승인처리, 반려처리
    public int updateStatusApprLine(ApproverVo vo) {
        return mapper.updateStatusApprLine(vo);
    }
    public List<ApproverVo> getApprovalLinesByDocNo(int docNo) {
        return mapper.getApprovalLinesByDocNo(docNo);
    }
    // 문서 - 승인처리, 반려처리
    public int updateStatusDocument(int docNo, int status) {
        return mapper.updateStatusDocument(docNo, status);
    }

    // 결재선 삭제
    public void deleteApprLine(int apprLineNo) {
        mapper.deleteApprLine(apprLineNo);
    }



}
