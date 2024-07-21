package com.groupware.orca.calendar.controller;

import com.groupware.orca.calendar.service.CalendarService;
import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.common.vo.PageVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/calendar")
public class CalendarRestController {
    private final CalendarService service;

    @GetMapping("showCalendarBarContent")
    public ResponseEntity<List<CalendarVo>> showCalendarBarContent(
            @RequestParam("range") String range,
            HttpSession httpSession) {
        // 세션에서 로그인한 사용자 정보를 가져옴
        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");
        if (userVo == null) {
            // 사용자 정보가 없으면 인증되지 않은 상태로 응답
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        try {
            // 서비스 메소드 호출하여 데이터 조회
            List<CalendarVo> calendarBarList = service.showCalendarBarContent(range, userVo);
            // 데이터가 성공적으로 조회된 경우
            return ResponseEntity.ok(calendarBarList);
        } catch (Exception e) {
            // 데이터 조회 중 오류가 발생한 경우
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("createCalendar")
    public ResponseEntity<Integer> createCalendar(CalendarVo vo, HttpSession httpSession) throws InvalidInputException {
        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");
        if (userVo == null) {
            // 사용자 정보가 세션에 없으면 인증되지 않은 상태로 응답
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        int writerNo = userVo.getEmpNo();
        vo.setWriterNo(writerNo);
        try {
            int result = service.createCalendar(vo);
            if (result > 0) {
                // 작업이 성공적으로 완료된 경우
                return ResponseEntity.ok(result);
            } else {
                // 작업이 실패한 경우
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

    @PostMapping("deleteCalendar")
    public int deleteCalendar(@RequestParam("calendarNo") int calendarNo, HttpSession httpSession) {
        int writerNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        int result = service.deleteCalendar(calendarNo, writerNo);
        return result;
    }

    @PostMapping("editCalendar")
    public int editCalendar(@RequestBody CalendarVo vo, HttpSession httpSession) throws InvalidInputException {
        int writerNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();
        vo.setWriterNo(writerNo);
        int result = service.editCalendar(vo);
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

    @GetMapping("getCalendarByOne")
    public CalendarVo getCalendarByOne(@RequestParam("calendarNo") int calendarNo) {
        CalendarVo calendarVo = service.getCalendarByOne(calendarNo);
        return calendarVo;
    }
}
