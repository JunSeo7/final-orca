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

    // 개인 근무정보 리스트
    @GetMapping("list")
    public List<WorkInfoVo> workList() {
        List<WorkInfoVo> wVo = service.workList();
        return wVo;
    }

    // 출근
    @PostMapping("goWork")
    public WorkInfoVo startWork(@RequestBody WorkInfoVo vo) {
        WorkInfoVo wiVo = service.startWork(vo);
        return wiVo;
    }

    // 퇴근
    @PostMapping("leaveWork")
    public WorkInfoVo endWork(@RequestBody WorkInfoVo vo) {
        return service.endWork(vo);
    }

    // 연장 근무
    @PostMapping("overtime")
    public WorkInfoVo overTimeWork(@RequestBody WorkInfoVo vo){
        return service.overTimeWork(vo);
    }

    // 휴일 근무
}
