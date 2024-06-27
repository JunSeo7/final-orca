package com.groupware.orca.workInfo.service;

import com.groupware.orca.workInfo.dao.WorkInfoDao;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
