package com.groupware.orca.document.dao;

import com.groupware.orca.document.mapper.TemplateMapper;
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

    public TemplateVo TemplateDetail(String templateNo) {
        return mapper.TemplateDetail(templateNo);
    }

    public int editTemplate(TemplateVo templateVo) {
        return mapper.editTemplate(templateVo);
    }

    public int deleteTemplate(String no) {
        return mapper.deleteTemplate(no);
    }


}
