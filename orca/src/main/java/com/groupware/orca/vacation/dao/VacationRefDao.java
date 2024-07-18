package com.groupware.orca.vacation.dao;

import com.groupware.orca.vacation.mapper.VacationRefMapper;
import com.groupware.orca.vacation.vo.VacationRefVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public void deleteVCode(List<String> vacationCode) {
        mapper.deleteVCode(vacationCode);

    }
}
