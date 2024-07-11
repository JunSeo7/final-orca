package com.groupware.orca.docTemplate.dao;

import com.groupware.orca.docTemplate.mapper.TemplateMapper;
import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import com.groupware.orca.docTemplate.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TemplateDao {

    private final TemplateMapper mapper;

    public int addTemplate(TemplateVo vo) {
        return mapper.addTemplate(vo);
    }

    public List<TemplateVo> getTemplateList() {
        return mapper.getTemplateList();
    }

    public List<TemplateVo> getsearchTemplateList(TemplateVo vo) {
        return mapper.getsearchTemplateList(vo);
    }

    public TemplateVo templateDetail(int templateNo) {
        // Template 정보 가져오기
        TemplateVo templateVo = mapper.templateDetail(templateNo);
        return templateVo;
    }
    public List<ApprovalLineVo> selectApproverLineVo(int apprLineNo) {
        // ApproverLine 정보 가져오기
        List<ApprovalLineVo> approvalLineVoList = mapper.selectApproverLineVo(apprLineNo);
        System.out.println("approverLineVoList = " + approvalLineVoList);
        if(approvalLineVoList ==null){
            System.out.println("approverLineVoList null= " + approvalLineVoList);
        }
        return approvalLineVoList;
    }
    public int editTemplate(TemplateVo templateVo) {
        return mapper.editTemplate(templateVo);
    }

    public int deleteTemplate(int templateNo) {
        return mapper.deleteTemplate(templateNo);
    }


}
