package com.groupware.orca.docTemplate.service;

import com.groupware.orca.docTemplate.dao.TemplateDao;
import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import com.groupware.orca.docTemplate.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateService {

    private final TemplateDao dao;



    public int addTemplate(TemplateVo vo) {
        return dao.addTemplate(vo);
    }

    public List<TemplateVo> getTemplateList() {
        return dao.getTemplateList();
    }

    public List<TemplateVo> getsearchTemplateList(TemplateVo vo) {
        return dao.getsearchTemplateList(vo);
    }

    public TemplateVo getTemplateDetail(String templateNo) {
        // Template 정보 가져오기
        TemplateVo templateVo = dao.templateDetail(templateNo);

        // ApproverLine 정보 가져오기
        if (templateVo != null) {
            List<ApprovalLineVo> approvalLineVoList = dao.selectApproverLineVo(templateVo.getApprLineNo());
            templateVo.setApprLineList(approvalLineVoList);
        }
        return templateVo;
    }



    public int editTemplate(TemplateVo vo) {
        return dao.editTemplate(vo);
    }

    public int deleteTemplate(int templateNo) {
        return dao.deleteTemplate(templateNo);
    }



}
