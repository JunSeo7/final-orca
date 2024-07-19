package com.groupware.orca.vacation.mapper;

import com.groupware.orca.vacation.vo.VacationRefVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VacationRefMapper {

    @Insert("INSERT INTO VACATION_REFERENCE (VACATION_CODE, VACATION_NAME) " +
            "VALUES (#{vacationCode}, #{vacationName})")
    void registrationVCode(VacationRefVo vo);

    @Update("UPDATE VACATION_REFERENCE SET VACATION_NAME = #{vacationName}" +
            "WHERE VACATION_CODE = #{vacationCode}")
    void editVCode(VacationRefVo vo);

    @Delete("""
                <script>
                    DELETE FROM VACATION_REFERENCE WHERE VACATION_CODE IN
                    <foreach item='code' collection='vacationCode' open='(' separator=',' close=')'>
                    #{code}
                    </foreach>
                </script>
            """
            )
    int deleteVCode(@Param("vacationCode") List<String> vacationCode);
}
