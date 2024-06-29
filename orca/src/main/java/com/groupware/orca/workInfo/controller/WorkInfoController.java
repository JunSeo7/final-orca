package com.groupware.orca.workInfo.controller;

import com.groupware.orca.workInfo.service.WorkInfoService;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/work")
public class WorkInfoController {

    private final WorkInfoService service;

    // 개인 근무 화면
    @GetMapping("workInfo")
    public String showWorkList(){
        return "work/workInfo";
    }

    // 휴일 근무
}
