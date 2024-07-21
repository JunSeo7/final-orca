package com.groupware.orca.approvalLine.controller;

import com.groupware.orca.approvalLine.service.ApprovalLineService;
import com.groupware.orca.approvalLine.vo.ApprovalLineVo;
import com.groupware.orca.approvalLine.vo.ApproverVo;
import com.groupware.orca.docTemplate.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("orca/apprline")
@RequiredArgsConstructor
public class ApprovalLineController {

    private final ApprovalLineService service;

    // 관리자(경영지원부서)
    // 기본 결재선 등록 - 화면
    // 세션 - 관리자 아니면 나가세요 하기
    @GetMapping("add")
    public String addApprLineView(Model model, HttpSession httpSession) {
        return "apprline/add";
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

    //기본 결재선 등록
    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<String> addApprovalLine(@RequestBody ApprovalLineVo approvalLineVo) { // @RequestBody로 변경
        try {
            int result = service.addApprovalLine(approvalLineVo);
            return ResponseEntity.ok("결재선이 저장되었습니다."); // 성공 시 메시지 반환
        } catch (IllegalStateException e) {
            // 기본 결재선 중복 등 특정 조건에 대한 예외 처리
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // 결재선 전체목록 (양식/결재라인)
    // 세션 - 관리자 아니면 나가세요 하기
    @GetMapping("list")
    public String getApprLines(Model model, HttpSession httpSession) {
        List<ApprovalLineVo> approvalLines = service.getApprovalLines();
        model.addAttribute("approvalLines", approvalLines);
        return "apprline/list";
    }

    // 결재선 전체목록 (양식/결재라인)
    // 세션 - 관리자 아니면 나가세요 하기
    @GetMapping("getlist")
    @ResponseBody
    public List<ApprovalLineVo> getApprLines(HttpSession httpSession) {
        List<ApprovalLineVo> approvalLines = service.getApprovalLines();
        return approvalLines;
    }

    // 결재선 삭제
    @PostMapping("delete")
    public String deleteApprLine(@RequestParam("apprLineNo") int apprLineNo) {
        int result = service.deleteApprLine(apprLineNo);
        return "redirect:/orca/apprline/list";
    }

    // 결재자, 합의자
    // 결재선 - 승인처리, 반려처리
    @PostMapping("status")
    public String updateStatusApprLine(ApproverVo vo, HttpSession httpSession){
        vo.setApproverNo(((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo());
        int result = service.updateStatusApprLine(vo);
        return "redirect:/orca/document/list";
    }
}