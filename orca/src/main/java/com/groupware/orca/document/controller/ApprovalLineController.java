package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.ApprovalLineService;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.ApproverVo;
import com.groupware.orca.document.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("orca/apprline")
@RequiredArgsConstructor
public class ApprovalLineController {

    private final ApprovalLineService service;

    //기본 결재선 등록 - 화면
    @GetMapping("add")
    public String addApprLineView(Model model) {
        System.out.println("model = " + model);
        model.addAttribute("approvalLine", new ApprovalLineVo());
        return "apprline/add";
    }
//    // 결재선 등록 조직도 가져오기
//    @GetMapping("/add/organization/list")
//    public List<UserVo> getUsers() {
//        return service.getUsers();
//    }
//    @GetMapping("template/categories")
//    public List<TemplateVo> getTemplateTitle() {
//        return service.getTemplateTitle();
//    }

    //기본 결재선 등록
    @PostMapping("/add")
    public String addApprovalLine(@ModelAttribute ApprovalLineVo approvalLineVo) {
        System.out.println("ApprovalLineController.addApprovalLine");
        System.out.println("approvalLineVo = " + approvalLineVo);
        service.addApprovalLine(approvalLineVo);
       return "redirect:/apprline/list";
    }


//    // 결재선 전체목록 (양식/결재라인)
//    @GetMapping("list")
//    public String getApprLines(Model model) {
//        List<ApprovalLineVo> approvalLines = service.getApprovalLines();
//        System.out.println("approvalLines = " + approvalLines);
//        model.addAttribute("approvalLines", approvalLines);
//        return "apprline/list";
//    }

    // 결재선 수정


    // 결재선 삭제
    public String deleteApprLine(@RequestParam("apprLineNo") int apprLineNo) {
        service.deleteApprLine(apprLineNo);
        return "redirect:/orca/apprline/list";
    }
}
