package com.groupware.orca.workInfo.controller;

import com.groupware.orca.user.vo.UserVo;
import com.groupware.orca.workInfo.service.WorkInfoService;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import jakarta.servlet.http.HttpSession;
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
    public List<WorkInfoVo> workList(HttpSession httpSession) {
        UserVo loginUser = (UserVo) httpSession.getAttribute("loginUserVo");
        if (loginUser != null) {
            String empNo = loginUser.getEmpNo();

            List<WorkInfoVo> wVo = service.workList(empNo);

            System.out.println("wVo = " + wVo);

            return wVo;
        } else {
            throw new IllegalStateException("로그인 후 이용가능합니다.");
        }
    }

    // 출근 시간 기록
    @PostMapping("goWork")
    public void startWork(WorkInfoVo vo, HttpSession httpSession) {
        String empNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setEmpNo(empNo);

        service.startWork(vo);

        httpSession.setAttribute("workNo", vo.getWorkNo());
    }

    // 퇴근 시간 기록
    @PostMapping("leaveWork")
    public void endWork(WorkInfoVo vo, HttpSession httpSession) {
        String empNo = ((UserVo)httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setEmpNo(empNo);

        String workNo = (String) httpSession.getAttribute("workNo");
        if(workNo != null){
            vo.setWorkNo(workNo);

            service.endWork(vo);
        } else {
            throw new RuntimeException("출근 기록이 없습니다.");
        }
    }

    // 연장 근무 시간 기록
    @PostMapping("overtime")
    public void overTimeWork(WorkInfoVo vo) {
        service.overTimeWork(vo);
    }
}
