package com.groupware.orca.etc.service;

import com.groupware.orca.etc.dao.EtcRefDao;
import com.groupware.orca.etc.vo.EtcRefVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EtcRefService {

    private final EtcRefDao dao;

    public int registrationEtcCode(EtcRefVo vo) {
        return dao.registrationEtcCode(vo);
    }

    public void editEtcCode(EtcRefVo vo) {
        dao.editEtcCode(vo);
    }

    public int deleteECode(String etcCode) {
        return dao.deleteECode(etcCode);
    }
}
