package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.ApprovalLineService;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("orca/apprline")
@RequiredArgsConstructor
public class ApprovalLineController {

    private final ApprovalLineService service;

    //기본 결재선 등록 - 화면
    @GetMapping("add")
    public String addApprLineView(Model model) {
        model.addAttribute("approvalLine", new ApprovalLineVo());
        return "apprline/add";
    }
    //기본 결재선 등록
    @PostMapping("add")
    public String addApprLine(@ModelAttribute ApprovalLineVo approvalLineVo) {
        service.addApprLineTemplate(approvalLineVo);
        return "redirect:/orca/apprline/list";
    }

    // 결재선 전체목록 (양식/결재라인)
    @GetMapping("list")
    public String getApprLines(Model model) {
        List<TemplateVo> approvalLines = service.getApprovalLines();
        System.out.println("approvalLines = " + approvalLines);
        model.addAttribute("approvalLines", approvalLines);
        return "apprline/list";
    }

    //edit

    // 결재선 삭제
    public String deleteApprLine(@RequestParam("apprLineNo") int apprLineNo) {
        service.deleteApprLine(apprLineNo);
        return "redirect:/orca/apprline/list";
    }
}
