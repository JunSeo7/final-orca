package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.DocumentService;
import com.groupware.orca.document.vo.ApprovalLineVo;
import com.groupware.orca.document.vo.DocumentVo;
import com.groupware.orca.document.vo.TemplateVo;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("orca/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    // 결재 작성 화면
    @GetMapping("write")
    public String getTemplateList(){
        return "document/write";
    }
    // 결재 작성 카테고리 가져오기
    @GetMapping("categorie/list")
    @ResponseBody
    public List<TemplateVo> getCategory() {
        System.out.println("ApprovalLineController.getCategory");
        List<TemplateVo> templateVoList = service.getCategory();
        System.out.println("templateVoList = " + templateVoList);
        return templateVoList;
    }
    // 결재 작성 결재양식 제목 가져오기
    @GetMapping("template/list")
    @ResponseBody
    public List<TemplateVo> getTemplateByCategoryNo(@RequestParam int categoryNo) {
        System.out.println("ApprovalLineController.getTemplateByCategoryNo");
        System.out.println("categoryNo = " + categoryNo);
        List<TemplateVo> templateVoList= service.getTemplateByCategoryNo(categoryNo);
        System.out.println("templateVoList = " + templateVoList);
        return service.getTemplateByCategoryNo(categoryNo);
    }
    // 결재 작성 템플릿 내용 가져오기
    @GetMapping("template/content")
    @ResponseBody
    public TemplateVo getTemplateContent(@RequestParam("templateNo") int templateNo){
        TemplateVo template = service.getTemplateContent(templateNo);
        return template;
    }
    // 결재 작성 결재선 가져오기
    @GetMapping("template/apprline")
    @ResponseBody
    public ApprovalLineVo getTemplateApprLine(@RequestParam("templateNo") int templateNo){
        System.out.println("templateNo = " + templateNo);
        ApprovalLineVo apprline = service.getTemplateApprLine(templateNo);
        return apprline;
    }
    // 결재 작성
    @PostMapping("write")
    public String writeDocument(DocumentVo vo, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(Integer.parseInt(loginUserNo));
        System.out.println("DocumentController.writeDocument");
        System.out.println("vo = " + vo);
        int result = service.writeDocument(vo);
        return "redirect:/orca/document/list";
    }

    // 내가 작성한 결재 문서 목록 조회
    @GetMapping("list")
    public String getDocumentList(Model model, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        List<DocumentVo> documentList = service.getDocumentList(loginUserNo);
        model.addAttribute("documentList", documentList);
        System.out.println("documentList = " + documentList);
        return "document/list";
    }

    // 올린 결재


    // 받은 결재


    // 검색(기안자/제목/내용/카테고리)


    // 결재 상세보기 - 기안자 no 추가 (params)
    @GetMapping("detail")
    public String getDocumentByNo(Model model, int docNo){
        DocumentVo documentList = service.getDocumentByNo(docNo);
        model.addAttribute("documentList", documentList);
        return "document/detail";
    }

    // 결재 기안 철회(아무도 결재승인 안했을 경우 가능)
    @PutMapping("delete")
    public String deleteDocumentByNo(int docNo, HttpSession httpSession){
        String loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        int result = service.deleteDocumentByNo(docNo, loginUserNo);
        return "redirect:/orca/document/list";
    }
}