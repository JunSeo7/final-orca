package com.groupware.orca.salary.dao;

import com.groupware.orca.salary.mapper.PersonSalaryMapper;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonSalaryDao {

    private final PersonSalaryMapper mapper;


    public SalaryVo getPersonSalaryByNo(UserVo userVo, String payrollNo) {
        return mapper.getPersonSalaryByNo( userVo,payrollNo);
    }

    public List<SalaryVo> getPersonSalaryList(UserVo userVo) {
        return mapper.getPersonSalaryList( userVo);

    }
}