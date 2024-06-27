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

    public List<WorkInfoVo> workList() {

        return dao.workList();
    }

    public WorkInfoVo startWork(WorkInfoVo vo) {
        return dao.startWork(vo);
    }

    public WorkInfoVo endWork(WorkInfoVo vo) {
        dao.endWork(vo);
        dao.overTimeWork(vo);
        return vo;
    }

    public WorkInfoVo overTimeWork(WorkInfoVo vo) {

        String endTimeStr = vo.getEnd_time();  // end_time은 endWork 호출 후 vo에 업데이트된 값이라고 가정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);
        LocalDateTime standardTime = endTime.toLocalDate().atTime(LocalTime.of(18, 0));

        // 퇴근 시간이 18:00을 초과했는지 확인합니다.
        if (endTime.isAfter(standardTime)) {
            // 초과된 시간을 계산합니다.
            int overtimeHours = endTime.getHour() - standardTime.getHour();
            if (endTime.getMinute() > 0 || endTime.getSecond() > 0) {
                overtimeHours += 1;  // 분이나 초가 있으면 시간 단위로 올림
            }

            // 초과된 시간을 WorkInfoVo에 설정합니다.
            vo.setOvertime_work(overtimeHours);
            dao.overTimeWork(vo);
        }
        return vo;
    }
}
