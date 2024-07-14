package com.groupware.orca.user.service;

import com.groupware.orca.user.dao.UserDao;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao dao;
    private final BCryptPasswordEncoder encoder;

    public UserVo login(UserVo vo) {
        UserVo userVo = dao.login(vo);

        boolean isMatch = encoder.matches(vo.getPassword(), userVo.getPassword());

        return isMatch ? userVo : null;
    }

    public UserVo getUserVo(String userNo) {
        return dao.getUserVo(userNo);
    }
}
