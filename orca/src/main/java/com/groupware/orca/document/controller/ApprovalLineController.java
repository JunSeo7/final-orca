package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.ApprovalLineService;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("orca/apprline")
@RequiredArgsConstructor
public class ApprovalLineController {

    private final ApprovalLineService service;

    //add

    //list
    @GetMapping("list")
//    public String list(){
//        List<ApprovalLineVo> approvalLine = service.list();
//        System.out.println("approvalLine = " + approvalLine);
//        return "apprline/list";
//    }
//
//  @GetMapping("approvalLines")
    public String getApprovalLines(Model model) {
        List<TemplateVo> approvalLines = service.getApprovalLines();
        System.out.println("approvalLines = " + approvalLines);
        return "apprline/list";
    }

    //edit

    //delete
}
