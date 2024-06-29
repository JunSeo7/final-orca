package com.groupware.orca.workInfo.controller;

import com.groupware.orca.workInfo.service.WorkInfoService;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/re/work")
public class WorkInfoRestController {

    private final WorkInfoService service;

    // 개인 근무정보 리스트
    @GetMapping("list")
    public List<WorkInfoVo> workList(@RequestParam String empNo) {
        List<WorkInfoVo> wVo = service.workList(empNo);
        System.out.println("wVo = " + wVo);
        
        return wVo;
    }

    // 출근 시간 기록
    @PostMapping("goWork")
    public void startWork(WorkInfoVo vo) {
        service.startWork(vo);
    }

    // 퇴근 시간 기록
    @PostMapping("leaveWork")
    public void endWork(WorkInfoVo vo) {
        service.endWork(vo);
    }

    // 연장 근무 시간 기록
    @PostMapping("overtime")
    public void overTimeWork(WorkInfoVo vo){
        service.overTimeWork(vo);
    }
}
