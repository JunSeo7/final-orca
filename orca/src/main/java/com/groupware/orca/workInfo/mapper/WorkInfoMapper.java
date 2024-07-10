package com.groupware.orca.workInfo.mapper;

import com.groupware.orca.workInfo.vo.WorkInfoVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkInfoMapper {

    //휴일근무

    // 근무 정보 조회
    @Select("SELECT P.NAME , WORK_DATE , TO_CHAR(START_TIME, 'hh24:mi:ss') , TO_CHAR(END_TIME, 'hh24:mi:ss') , OVERTIME_WORK , HOLIDAY_WORK " +
            "FROM WORK_INFO W JOIN PERSONNEL_INFORMATION P ON W.EMP_NO = P.EMP_NO " +
            "WHERE W.EMP_NO = #{empNo}")
    List<WorkInfoVo> workList(String empNo);

    //출근
    @Insert("INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME) VALUES (#{workNo}, #{empNo}, SYSDATE ,SYSDATE)")
    @SelectKey(statement = "SELECT SEQ_WORK_INFO.NEXTVAL FROM DUAL", keyProperty = "workNo", before = true, resultType = String.class)
    void startWork(WorkInfoVo vo);

    //퇴근
    @Update("UPDATE WORK_INFO SET END_TIME = SYSDATE WHERE WORK_NO = #{workNo} AND EMP_NO = #{empNo}")
    void endWork(WorkInfoVo vo);

    // 연장 근무
    @Update("UPDATE WORK_INFO " +
            "SET OVERTIME_WORK = ROUND((EXTRACT(HOUR FROM (END_TIME - START_TIME))" +
            " + EXTRACT(DAY FROM (END_TIME - START_TIME)) * 24" +
            " + EXTRACT(MINUTE FROM (END_TIME - START_TIME)) / 60" +
            " + EXTRACT(SECOND FROM (END_TIME - START_TIME)) / 3600), 2) " +
            "WHERE WORK_NO = #{workNo} AND EMP_NO = #{empNo}")
    void overTimeWork(WorkInfoVo vo);

    // 출근 시간 조회
    @Select("SELECT START_TIME FROM WORK_INFO WHERE EMP_NO = 1 AND TRUNC(WORK_DATE) = TRUNC(SYSDATE)")
    String getStartWorkTime(String empNo, String workDate);

    // 퇴근 시간 조회
    @Select("SELECT END_TIME FROM WORK_INFO WHERE EMP_NO = 1 AND TRUNC(WORK_DATE) = TRUNC(SYSDATE)")
    String getEndWorkTime(String empNo, String workDate);
}
