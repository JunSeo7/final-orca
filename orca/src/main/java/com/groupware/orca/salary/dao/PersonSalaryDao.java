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


    public SalaryVo getPersonSalaryByNo(String payrollNo, String empNo, UserVo userVo) {
        return mapper.getPersonSalaryByNo(payrollNo, empNo, userVo);
    }

    public List<SalaryVo> getPersonSalaryList(String empNo, UserVo userVo) {
        return mapper.getPersonSalaryList(empNo, userVo);

    }
}