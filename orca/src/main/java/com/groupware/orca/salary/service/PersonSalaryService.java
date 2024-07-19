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



    public SalaryVo getPersonSalary(String payrollNo, String empNo, UserVo userVo) {

        return dao.getPersonSalary(payrollNo,empNo,userVo);
    }


    public List<SalaryVo> getPersonSalaryList(String empNo, UserVo userVo) {
        return dao.getPersonSalaryList(empNo,userVo);

}
