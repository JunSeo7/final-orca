package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.DocumentService;
import com.groupware.orca.document.vo.DocumentVo;
import com.groupware.orca.document.vo.TemplateVo;
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
    // 결재 작성
    @PostMapping("write")
    public String writeDocument(DocumentVo vo){
        System.out.println("DocumentController.writeDocument");
        System.out.println("vo = " + vo);
    int result = service.writeDocument(vo);
        return "redirect:/orca/document/list";
    }

    // 전체 목록
    @GetMapping("list")
    public String getDocumentList(Model model){
        List<DocumentVo> documentList = service.getDocumentList();
        model.addAttribute("documentList", documentList);
        System.out.println("documentList = " + documentList);
        return "document/list";
    }
    // 올린 결재
    // 받은 결재
    // 검색(기안자/제목/내용/카테고리)
    // 결재 수정 (임시저장일 경우)
    // 결재 취소
}