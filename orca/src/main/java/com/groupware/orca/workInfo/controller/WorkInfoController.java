package com.groupware.orca.workInfo.controller;

import com.groupware.orca.workInfo.service.WorkInfoService;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public WorkInfoVo startWork(WorkInfoVo vo) {
        WorkInfoVo wiVo = service.startWork(vo);
        return wiVo;
    }
}
