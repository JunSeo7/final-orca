//package com.groupware.orca.salary.dao;
//
//import com.groupware.orca.salary.mapper.SalaryMapper;
//import com.groupware.orca.salary.vo.RatesVo;
//import com.groupware.orca.salary.vo.SalaryVo;
//import com.groupware.orca.user.vo.UserVo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class SalaryDao {
//
//    private final SalaryMapper mapper;
//
//    public double salaryWrite(UserVo vo, SalaryVo svo, RatesVo rvo, double holidayTime, double overTime, double person, double position) {
//        System.out.println("SalaryDao.salaryWrite");
//        return mapper.salaryWrite(vo,svo,rvo,holidayTime,overTime,person,position);
//
//    }
//
//    public int ratesWrite(RatesVo vo) {
//        return mapper.ratesWrite(vo);
//    }
//
//    public List<SalaryVo> getSalaryList() {
//        System.out.println("SalaryDao.getSalaryList");
//        return mapper.getSalaryList();
//    }
//
//    public SalaryVo getSalaryVo() {
//        return mapper.getSalaryVo();
//    }
//
//
//    public RatesVo getRatesVo() {
//        return mapper.getRatesVo();
//    }
//
//    public UserVo getUserVo() {
//        return mapper.getUserVo();
//    }
//}
