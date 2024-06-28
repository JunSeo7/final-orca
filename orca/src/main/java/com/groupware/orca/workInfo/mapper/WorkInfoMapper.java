package com.groupware.orca.workInfo.mapper;

import com.groupware.orca.workInfo.vo.WorkInfoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WorkInfoMapper {

    //연장근무

    //휴일근무

    // 근무 정보 조회
    @Select("SELECT P.NAME , WORK_DATE , TO_CHAR(START_TIME, 'hh24:mi:ss') , TO_CHAR(END_TIME, 'hh24:mi:ss') , OVERTIME_WORK , HOLIDAY_WORK " +
            "FROM WORK_INFO W JOIN PERSONNEL_INFORMATION P ON W.EMP_NO = P.EMP_NO" +
            "WHERE P.EMP_NO = #{no}")
    List<WorkInfoVo> workList();

    //출근
    @Insert("INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME) " +
            "VALUES (SEQ_WORK_INFO.NEXTVAL, #{no}, SYSDATE, SYSDATE)")
    WorkInfoVo startWork(WorkInfoVo vo);

    //퇴근
    @Update("UPDATE WORK_INFO SET END_TIME = SYSDATE WHERE WORK_NO = #{workNo} AND EMP_NO = {empNo}")
    WorkInfoVo endWork(WorkInfoVo vo);

    // 연장 근무
    @Update("UPDATE WORK_INFO SET OVERTIME_WORK = NUMTODSINTERVAL((END_TIME - START_TIME) * 24 * 60 * 60, 'SECOND') WHERE WORK_NO = #{workNo} AND EMP_NO = {empNo}")
    WorkInfoVo overTimeWork(WorkInfoVo vo);
}
