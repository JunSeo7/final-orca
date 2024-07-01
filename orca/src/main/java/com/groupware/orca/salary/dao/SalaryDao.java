package com.groupware.orca.salary.dao;

import com.groupware.orca.salary.mapper.SalaryMapper;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SalaryDao {

    private final SalaryMapper mapper;

    public int salaryWrite(UserVo userVo, SalaryVo vo, RatesVo rvo, double holidayTime) {
        return mapper.salarywrite(vo);

    }

    public int ratesWrite(RatesVo vo) {
        return mapper.ratesWrite(vo);
    }
}
