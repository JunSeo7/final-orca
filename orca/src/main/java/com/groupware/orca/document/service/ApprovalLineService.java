package com.groupware.orca.document.service;

import com.groupware.orca.document.dao.ApprovalLineDao;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.ApproverVo;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApprovalLineService {

    private final ApprovalLineDao dao;

    //기본 결재선 등록
    public int addApprLineTemplate(ApprovalLineVo approvalLineVo) {
       int result = dao.addApprLineTemplate(approvalLineVo);
       return result;
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
