package com.groupware.orca.workInfo.dao;

import com.groupware.orca.workInfo.mapper.WorkInfoMapper;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WorkInfoDao {

    private final WorkInfoMapper mapper;

    public List<WorkInfoVo> workList(int empNo) {
        List<WorkInfoVo> workList = mapper.workList(empNo);
        for (WorkInfoVo workInfo : workList) {
            System.out.println(workInfo);
        }
        return workList;
    }

    public void startWork(WorkInfoVo vo) {
        mapper.startWork(vo);
    }

    public void endWork(WorkInfoVo vo) {
        mapper.endWork(vo);
    }

    public void overTimeWork(WorkInfoVo vo) {
        mapper.overTimeWork(vo);
    }

    public String getStartWorkTime(int empNo, String workDate) {
        return mapper.getStartWorkTime(empNo, workDate);
    }

    public String getEndWorkTime(int empNo, String workDate) {
        return mapper.getEndWorkTime(empNo, workDate);
    }
}
