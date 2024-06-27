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

    public List<WorkInfoVo> workList() {

        return mapper.workList();
    }

    public WorkInfoVo startWork(WorkInfoVo vo) {
        return mapper.startWork(vo);
    }
}
