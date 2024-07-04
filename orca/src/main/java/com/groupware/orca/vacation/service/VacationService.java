package com.groupware.orca.vacation.service;

import com.groupware.orca.document.dao.DocumentDao;
import com.groupware.orca.vacation.dao.VacationDao;
import com.groupware.orca.vacation.vo.VacationRefVo;
import com.groupware.orca.vacation.vo.VacationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacationService {

    private final VacationDao dao;
    private final DocumentDao DDao;

    public void enrollVacation(VacationVo vo) {
        dao.enrollVacation(vo);
    }


    public List<VacationRefVo> loadVacationCode() {
        return dao.loadVacationCode();

    }
}
