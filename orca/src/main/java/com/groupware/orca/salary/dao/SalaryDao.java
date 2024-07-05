//package com.groupware.orca.salary.dao;
//
//import com.groupware.orca.salary.mapper.SalaryMapper;
//import com.groupware.orca.salary.vo.PayrollVo;
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
//    public int salaryWrite(UserVo vo, RatesVo rvo, double holidayTime, double overTime, double person, double position) {
//        System.out.println("SalaryDao.salaryWrite");
//        return mapper.salaryWrite(vo,rvo,holidayTime,overTime,person,position);
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
//    public SalaryVo getSalaryVo(String empNo) {
//        System.out.println("empNo-dao-svo: " + empNo);
//        return mapper.getSalaryVo(empNo);
//    }
//
//
//    public RatesVo getRatesVo() {
//        System.out.println("Dao-rvo called");
//        return mapper.getRatesVo();
//    }
//
//    public UserVo getUserVo(String empNo) {
//        return mapper.getUserVo(empNo);
//    }
//
//    //TODO swy 삭제
//    public RatesVo getRates() {
//        return mapper.getRates();
//    }
//
//
//    public UserVo getPersonalInformation(String empNo) {
//        return mapper.getPersonalInformation(empNo);
//    }
//
//    public int insertPayroll(PayrollVo vo) {
//        return mapper.insertPayroll(vo);
//    }
//}
