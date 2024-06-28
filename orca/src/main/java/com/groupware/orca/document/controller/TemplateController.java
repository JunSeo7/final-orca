package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.TemplateService;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("orca/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService service;

    @GetMapping("add")
    public String addTemplate() {
        System.out.println("TemplateController.addTemplate get");
        return "template/add";
    }

    @PostMapping("add")
    public String addTemplate(TemplateVo vo) {
        System.out.println("TemplateController.addTemplate post");
        int result = service.addTemplate(vo);
        return "redirect:/orca/template/list";
    }

    @GetMapping("list")
    public String getTemplateList(Model model){
        List<TemplateVo> templateList = service.getTemplateList();
        model.addAttribute("templateList", templateList);
        System.out.println("templateList = " + templateList);
        return "template/list";
    }

    @GetMapping("detail")
    public String templateDetail(@RequestParam("templateNo") String templateNo, Model model) {
        TemplateVo vo = service.getTemplateDetail(templateNo);
        model.addAttribute("templateDetail", vo);
        System.out.println("templateDetail = " + vo);
        return "template/detail";
    }

    @PostMapping("edit")
    @ResponseBody
    public String editTemplate(@RequestBody TemplateVo vo){
        service.editTemplate(vo);
        return "redirect:/orca/template/list";
    }

    @PostMapping("delete")
    @ResponseBody
    public String deleteTemplate(@RequestParam("templateNo")int templateNo) {
        System.out.println("templateNo = " + templateNo);
        int result = service.deleteTemplate(templateNo);
        System.out.println("result = " + result);
        return "redirect:/orca/template/list";
    }
}
