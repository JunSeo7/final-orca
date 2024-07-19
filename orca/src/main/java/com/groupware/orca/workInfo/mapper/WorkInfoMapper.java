package com.groupware.orca.workInfo.mapper;

import com.groupware.orca.workInfo.vo.WorkInfoVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkInfoMapper {

    //휴일근무

    //개인 근무 정보 조회
    @Select("SELECT P.NAME as name, WORK_DATE as workDate, TO_CHAR(START_TIME, 'hh24:mi:ss') as startTime, TO_CHAR(END_TIME, 'hh24:mi:ss') as endTime, OVERTIME_WORK as overtimeWork, HOLIDAY_WORK as holidayWork " +
            "FROM WORK_INFO W JOIN PERSONNEL_INFORMATION P ON W.EMP_NO = P.EMP_NO " +
            "WHERE W.EMP_NO = #{empNo}")
    List<WorkInfoVo> workList(int empNo);

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
    @Select("SELECT START_TIME FROM WORK_INFO WHERE EMP_NO = #{empNo} AND TRUNC(WORK_DATE) = TRUNC(SYSDATE)")
    String getStartWorkTime(int empNo, String workDate);

    // 퇴근 시간 조회
    @Select("SELECT END_TIME FROM WORK_INFO WHERE EMP_NO = #{empNo} AND TRUNC(WORK_DATE) = TRUNC(SYSDATE)")
    String getEndWorkTime(int empNo, String workDate);

    // 모든 사원 근무 정보 조회
    @Select("SELECT P.NAME as name, WORK_DATE as workDate, D.PARTNAME, PT.NAME_OF_POSITION, DT.TEAM_NAME, TO_CHAR(START_TIME, 'hh24:mi:ss') as startTime, TO_CHAR(END_TIME, 'hh24:mi:ss') as endTime, OVERTIME_WORK as overtimeWork, HOLIDAY_WORK as holidayWork FROM WORK_INFO W JOIN PERSONNEL_INFORMATION P ON W.EMP_NO = P.EMP_NO LEFT JOIN DEPARTMENT D ON P.DEPT_CODE = D.DEPT_CODE LEFT JOIN DEPARTMENT_TEAM DT ON P.TEAM_CODE = DT.TEAM_CODE LEFT JOIN POSITION PT ON P.POSITION_CODE = PT.POSITION_CODE")
    List<WorkInfoVo> allWorkList();
}
