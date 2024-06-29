package com.groupware.orca.vacation.service;

import com.groupware.orca.vacation.dao.VacationRefDao;
import com.groupware.orca.vacation.vo.VacationRefVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VacationRefService {

    private final VacationRefDao dao;

    public void registrationVCode(VacationRefVo vo) {
        dao.registrationVCode(vo);
    }

    public void editVCode(VacationRefVo vo) {
        dao.editVCode(vo);
    }

    public int deleteVCode(String vacationCode) {
        return dao.deleteVCode(vacationCode);

    }
}
