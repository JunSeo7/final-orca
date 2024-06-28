package com.groupware.orca.workInfo.service;

import com.groupware.orca.workInfo.dao.WorkInfoDao;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkInfoService {

    private final WorkInfoDao dao;

    // 근무 정보 조회
    public List<WorkInfoVo> workList() {
        return dao.workList();
    }

    // 출근
    public WorkInfoVo startWork(WorkInfoVo vo) {
        return dao.startWork(vo);
    }

    // 퇴근
    public WorkInfoVo endWork(WorkInfoVo vo) {
        dao.endWork(vo);
        dao.overTimeWork(vo);
        return vo;
    }

    // 연장근무
    public WorkInfoVo overTimeWork(WorkInfoVo vo) {

        String endTimeStr = vo.getEnd_time();  // end_time은 endWork 호출 후 vo에 업데이트된 값이라고 가정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);
        LocalDateTime standardTime = endTime.toLocalDate().atTime(LocalTime.of(18, 0));

        // 퇴근 시간 18:00 넘는지 체크.
        if (endTime.isAfter(standardTime)) {
            // 넘은 시간 계산.
            int overtimeHours = endTime.getHour() - standardTime.getHour();
            if (endTime.getMinute() > 0 || endTime.getSecond() > 0) {
                overtimeHours += 1;  // 분이나 초가 있으면 시간 단위로 올림
            }

            vo.setOvertime_work(overtimeHours);
        } else {
            vo.setOvertime_work(0);
        }
        dao.overTimeWork(vo);
        return vo;
    }
}
