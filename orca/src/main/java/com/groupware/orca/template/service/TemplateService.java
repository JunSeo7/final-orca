package com.groupware.orca.template.service;

import com.groupware.orca.template.dao.TemplateDao;
import com.groupware.orca.template.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TemplateService {

    private final TemplateDao dao;

    public List<TemplateVo> getTemplateList() {
        return dao.getTemplateList();
    }

    public int addTemplate(TemplateVo vo) {
        return dao.addTemplate(vo);
    }

    public TemplateVo TemplateDetail(String templateNo) {
        return dao.TemplateDetail(templateNo);
    }

    public int editTemplate(TemplateVo vo) {
        return dao.editTemplate(vo);
    }

    public int deleteTemplate(String no) {
        return dao.deleteTemplate(no);
    }


}
