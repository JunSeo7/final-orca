package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.DocumentService;
import com.groupware.orca.document.vo.DocumentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService service;

    @GetMapping("write")
    public void write(){

    }

    @PostMapping("write")
    public void write(DocumentVo vo){

    }

    //전체목록
    @GetMapping("list")
    public List<DocumentVo> getDocumentList(Model model){
        System.out.println("model = " + model);
        List<DocumentVo> documentList = service.getDocumentList();
        System.out.println("documentList = " + documentList);
        return documentList;
    }
}
