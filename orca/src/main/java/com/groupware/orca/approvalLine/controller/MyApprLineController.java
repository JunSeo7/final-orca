package com.groupware.orca.approvalLine.controller;

import com.groupware.orca.approvalLine.service.MyApprLineService;
import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import com.groupware.orca.docTemplate.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("orca/myapprline")
@RequiredArgsConstructor
public class MyApprLineController {

    private final MyApprLineService service;

    //사원

    //기본 결재선 등록 - 화면
    @GetMapping("add")
    public String addApprLineView(Model model) {
        model.addAttribute("approvalLine", new ApprovalLineVo());
        return "myapprline/add";
    }
    // 결재선 등록 조직도 가져오기
    @GetMapping("organization/list")
    @ResponseBody
    public List<UserVo> getUsers() {
        List<UserVo> userVoList = service.getUsers();
        return userVoList;
    }
    // 결재선 등록 카테고리 가져오기
    @GetMapping("categorie/list")
    @ResponseBody
    public List<TemplateVo> getCategory() {
        List<TemplateVo> templateVoList = service.getCategory();
        return templateVoList;
    }
   // 결재선 등록 결재양식 제목 가져오기
   @GetMapping("template/list")
   @ResponseBody
   public List<TemplateVo> getTemplateByCategoryNo(@RequestParam int categoryNo) {
       List<TemplateVo> templateVoList= service.getTemplateByCategoryNo(categoryNo);
       return service.getTemplateByCategoryNo(categoryNo);
   }

    //마이 결재선 등록
    @PostMapping("add")
    public String addApprovalLine(@ModelAttribute ApprovalLineVo approvalLineVo, HttpSession httpSession) {
        approvalLineVo.setWriterNo(Integer.parseInt(((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo()));
        int result = service.addApprovalLine(approvalLineVo);
       return "redirect:/orca/myapprline/list";
    }

    // 결재선 전체목록 (양식/결재라인)
    @GetMapping("list")
    public String getApprLines(Model model, HttpSession httpSession) {
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<ApprovalLineVo> approvalLines = service.getApprovalLines(loginUserNo);
        System.out.println("approvalLines = " + approvalLines);
        model.addAttribute("approvalLines", approvalLines);
        return "myapprline/list";
    }

    // 결재선 삭제
    @PostMapping("delete")
    public String deleteApprLine(@RequestParam("apprLineNo") int apprLineNo, HttpSession httpSession) {
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        System.out.println("loginUserNo = " + loginUserNo);
        System.out.println("apprLineNo = " + apprLineNo);
        service.deleteApprLine(apprLineNo, loginUserNo);
        return "redirect:/orca/myapprline/list";
    }
}