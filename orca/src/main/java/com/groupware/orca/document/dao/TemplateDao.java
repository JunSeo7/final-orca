package com.groupware.orca.document.dao;

import com.groupware.orca.document.mapper.TemplateMapper;
import com.groupware.orca.document.vo.ApproverLineVo;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TemplateDao {

    private final TemplateMapper mapper;

    public List<TemplateVo> getTemplateList() {
        return mapper.getTemplateList();
    }

    public int addTemplate(TemplateVo vo) {
        return mapper.addTemplate(vo);
    }

    public TemplateVo templateDetail(String templateNo) {
        // Template 정보 가져오기
        TemplateVo templateVo = mapper.templateDetail(templateNo);
        System.out.println("templateVo = " + templateVo);
        return templateVo;
    }
    public List<ApproverLineVo> selectApproverLineVo(int apprLineNo) {
        // ApproverLine 정보 가져오기
        List<ApproverLineVo> approverLineVoList = mapper.selectApproverLineVo(apprLineNo);
        System.out.println("approverLineVoList = " + approverLineVoList);
        if(approverLineVoList==null){
            System.out.println("approverLineVoList nulllllllllll= " + approverLineVoList);
        }
        return approverLineVoList;
    }
    public int editTemplate(TemplateVo templateVo) {
        return mapper.editTemplate(templateVo);
    }

    public int deleteTemplate(String no) {
        return mapper.deleteTemplate(no);
    }


}
