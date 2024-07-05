package com.groupware.orca.sick.dao;

import com.groupware.orca.sick.mapper.SickMapper;
import com.groupware.orca.sick.vo.SickVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SickDao {

    private final SickMapper mapper;

    public void enrollSick(SickVo vo) {
        mapper.enrollSick(vo);
    }
}
