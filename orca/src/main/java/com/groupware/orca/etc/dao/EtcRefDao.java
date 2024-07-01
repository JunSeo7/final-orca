package com.groupware.orca.etc.dao;

import com.groupware.orca.etc.mapper.EtcRefMapper;
import com.groupware.orca.etc.vo.EtcRefVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EtcRefDao {

    private final EtcRefMapper mapper;

    public int registrationEtcCode(EtcRefVo vo) {
        return mapper.registrationEtcCode(vo);
    }

    public void editEtcCode(EtcRefVo vo) {
        mapper.editEtcCode(vo);
    }

    public int deleteECode(String etcCode) {
        return mapper.deleteECode(etcCode);
    }
}
