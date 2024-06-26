package com.groupware.orca.user.mapper;

import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT EMP_NO FROM PERSONNEL_INFORMATION WHERE EMP_NO = #{empNo} AND PASSWORD = #{password}")
    UserVo login(UserVo vo);
}
