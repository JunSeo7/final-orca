package com.groupware.orca.etc.dao;

import com.groupware.orca.etc.mapper.EtcMapper;
import com.groupware.orca.etc.vo.EtcRefVo;
import com.groupware.orca.etc.vo.EtcVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EtcDao {

    private final EtcMapper mapper;

    public List<EtcRefVo> loadEtcCode() {
        return mapper.loadEtcCode();
    }

    public void enrollEtc(EtcVo vo) {
        mapper.enrollEtc(vo);
    }
}
