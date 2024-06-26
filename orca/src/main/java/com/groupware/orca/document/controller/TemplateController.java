package com.groupware.orca.document.controller;

import com.groupware.orca.document.service.TemplateService;
import com.groupware.orca.document.vo.TemplateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService service;

    @GetMapping("list")
    public List<TemplateVo> getTemplateList(Model model){
        List<TemplateVo> templateList = service.getTemplateList();
        model.addAttribute("templateList", templateList);
        System.out.println("templateList = " + templateList);
        return templateList;
    }

    @GetMapping("add")
    public String addTemplate() {
        return "template/add";
    }

    @PostMapping("add")
    @ResponseBody
    public String  addTemplate(TemplateVo vo) {
        System.out.println("vo = " + vo);
        int result = service.addTemplate(vo);
        System.out.println(result);
        return "redirect:/template/list";
    }

    @GetMapping("detail")
    public String TemplateDetail(@RequestParam("templateNo")String templateNo, Model model){
        TemplateVo vo = service.TemplateDetail(templateNo);
        model.addAttribute("templateDetail", vo);
        System.out.println("templateDetail = " + vo);
        return "template/detail";
    }

    @PostMapping("edit")
    @ResponseBody
    public void editTemplate(@RequestBody TemplateVo vo){
        service.editTemplate(vo);
    }

    @PostMapping("delete")
    @ResponseBody
    public String deleteTemplate(@RequestParam("templateNo")String templateNo) {
        System.out.println("templateNo = " + templateNo);
        int result = service.deleteTemplate(templateNo);
        System.out.println("result = " + result);
        return "redirect:/template/list";
    }
}
