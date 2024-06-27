package com.groupware.orca.workInfo.mapper;

import com.groupware.orca.workInfo.vo.WorkInfoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkInfoMapper {

    //출근

    //퇴근

    //연장근무

    //휴일근무

    // 근무 정보 조회
    @Select("SELECT P.NAME , WORK_DATE , TO_CHAR(START_TIME, 'hh24:mi:ss') , TO_CHAR(END_TIME, 'hh24:mi:ss') , OVERTIME_WORK , HOLIDAY_WORK " +
            "FROM WORK_INFO W JOIN PERSONNEL_INFORMATION P ON W.EMP_NO = P.EMP_NO" +
            "WHERE P.EMP_NO = #{no}")
    List<WorkInfoVo> workList();

    @Insert("")
    WorkInfoVo startWork(WorkInfoVo vo);
}
