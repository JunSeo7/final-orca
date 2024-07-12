package com.groupware.orca.salary.dao;

import com.groupware.orca.salary.mapper.PersonSalaryMapper;
import com.groupware.orca.salary.vo.SalaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonSalaryDao {

    private final PersonSalaryMapper mapper;


    public SalaryVo getPersonSalary(String payrollNo, String empNo) {
        return mapper.getPersonSalary(payrollNo,empNo);
    }

    public List<SalaryVo> getPersonSalaryList(String empNo) {
        return mapper.getPersonSalaryList(empNo);
    }
}
