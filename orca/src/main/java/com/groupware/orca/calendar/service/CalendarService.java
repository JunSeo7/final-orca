package com.groupware.orca.calendar.service;

import com.groupware.orca.calendar.dao.CalendarDao;
import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarService {
    private final CalendarDao dao;

    public int createCalendar(CalendarVo vo) throws InvalidInputException {

        if (!vo.getTitle().isEmpty() && vo.getTitle().length() > 13) {
            throw new InvalidInputException("글자수가 최대입니다. (제목은 13자 이내)");
        }
        if (!vo.getContent().isEmpty() && vo.getContent().length() > 332) {
            throw new InvalidInputException("글자수가 최대입니다. (내용은 332자 이내)");
        }

        if (!vo.getTitle().isEmpty() && !vo.getStartDate().isEmpty() && !vo.getEndDate().isEmpty()) {
            LocalDate startDate = LocalDate.parse(vo.getStartDate());
            LocalDate endDate = LocalDate.parse(vo.getEndDate());
            if (endDate.isBefore(startDate)) {
                throw new InvalidInputException("시작일은 종료일보다 빨라야합니다.");
            }
        } else {
            throw new InvalidInputException("제목, 시작일, 종료일은 모두 필수 입력 사항입니다.");
        }
        return dao.createCalendar(vo);
    }

    public List<CalendarVo> showCalendarBarContent(String range, UserVo userVo) {
        return dao.showCalendarBarContent(range, userVo);
    }

    public int deleteCalendar(int calendarNo, int writerNo) {
        return dao.deleteCalendar(calendarNo, writerNo);
    }

    public int editCalendar(CalendarVo vo) throws InvalidInputException {
        if (vo.getTitle() != null && vo.getTitle().length() > 13) {
            throw new InvalidInputException("글자수가 최대입니다. (제목은 13자 이내)");
        }
        if (vo.getContent() != null && vo.getContent().length() > 332) {
            throw new InvalidInputException("글자수가 최대입니다. (내용은 332자 이내)");
        }

        if (vo.getTitle() != null && vo.getStartDate() != null && vo.getEndDate() != null) {
            LocalDate startDate = LocalDate.parse(vo.getStartDate());
            LocalDate endDate = LocalDate.parse(vo.getEndDate());
            if (endDate.isBefore(startDate)) {
                throw new InvalidInputException("시작일은 종료일보다 빨라야합니다.");
            }
        }

        return dao.editCalendar(vo);
    }

    public List<CalendarVo> searchListCalendarData(String keyword,int startNum, int endNum) {
        return dao.searchListCalendarData(keyword, startNum, endNum);
    }


    public int getSearchCalendarCnt(String keyword) {
        return dao.getSearchCalendarCnt(keyword);
    }
}
