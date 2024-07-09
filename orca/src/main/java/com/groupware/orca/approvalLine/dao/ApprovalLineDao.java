package com.groupware.orca.approvalLine.dao;

import com.groupware.orca.approvalLine.mapper.ApprovalLineMapper;
import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import com.groupware.orca.approvalLine.vo.ApproverVo;
import com.groupware.orca.docTemplate.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

    // 기본 결재선 등록 전 - 기본 결재선 존재 여부 확인 - 0이여야 insert 될 수 있도록 함.
    public int countBasicApprovalLine(int templateNo){
        return mapper.countBasicApprovalLine(templateNo);
    }

    // 기본 결재선 등록
    public int insertApprovalLine(ApprovalLineVo approvalLine) {
        return mapper.insertApprovalLine(approvalLine);
    }
    // 결재선 - 결재자 여러명 등록
    public int insertApprover(ApproverVo approver) {
        return mapper.insertApprover(approver);
    }

    // 결재선 전체목록 (결재선)
    public List<ApprovalLineVo> getApprovalLineList() {
        List<ApprovalLineVo> approvalLines = mapper.getApprovalLineList();
        return approvalLines;
    }

    // 결재선 전체목록 (결재자 여러명)
    public List<ApproverVo> getApproverList(int apprLineNo) {
        List<ApproverVo> approvers = mapper.getApproverList(apprLineNo);
        if(approvers ==null){
    } return approvers;
  }

    // 결재선 삭제
    public int deleteApprLine(int apprLineNo) {
        return mapper.deleteApprLine(apprLineNo);
    }

    // 결재선 - 승인처리, 반려처리
    public int updateStatusApprLine(ApproverVo vo) {
        return mapper.updateStatusApprLine(vo);
    }

    // 문서 - 승인처리, 반려처리
    // 문서 상태 확인
    public List<ApproverVo> selectApprLineByDocNo(int docNo) {
        return mapper.selectApprLineByDocNo(docNo);
    }

    // 처리중....
    // 결재자가 모두 승인했을 경우 결재 문서 승인 (종결처리)
    // 결재자중 한명이라도 반려했을 경우 문서 반려 (종결처리)
    public int updateDocumentStatus(int docNo, int status) {
        return mapper.updateDocumentStatus(docNo, status);
    }
}