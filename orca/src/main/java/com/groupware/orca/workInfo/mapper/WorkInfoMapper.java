package com.groupware.orca.workInfo.mapper;

import com.groupware.orca.common.vo.PageVo;
import com.groupware.orca.user.vo.UserVo;
import com.groupware.orca.workInfo.vo.WorkInfoVo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface WorkInfoMapper {

    //휴일근무

    //개인 근무 정보 조회
    @Select("SELECT P.NAME as name, TO_CHAR(W.WORK_DATE, 'YYYY-MM-DD') as workDate, TO_CHAR(START_TIME, 'hh24:mi:ss') as startTime, TO_CHAR(END_TIME, 'hh24:mi:ss') as endTime, OVERTIME_WORK as overtimeWork, HOLIDAY_WORK as holidayWork " +
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
    @Select("""
            <script>
                SELECT 
                    P.EMP_NO, 
                    P.NAME as name,
                    TO_CHAR(W.WORK_DATE, 'YYYY-MM-DD') as workDate,
                    D.PARTNAME,
                    PT.NAME_OF_POSITION,
                    DT.TEAM_NAME,
                    TO_CHAR(START_TIME, 'hh24:mi:ss') as startTime,
                    TO_CHAR(END_TIME, 'hh24:mi:ss') as endTime,
                    OVERTIME_WORK as overtimeWork,
                    HOLIDAY_WORK as holidayWork
                FROM WORK_INFO W
                JOIN PERSONNEL_INFORMATION P ON W.EMP_NO = P.EMP_NO
                LEFT JOIN DEPARTMENT D ON P.DEPT_CODE = D.DEPT_CODE
                LEFT JOIN DEPARTMENT_TEAM DT ON P.TEAM_CODE = DT.TEAM_CODE
                LEFT JOIN POSITION PT ON P.POSITION_CODE = PT.POSITION_CODE
                WHERE TRUNC(W.WORK_DATE) = TRUNC(SYSDATE)
                ORDER BY PT.POSITION_CODE DESC
            </script>
            """)
    List<WorkInfoVo> getAllWorkInfo();

    @Select("SELECT COUNT(*) FROM WORK_INFO W JOIN PERSONNEL_INFORMATION P ON W.EMP_NO = P.EMP_NO LEFT JOIN DEPARTMENT D ON P.DEPT_CODE = D.DEPT_CODE LEFT JOIN DEPARTMENT_TEAM DT ON P.TEAM_CODE = DT.TEAM_CODE LEFT JOIN POSITION PT ON P.POSITION_CODE = PT.POSITION_CODE")
    int dataCount();

    @Select("""
    <script>
        SELECT
            *
        FROM
        (
            SELECT
                A.*,
                ROWNUM AS RNUM
            FROM
            (
                SELECT 
                    P.EMP_NO, 
                    P.NAME as name,
                    TO_CHAR(W.WORK_DATE, 'YYYY-MM-DD') as workDate,
                    D.PARTNAME,
                    PT.NAME_OF_POSITION,
                    DT.TEAM_NAME,
                    TO_CHAR(START_TIME, 'hh24:mi:ss') as startTime,
                    TO_CHAR(END_TIME, 'hh24:mi:ss') as endTime,
                    OVERTIME_WORK as overtimeWork,
                    HOLIDAY_WORK as holidayWork
                FROM WORK_INFO W
                JOIN PERSONNEL_INFORMATION P ON W.EMP_NO = P.EMP_NO
                LEFT JOIN DEPARTMENT D ON P.DEPT_CODE = D.DEPT_CODE
                LEFT JOIN DEPARTMENT_TEAM DT ON P.TEAM_CODE = DT.TEAM_CODE
                LEFT JOIN POSITION PT ON P.POSITION_CODE = PT.POSITION_CODE
                ORDER BY PT.POSITION_CODE DESC
            ) A
        )
        WHERE RNUM BETWEEN #{startNum} AND #{endNum}
    </script>
    """)
    List<WorkInfoVo> getData(int startNum, int endNum);

    @Select("""
    <script>
        SELECT
            *
        FROM
        (
            SELECT
                A.*,
                ROWNUM AS RNUM
            FROM
            (
                SELECT 
                    P.EMP_NO, 
                    P.NAME as name,
                    TO_CHAR(W.WORK_DATE, 'YYYY-MM-DD') as workDate,
                    D.PARTNAME,
                    PT.NAME_OF_POSITION,
                    DT.TEAM_NAME,
                    TO_CHAR(START_TIME, 'hh24:mi:ss') as startTime,
                    TO_CHAR(END_TIME, 'hh24:mi:ss') as endTime,
                    OVERTIME_WORK as overtimeWork,
                    HOLIDAY_WORK as holidayWork
                FROM WORK_INFO W
                JOIN PERSONNEL_INFORMATION P ON W.EMP_NO = P.EMP_NO
                LEFT JOIN DEPARTMENT D ON P.DEPT_CODE = D.DEPT_CODE
                LEFT JOIN DEPARTMENT_TEAM DT ON P.TEAM_CODE = DT.TEAM_CODE
                LEFT JOIN POSITION PT ON P.POSITION_CODE = PT.POSITION_CODE
                <if test="name != null and name != ''">
                    AND P.NAME LIKE '%' || #{name} || '%'
                </if>
                <if test="partName != null and partName != ''">
                    AND D.PARTNAME LIKE '%' || #{partName} || '%'
                </if>
                <if test="startDate != null and startDate != ''">
                    AND W.WORK_DATE &gt;= TO_DATE(#{startDate}, 'YYYY-MM-DD')
                </if>
                <if test="endDate != null and endDate != ''">
                    AND W.WORK_DATE &lt;= TO_DATE(#{endDate}, 'YYYY-MM-DD')
                </if>
                ORDER BY PT.POSITION_CODE DESC
            ) A
        )
        WHERE RNUM BETWEEN #{startNum} AND #{endNum}
    </script>
""")
    List<WorkInfoVo> searchData(@Param("name") String name,
                                @Param("partName") String partName,
                                @Param("startDate") String startDate,
                                @Param("endDate") String endDate,
                                @Param("startNum") int startNum,
                                @Param("endNum") int endNum);

    @Select("SELECT * FROM WORK_INFO WHERE EMP_NO = #{empNo} AND WORK_DATE BETWEEN #{startOfWeek} AND #{endOfWeek}")
    List<WorkInfoVo> getWorkRecordsBetween(@Param("empNo") int empNo, @Param("startOfWeek") LocalDateTime startOfWeek, @Param("endOfWeek") LocalDateTime endOfWeek);
}
