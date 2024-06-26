package com.groupware.orca.user.mapper;

import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT EMP_NO, DEPT_CODE, POSITION_CODE, TEAM_CODE, NAME, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, DATE_OF_EMPLOYMENT, HEIGHT, WEIGHT, BLOOD_TYPE, RELIGION, SALARY, BANK_NUMBER, LEAVING_DATE, IMG_ORIGIN_NAME, IMG_CHANGE_NAME, OTP_KEY, SICK_DATE, VACATION_DATE, FROM PERSONNEL_INFORMATION")
    UserVo login(UserVo vo);
}
