package com.groupware.orca.user.dao;

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
}
