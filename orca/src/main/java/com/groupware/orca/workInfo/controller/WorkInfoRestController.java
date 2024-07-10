package com.groupware.orca.workInfo.controller;

import com.groupware.orca.user.vo.UserVo;
import com.groupware.orca.workInfo.service.WorkInfoService;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/re/work")
public class WorkInfoRestController {

    private final WorkInfoService service;
    private static final Logger logger = LoggerFactory.getLogger(WorkInfoRestController.class);


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

    // 출근
    @PostMapping("/goWork")
    public ResponseEntity<Map<String, Object>> startWork(WorkInfoVo vo, HttpSession httpSession) {
        UserVo loginUser = (UserVo) httpSession.getAttribute("loginUserVo");

        String empNo = loginUser.getEmpNo();
        vo.setEmpNo(empNo);
        String workNo = service.startWork(vo);
        httpSession.setAttribute("workNo", workNo);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("startWorkTime", vo.getFormattedStartTime());
        return ResponseEntity.ok(response);
    }

    // 퇴근
    @PostMapping("leaveWork")
    public ResponseEntity<Map<String, Object>> endWork(WorkInfoVo vo, HttpSession httpSession) {
        Map<String, Object> response = new HashMap<>();
        logger.info("endWork called with vo: {}", vo);

        UserVo loginUser = (UserVo) httpSession.getAttribute("loginUserVo");

        String empNo = loginUser.getEmpNo();
        vo.setEmpNo(empNo);

        String workNo = (String) httpSession.getAttribute("workNo");
        logger.info("WorkNo from session: {}", workNo);

        if (workNo != null) {
            vo.setWorkNo(workNo);
            service.endWork(vo);

            response.put("success", true);
            response.put("endWorkTime", vo.getFormattedEndTime());
        } else {
            response.put("success", false);
            response.put("message", "출근 기록이 없습니다.");
        }

        return ResponseEntity.ok(response);
    }

    // 연장 근무 시간 기록
    @PostMapping("overtime")
    public void overTimeWork(WorkInfoVo vo) {
        service.overTimeWork(vo);
    }
}
