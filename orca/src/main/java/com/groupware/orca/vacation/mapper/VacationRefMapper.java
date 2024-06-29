package com.groupware.orca.vacation.mapper;

import com.groupware.orca.vacation.vo.VacationRefVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VacationRefMapper {

    @Insert("INSERT INTO VACATION_REFERENCE (VACATION_CODE, VACATION_NAME) " +
            "VALUES (#{vacationCode}, #{vacationName})")
    void registrationVCode(VacationRefVo vo);

    @Update("UPDATE VACATION_REFERENCE SET VACATION_NAME = #{vacationName} " +
            "WHERE VACATION_CODE = #{vacationCode}")
    void editVCode(VacationRefVo vo);

    @Delete("DELETE VACATION_REFERENCE WHERE VACATION_CODE = #{vacationCode}")
    int deleteVCode(String vacationCode);
}
