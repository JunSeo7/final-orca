package com.groupware.orca.user.dao;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.user.mapper.UserMapper;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserDao {
    private final UserMapper mapper;

    public UserVo login(UserVo vo) {
        UserVo userVo = mapper.login(vo);
        return userVo;
    }

    public UserVo getUserVo(int  userNo) {
        return mapper.getUserVo(userNo);
    }

    public UserVo TestLogin(int empNo) {
        return mapper.TestLogin(empNo);
    }

    public int changePassword(String encPassword, UserVo userVo) {
        return mapper.changePassword(encPassword, userVo);
    }

    public DepartmentVo departmentLogin(DepartmentVo departmentVo) {
        return mapper.departmentLogin(departmentVo);
    }
}
