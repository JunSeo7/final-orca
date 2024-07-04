package com.groupware.orca.vacation.dao;

import com.groupware.orca.vacation.mapper.VacationMapper;
import com.groupware.orca.vacation.vo.VacationRefVo;
import com.groupware.orca.vacation.vo.VacationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VacationDao {

    private final VacationMapper mapper;

    public void enrollVacation(VacationVo vo) {
        mapper.enrollVacation(vo);
    }


    public List<VacationRefVo> loadVacationCode() {
        return mapper.loadVacationCode();

    }
}
