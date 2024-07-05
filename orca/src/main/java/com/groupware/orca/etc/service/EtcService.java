package com.groupware.orca.etc.service;

import com.groupware.orca.etc.dao.EtcDao;
import com.groupware.orca.etc.vo.EtcRefVo;
import com.groupware.orca.etc.vo.EtcVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtcService {

    private final EtcDao dao;

    public List<EtcRefVo> loadEtcCode() {
      return dao.loadEtcCode();
    }

    public void enrollEtc(EtcVo vo) {
        dao.enrollEtc(vo);
    }
}
