package com.groupware.orca.vacation.dao;

import com.groupware.orca.vacation.mapper.VacationRefMapper;
import com.groupware.orca.vacation.vo.VacationRefVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VacationRefDao {

    private final VacationRefMapper mapper;

    public void registrationVCode(VacationRefVo vo) {

        mapper.registrationVCode(vo);
    }

    public void editVCode(VacationRefVo vo) {
        mapper.editVCode(vo);
    }

    public int deleteVCode(String vacationCode) {
        return mapper.deleteVCode(vacationCode);

    }
}
