package com.groupware.orca.document.dao;

import com.groupware.orca.document.mapper.ApprovalLineMapper;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApprovalLineDao {

    private final ApprovalLineMapper mapper;

    //기본 결재선 등록
    public void addApprLineTemplate(ApprovalLineVo approvalLineVo) {
        mapper.addApprLineTemplate(approvalLineVo);
    }


    // 결재선 전체목록 (양식)
    public List<TemplateVo> getTemplateList() {
        List<TemplateVo> templates = mapper.getTemplateList();
        System.out.println("templates = " + templates);
        return templates;
    }

    // 결재선 전체목록 (양식-결재라인)
    public List<ApprovalLineVo> getApprovalLineList() {
        // ApproverLine 정보 가져오기
        List<ApprovalLineVo> approvalLines = mapper.getApprovalLineList();
        System.out.println("approvalLines = " + approvalLines);
        if(approvalLines ==null){
            System.out.println("approverLineVoList nulllllllllll= " + approvalLines);
    } return approvalLines;
  }

    // 결재선 삭제
    public void deleteApprLine(int apprLineNo) {
        mapper.deleteApprLine(apprLineNo);
    }
}
