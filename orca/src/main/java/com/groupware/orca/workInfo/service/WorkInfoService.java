package com.groupware.orca.workInfo.service;

import com.groupware.orca.workInfo.dao.WorkInfoDao;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkInfoService {

    private final WorkInfoDao dao;
    private static final Logger logger = LoggerFactory.getLogger(WorkInfoService.class);

    // 근무 정보 조회
    public List<WorkInfoVo> workList(String empNo) {
        return dao.workList(empNo);
    }

    // 출근
    public String startWork(WorkInfoVo vo) {
        vo.setStartTime(String.valueOf(new Timestamp(System.currentTimeMillis())));
        dao.startWork(vo); // dao에서 반환하는 WorkInfoVo를 그대로 반환
        logger.info("StartWork service called, workNo: {}", vo.getWorkNo());
        return vo.getWorkNo(); // dao에서 반환하는 WorkInfoVo를 그대로 반환
    }

    // 퇴근
    public void endWork(WorkInfoVo vo) {
        logger.info("EndWork service called with vo: {}", vo);

        dao.endWork(vo);
        logger.info("EndWork DAO called");
        dao.overTimeWork(vo);
        logger.info("OverTimeWork DAO called");
    }

    // 연장근무
    public void overTimeWork(WorkInfoVo vo) {
        String endTimeStr = vo.getEndTime();  // end_time은 endWork 호출 후 vo에 업데이트된 값이라고 가정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);
        LocalDateTime standardTime = endTime.toLocalDate().atTime(LocalTime.of(18, 0));

        // 퇴근 시간 18:00 넘는지 체크.
        if (endTime.isAfter(standardTime)) {
            // 넘은 시간 계산.
            Duration duration = Duration.between(standardTime, endTime);
            long overtimeMinutes = duration.toMinutes();

            // 소수점 단위까지 포함한 시간 계산
            double overtimeHours = overtimeMinutes / 60.0;

            vo.setOvertimeWork(overtimeHours);
        } else {
            vo.setOvertimeWork(0);
        }
        dao.overTimeWork(vo);
    }
}
