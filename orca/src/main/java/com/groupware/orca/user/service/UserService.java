package com.groupware.orca.user.service;

import com.groupware.orca.user.dao.UserDao;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private static UserDao dao;

    public UserVo login(UserVo vo) {
        UserVo userVo = dao.login(vo);
        return  userVo;
    }
}
