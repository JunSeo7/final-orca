package com.groupware.orca.department.managementSupport.calendar.controller;

import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.common.vo.PageVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.department.managementSupport.calendar.service.ManagementSupportCalendarService;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orca/managementSupport")
@RequiredArgsConstructor
public class ManagementSupportCalendarRestController {

    private final ManagementSupportCalendarService service;

    @PostMapping("createCalendarCompany")
    public ResponseEntity<Integer> createCalendarCompany(CalendarVo vo, HttpSession httpSession) throws InvalidInputException {
        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");
        if (userVo == null) {
            // 사용자 정보가 세션에 없을 경우, 인증되지 않은 상태로 응답
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        int writerNo = userVo.getEmpNo();
        vo.setWriterNo(writerNo);
        try {
            int result = service.createCalendarCompany(vo);
            if (result > 0) {
                // 성공적으로 작업이 완료된 경우
                return ResponseEntity.ok(result);
            } else {
                // 실패한 경우
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
        } catch (InvalidInputException e) {
            // 입력 유효성 검사 실패한 경우
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // 일반적인 서버 오류
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("listCalendarPage")
    public Pagination listCalendarPage(@RequestParam("page") int page) {
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setPageSize(10);
        pageVo.setRecordSize(10);
        int totalRecordCount = service.getCalendarCnt();
        Pagination pagination = new Pagination(totalRecordCount, pageVo);
        return pagination;
    }

    @GetMapping("listCalendarData")
    public List<CalendarVo> listCalendarData(@RequestParam("startNum") int startNum, @RequestParam("endNum") int endNum) {
        List<CalendarVo> calendarVoList = service.listCalendarData(startNum, endNum);
        return calendarVoList;
    }

    @GetMapping("getCalendarByOne")
    public CalendarVo getCalendarByOne(@RequestParam("calendarNo") int calendarNo) {
        CalendarVo calendarVo = service.getCalendarByOne(calendarNo);
        return calendarVo;
    }

    @PostMapping("editCalendar")
    public int editCalendar(@RequestBody CalendarVo vo) throws InvalidInputException {
        int result = service.editCalendar(vo);
        return result;
    }

    @PostMapping("deleteCalendar")
    public int deleteCalendar(@RequestParam("calendarNo") int calendarNo) {
        int result = service.deleteCalendar(calendarNo);
        return result;
    }

    @GetMapping("searchListCalendarPage")
    public Pagination searchListCalendarPage(@RequestParam("page") int page, @RequestParam("keyword") String keyword) {
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setPageSize(10);
        pageVo.setRecordSize(10);
        if (keyword != null) {
            keyword = keyword.replaceAll("\\s+", "");
        }
        int totalRecordCount = service.getSearchCalendarCnt(keyword);
        Pagination pagination = new Pagination(totalRecordCount, pageVo);
        return pagination;
    }

    @GetMapping("searchListCalendarData")
    public List<CalendarVo> searchListCalendarData(@RequestParam("startNum") int startNum,
                                                   @RequestParam("endNum") int endNum,
                                                   @RequestParam("keyword") String keyword) {
        if (keyword != null) {
            keyword = keyword.replaceAll("\\s+", "");
        }

        List<CalendarVo> calendarVoList = service.searchListCalendarData(keyword, startNum, endNum);
        return calendarVoList;
    }
}
