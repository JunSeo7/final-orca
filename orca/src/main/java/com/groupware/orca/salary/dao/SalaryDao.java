package com.groupware.orca.salary.dao;

import com.groupware.orca.salary.mapper.SalaryMapper;
import com.groupware.orca.salary.vo.ClientVo;
import com.groupware.orca.salary.vo.RatesVo;
import com.groupware.orca.salary.vo.SalaryVo;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SalaryDao {

    private final SalaryMapper mapper;

    public int salaryWrite(UserVo vo,ClientVo clientVo,SalaryVo svo) {
        System.out.println("SalaryDao.salaryWrite");
        return mapper.salaryWrite(vo,clientVo,svo);

    }

    public int ratesWrite(RatesVo vo) {
        return mapper.ratesWrite(vo);
    }

    public List<SalaryVo> getSalaryList() {
        System.out.println("SalaryDao.getSalaryList");
        return mapper.getSalaryList();
    }


    public RatesVo getRatesVo() {
        return mapper.getRatesVo();
    }

    public UserVo getUserVo(String empNo) {
        return mapper.getUserVo(empNo);
    }



}
