package com.groupware.orca.salary.service;

import com.groupware.orca.salary.dao.PersonSalaryDao;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonSalaryService {

    private final PersonSalaryDao dao;


    public SalaryVo getPersonSalaryByOne(String payrollNo, String empNo, UserVo vo) {

        return dao.getPersonSalaryByOne(payrollNo,empNo,vo);
    }


    public List<SalaryVo> getPersonSalaryList(String empNo, UserVo vo) {
        return dao.getPersonSalaryList(empNo,vo);
    }
}
