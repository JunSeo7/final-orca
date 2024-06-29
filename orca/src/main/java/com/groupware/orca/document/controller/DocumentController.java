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

    // 결재 작성 화면 (카테고리 보여주기)
    @GetMapping("write")
    @ResponseBody
    public List<TemplateVo> getTemplateList(){
        List<TemplateVo> templateVoList = service.getTemplateList();
        return templateVoList;
    }
    // 템플릿 내용 가져오기
    @GetMapping("gettemplate")
    @ResponseBody
    public TemplateVo getTemplateContent(@RequestParam("templateNo") int templateNo){
        TemplateVo template = service.getTemplateContent(templateNo);
        return template;
    }
    // 결재 작성
    @PostMapping("write")
    @ResponseBody
    public void writeDocument(DocumentVo vo){
    int result = service.writeDocument(vo);
    }

    // 전체 목록
    @GetMapping("list")
    public List<DocumentVo> getDocumentList(Model model){
        System.out.println("model = " + model);
        List<DocumentVo> documentList = service.getDocumentList();
        System.out.println("documentList = " + documentList);
        return documentList;
    }
    // 올린 결재
    // 받은 결재
    // 검색(기안자/제목/내용/카테고리)
    // 결재 수정 (임시저장일 경우)
    // 결재 취소
}