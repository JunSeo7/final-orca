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

//    public List<ApprovalLineVo> list() {
//        return mapper.list();
//    }

    public List<TemplateVo> getTemplateList() {
        List<TemplateVo> templates = mapper.getTemplateList();
        System.out.println("templates = " + templates);
        return templates;
    }

    public List<ApprovalLineVo> getApprovalLineList() {
        // ApproverLine 정보 가져오기
        List<ApprovalLineVo> approvalLines = mapper.getApprovalLineList();
        System.out.println("approvalLines = " + approvalLines);
        if(approvalLines ==null){
            System.out.println("approverLineVoList nulllllllllll= " + approvalLines);
    } return approvalLines;
  }
}
