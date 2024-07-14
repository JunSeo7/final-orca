package com.groupware.orca.user.mapper;

import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT P.EMP_NO, P.PASSWORD, D.PARTNAME FROM PERSONNEL_INFORMATION P JOIN DEPARTMENT D ON D.DEPT_CODE = P.DEPT_CODE WHERE EMP_NO = #{empNo}")
    UserVo login(UserVo vo);

    @Select("SELECT PI.EMP_NO, PI.NAME, PO.NAME_OF_POSITION, T.TEAM_NAME, PI.SOCIAL_SECURITY_NO, PI.PHONE, PI.EXTENSION_CALL, PI.EMAIL\n" +
            "FROM PERSONNEL_INFORMATION PI\n" +
            "JOIN DEPARTMENT_TEAM T ON PI.TEAM_CODE = T.TEAM_CODE \n" +
            "JOIN POSITION PO ON PI.POSITION_CODE = PO.POSITION_CODE\n" +
            "WHERE EMP_NO = #{userNo}")
    UserVo getUserVo(@Param("userNo") String userNo);
}
