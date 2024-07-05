package com.groupware.orca.sick.service;

import com.groupware.orca.sick.dao.SickDao;
import com.groupware.orca.sick.vo.SickVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SickService {

    private final SickDao dao;

    public void enrollSick(SickVo vo) {
        dao.enrollSick(vo);
    }
}
