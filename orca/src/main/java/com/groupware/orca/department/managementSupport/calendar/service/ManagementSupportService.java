package com.groupware.orca.department.managementSupport.calendar.service;

import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.department.managementSupport.calendar.dao.ManagementSupportDao;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ManagementSupportService {
    private final ManagementSupportDao dao;

    public int createCalendarCompany(CalendarVo vo) throws InvalidInputException {
        if (vo.getTitle().length() > 13) {
            throw new InvalidInputException("글자수가 최대입니다. (제목은 13자 이내)");
        }
        if (vo.getContent().length() > 332) {
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
        return dao.createCalendarCompany(vo);
    }
}
