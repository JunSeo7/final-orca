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

    @Transactional
    public int addApprLineTemplate(ApprovalLineVo approvalLineVo) {
        System.out.println("approvalLineVo = " + approvalLineVo);
        // 결재선 등록
        mapper.addApprLineTemplate(approvalLineVo);

        // 생성된 결재선 번호 가져오기
        int apprLineNo = approvalLineVo.getApprLineNo();
        System.out.println("apprLineNo = " + apprLineNo);


        // 결재자 등록
        List<ApproverVo> approverList = approvalLineVo.getApproverVoList();
        int result = 0;
        for (ApproverVo approverVo : approverList) {
            approverVo.setApprLineNo(apprLineNo); // 결재선 번호 설정
            result = mapper.addApproverInfo(approverVo); // 결재자 정보 등록
        }
        return result;
    }

    // 결재선 전체목록 (결재선)
    public List<ApprovalLineVo> getApprovalLineList() {
        List<ApprovalLineVo> approvalLines = mapper.getApprovalLineList();
        System.out.println("templates = " + approvalLines);
        return approvalLines;
    }

    // 결재선 전체목록 (결재자 여러명)
    public List<ApproverVo> getApproverList() {
        // ApproverLine 정보 가져오기
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
