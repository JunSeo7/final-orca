package com.groupware.orca.document.service;

import com.groupware.orca.document.dao.ApprovalLineDao;
import com.groupware.orca.document.vo.ApprovalLineVo;
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

//    public List<ApprovalLineVo> list() {
//
//        return dao.list();
//    }

    public List<TemplateVo> getApprovalLines() {
        List<TemplateVo> templates = dao.getTemplateList();
        List<ApprovalLineVo> approvalLines = dao.getApprovalLineList();

        // TemplateVo의 Map을 만들어서 ApprLineNo를 키로 사용하여 빠르게 접근할 수 있도록 함
        Map<Integer, TemplateVo> templateMap = new HashMap<>();
        for (TemplateVo template : templates) {
            template.setApprLineList(new ArrayList<>()); // 빈 리스트로 초기화
            templateMap.put(template.getApprLineNo(), template);
        }

        // approvalLines를 순회하면서 각 결재선을 해당하는 TemplateVo에 추가
        for (ApprovalLineVo approvalLine : approvalLines) {
            TemplateVo template = templateMap.get(approvalLine.getApprLineNo());
            if (template != null) {
                template.getApprLineList().add(approvalLine);
            }
        }
        System.out.println("templateMap = " + templateMap);
        return new ArrayList<>(templateMap.values());

    }
}
