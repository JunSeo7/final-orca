package com.groupware.orca.user.mapper;

import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT \n" +
            "    E.EMP_NO \n" +
            "    , E.NAME\n" +
            "    , E.IMG_CHANGE_NAME\n" +
            "    , T.TEAM_NAME\n" +
            "FROM PERSONNEL_INFORMATION E\n" +
            "JOIN DEPARTMENT D ON D.DEPT_CODE = E.DEPT_CODE\n" +
            "JOIN DEPARTMENT_TEAM T ON T.TEAM_CODE = E.TEAM_CODE\n" +
            "JOIN POSITION P ON P.POSITION_CODE = E.POSITION_CODE\n" +
            "WHERE EMP_NO = #{vo.empNo}")
    UserVo login(@Param("vo") UserVo vo);

    @Select("SELECT \n" +
            "    E.EMP_NO \n" +
            "    , E.NAME\n" +
            "    , E.IMG_CHANGE_NAME\n" +
            "    , T.TEAM_NAME\n" +
            "FROM PERSONNEL_INFORMATION E\n" +
            "JOIN DEPARTMENT D ON D.DEPT_CODE = E.DEPT_CODE\n" +
            "JOIN DEPARTMENT_TEAM T ON T.TEAM_CODE = E.TEAM_CODE\n" +
            "JOIN POSITION P ON P.POSITION_CODE = E.POSITION_CODE\n" +
            "WHERE EMP_NO = #{userNo}")
    UserVo TestLogin(@Param("userNo") int userNo);

    @Select("SELECT PI.EMP_NO, PI.NAME, PO.NAME_OF_POSITION, T.TEAM_NAME, PI.SOCIAL_SECURITY_NO, PI.PHONE, PI.EXTENSION_CALL, PI.EMAIL\n" +
            "FROM PERSONNEL_INFORMATION PI\n" +
            "JOIN DEPARTMENT_TEAM T ON PI.TEAM_CODE = T.TEAM_CODE \n" +
            "JOIN POSITION PO ON PI.POSITION_CODE = PO.POSITION_CODE\n" +
            "WHERE EMP_NO = #{userNo}")
    UserVo getUserVo(@Param("userNo") int userNo);
}
