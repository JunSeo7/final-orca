package com.groupware.orca.vacation.dao;

import com.groupware.orca.vacation.mapper.VacationMapper;
import com.groupware.orca.vacation.vo.VacationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VacationDao {

    private final VacationMapper mapper;

    public int enrollVacation(VacationVo vo) {
        return mapper.enrollVacation(vo);
    }


}
