package com.groupware.orca.vacation.mapper;

import com.groupware.orca.vacation.vo.VacationRefVo;
import com.groupware.orca.vacation.vo.VacationVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VacationMapper {

    // 휴가 신청
    @Insert("""
                <script>
                    INSERT INTO VACATION_MANAGEMENT (
                    VACATION_NO, VACATION_CODE, EMP_NO, DOC_NO, REG_DATE, START_DATE, EXPIRY_DATE
                        <if test="reason != null"> , REASON </if>
                    )
                    VALUES (
                    SEQ_VACATION_MANAGEMENT.NEXTVAL, #{vacationCode}, #{empNo}, #{docNo}, SYSDATE, #{startDate}, #{expiryDate}
                        <if test="reason != null">, #{reason} </if>
                    )
                </script>
            """)
    void enrollVacation(VacationVo vo);

    // 휴가 코드 불러오기
    @Select("SELECT VACATION_CODE, VACATION_NAME FROM VACATION_REFERENCE")
    List<VacationRefVo> loadVacationCode();
}
