package com.groupware.orca.docTemplate.controller;

import com.groupware.orca.docTemplate.service.TemplateService;
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
@RequestMapping("orca/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService service;

    // 관리자(경영지원부서)
    // 결재양식 등록 화면

    @GetMapping("add")
    public String addTemplate() {
        return "template/add";
    }


    @GetMapping("search")
    public String searchTemplateList() {
        return "template/search";
    }

    // 세션으로 관리자인지 확인하고 기능 - 구현
    // 결재양식 등록 기능
    @PostMapping("add")
    public String addTemplate(TemplateVo vo, HttpSession httpSession) {
        System.out.println("vo = " + vo);
        int result = service.addTemplate(vo);
        System.out.println("result = " + result);
        return "redirect:/orca/template/list";
    }
    // 결재양식 목록
    @GetMapping("list")
    public String getTemplateList(Model model, HttpSession httpSession){
        List<TemplateVo> templateList = service.getTemplateList();
        model.addAttribute("templateList", templateList);
        return "template/list";
    }

    // 결재양식 검색
    @GetMapping("/search/data")
    @ResponseBody
    public List<TemplateVo> getsearchTemplateList(
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchText", required = false) String searchText) {

            TemplateVo vo = new TemplateVo();

            if ("title".equals(searchType)) {
                vo.setTitle(searchText);
            } else if ("categoryName".equals(searchType)) {
                vo.setCategoryName(searchText);
            }

        System.out.println("vo 들어온거= " + vo);
        List<TemplateVo> templateList = service.getsearchTemplateList(vo);
        System.out.println("vo 결과= " + templateList);

        return templateList;
    }

    // 결재양식 상세보기
    @GetMapping("detail")
    public String templateDetail( Model model, int templateNo,HttpSession httpSession) {
        TemplateVo vo = service.getTemplateDetail(templateNo);
        model.addAttribute("templateDetail", vo);
        return "template/detail";
    }

    // 결재양식 수정 화면
    @GetMapping("edit")
    public String editTemplate(@RequestParam("templateNo") String templateNo, Model model, HttpSession httpSession) {
        System.out.println("templateNo = " + templateNo);
        model.addAttribute("templateNo", templateNo);
        return "template/edit";
    }
    // 결재양식 수정 데이터 가져오기
    @GetMapping("getTemplateData")
    @ResponseBody
    public TemplateVo getTemplateData(@RequestParam("templateNo") int templateNo, HttpSession httpSession) {
        System.out.println("templateNo = " + templateNo);
        TemplateVo vo = service.getTemplateDetail(templateNo);
        System.out.println("vo = " + vo);
        return vo;
    }
    // 결재양식 수정 기능
    @PutMapping("edit")
    public ResponseEntity<Void> editTemplate(@RequestBody TemplateVo vo, HttpSession httpSession){

        int loginUserNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();

        int result = service.editTemplate(vo);
        System.out.println("result = " + result);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // 결재양식 삭제
    @PostMapping("delete")
    public String deleteTemplate(int templateNo, HttpSession httpSession) {
        int result = service.deleteTemplate(templateNo);
        return "redirect:/orca/template/list";
    }
}
