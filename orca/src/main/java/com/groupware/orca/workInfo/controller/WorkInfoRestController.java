package com.groupware.orca.workInfo.controller;

import com.groupware.orca.common.vo.PageVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.common.vo.SearchVo;
import com.groupware.orca.user.vo.UserVo;
import com.groupware.orca.workInfo.service.WorkInfoService;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/re/work")
public class WorkInfoRestController {

    private final WorkInfoService service;
    private static final Logger logger = LoggerFactory.getLogger(WorkInfoRestController.class);

    // 모든 사원 근무 정보 리스트
    @GetMapping("allList")
    public List<WorkInfoVo> getAllWorkInfo() {
        List<WorkInfoVo> workInfoList = service.getAllWorkInfo();

        return workInfoList;
    }

    // 개인 근무정보 리스트
    @GetMapping("list")
    public List<WorkInfoVo> workList(HttpSession httpSession) {
        UserVo loginUser = (UserVo) httpSession.getAttribute("loginUserVo");
        int empNo = loginUser.getEmpNo();

        List<WorkInfoVo> wVo = service.workList(empNo);
        System.out.println("wVo = " + wVo);
        return wVo;
    }


    // 출근
    @PostMapping("/goWork")
    public ResponseEntity<Map<String, Object>> startWork(WorkInfoVo vo, HttpSession httpSession) {
        UserVo loginUser = (UserVo) httpSession.getAttribute("loginUserVo");

        int empNo = loginUser.getEmpNo();
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

        int empNo = loginUser.getEmpNo();
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

    // 출근 시간 조회
    @GetMapping("getStartWorkTime")
    public ResponseEntity<Map<String, Object>> getStartWorkTime(HttpSession httpSession, @RequestParam String workDate) {
        UserVo loginUser = (UserVo) httpSession.getAttribute("loginUserVo");
        int empNo = loginUser.getEmpNo();
        String startWorkTime = service.getStartWorkTime(empNo, workDate);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("startWorkTime", startWorkTime);
        return ResponseEntity.ok(response);
    }

    // 퇴근 시간 조회
    @GetMapping("getEndWorkTime")
    public ResponseEntity<Map<String, Object>> getEndWorkTime(HttpSession httpSession, @RequestParam String workDate) {
        UserVo loginUser = (UserVo) httpSession.getAttribute("loginUserVo");
        int empNo = loginUser.getEmpNo();
        String endWorkTime = service.getEndWorkTime(empNo, workDate);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("endWorkTime", endWorkTime);
        return ResponseEntity.ok(response);
    }

    // 연장 근무 시간 기록
    @PostMapping("overtime")
    public void overTimeWork(WorkInfoVo vo) {
        service.overTimeWork(vo);
    }

    @GetMapping("getPage")
    public Pagination getPage(@RequestParam("page") int page){
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setPageSize(10);
        pageVo.setRecordSize(12);
        int dataCount = service.dataCount();
        Pagination pagination = new Pagination(dataCount, pageVo);
        return pagination;
    }

    @GetMapping("getData")
    @ResponseBody
    public List<WorkInfoVo> getData(@RequestParam("startNum") int startNum,
                                         @RequestParam("endNum") int endNum) {
        List<WorkInfoVo> workInfoVoList = service.getData(startNum, endNum);
        return workInfoVoList;
    }

    @GetMapping("searchData")
    @ResponseBody
    public List<WorkInfoVo> searchData(
            @RequestParam("name") String name,
            @RequestParam("partName") String partName,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("startNum") int startNum,
            @RequestParam("endNum") int endNum) {

        List<WorkInfoVo> workInfoVoList = service.searchData(name, partName, startDate, endDate, startNum, endNum);
        return workInfoVoList;
    }

    @GetMapping("weekly")
    public List<WorkInfoVo> getWeeklyWorkRecords(HttpSession httpSession) {
        UserVo loginUser = (UserVo) httpSession.getAttribute("loginUserVo");
        int empNo = loginUser.getEmpNo();
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(java.time.DayOfWeek.SUNDAY);
        return service.getWorkRecordsBetween(empNo, startOfWeek.atStartOfDay(), endOfWeek.atTime(LocalTime.MAX));
    }
}
