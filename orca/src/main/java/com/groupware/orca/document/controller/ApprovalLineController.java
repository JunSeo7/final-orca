package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.ApprovalLineService;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.ApproverVo;
import com.groupware.orca.document.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //관리자

    //기본 결재선 등록 - 화면
    @GetMapping("add")
    public String addApprLineView(Model model) {
        System.out.println("model = " + model);
        return "apprline/add";
    }
    // 결재선 등록 조직도 가져오기
    @GetMapping("organization/list")
    @ResponseBody
    public List<UserVo> getUsers() {
        System.out.println("ApprovalLineController.getUsers");
        List<UserVo> userVoList = service.getUsers();
        System.out.println("userVoList = " + userVoList);
        return userVoList;
    }
    // 결재선 등록 카테고리 가져오기
    @GetMapping("categorie/list")
    @ResponseBody
    public List<TemplateVo> getCategory() {
        System.out.println("ApprovalLineController.getCategory");
        List<TemplateVo> templateVoList = service.getCategory();
        System.out.println("templateVoList = " + templateVoList);
        return templateVoList;
    }
   // 결재선 등록 결재양식 제목 가져오기
   @GetMapping("template/list")
   @ResponseBody
   public List<TemplateVo> getTemplateByCategoryNo(@RequestParam int categoryNo) {
       System.out.println("ApprovalLineController.getTemplateByCategoryNo");
       System.out.println("categoryNo = " + categoryNo);
       List<TemplateVo> templateVoList= service.getTemplateByCategoryNo(categoryNo);
       System.out.println("templateVoList = " + templateVoList);
       return service.getTemplateByCategoryNo(categoryNo);
   }

    //기본 결재선 등록
    @PostMapping("add")
    public String addApprovalLine(@ModelAttribute ApprovalLineVo approvalLineVo) {
        System.out.println("ApprovalLineController.addApprovalLine");
        System.out.println("approvalLineVo = " + approvalLineVo);
        service.addApprovalLine(approvalLineVo);
       return "redirect:/orca/apprline/list";
    }

    // 결재선 전체목록 (양식/결재라인)
    @GetMapping("list")
    public String getApprLines(Model model) {
        List<ApprovalLineVo> approvalLines = service.getApprovalLines();
        System.out.println("approvalLines = " + approvalLines);
        model.addAttribute("approvalLines", approvalLines);
        return "apprline/list";
    }

    // 결재선 - 승인처리, 반려처리
    // 결재자가 모두 승인했을 경우 결재 문서 승인 (종결처리)
    // 결재자중 한명이라도 반려했을 경우 문서 반려 (종결처리)
    @PostMapping("status")
    public void updateStatusApprLine(ApproverVo vo){
        int result = service.updateStatusApprLine(vo);
        System.out.println("result = " + result);

    }
    // 문서 - 승인처리, 반려처리
    // 결재자가 모두 승인했을 경우 결재 문서 승인 (종결처리)
    // 결재자중 한명이라도 반려했을 경우 문서 반려 (종결처리)
    @PostMapping("document/status")
    public String updateStatusDocument(ApproverVo vo) {
        int result = service.updateStatusApprLine(vo);
        System.out.println("result = " + result);
        return "redirect:/orca/document/list";
    }

    // 결재선 삭제
    @GetMapping("delete")
    public String deleteApprLine(@RequestParam("apprLineNo") int apprLineNo) {
        service.deleteApprLine(apprLineNo);
        return "redirect:/orca/apprline/list";
    }

}
