package com.groupware.orca.user.controller;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.user.service.UserService;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orca/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService service;

    @GetMapping("getUserVo")
    public ResponseEntity<UserVo> getUserVo(HttpSession httpSession) {
        UserVo loggedInUser = (UserVo) httpSession.getAttribute("loginUserVo");
        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        int userNo = loggedInUser.getEmpNo();
        UserVo userVo = service.getUserVo(userNo);
        if (userVo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userVo);
    }


    @GetMapping("logout")
    public ResponseEntity<Integer> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return ResponseEntity.ok(1);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PostMapping("changePassword")
    public ResponseEntity<Integer> changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            HttpSession httpSession) {

        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");

        if (userVo == null) {
            // 사용자 정보가 세션에 없을 경우, 인증되지 않은 상태로 응답
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        int result = service.changePassword(currentPassword, newPassword, userVo);

        if (result == 1) {
            // 비밀번호 변경 성공 시, result 값을 본문으로 반환
            return ResponseEntity.ok(result);
        } else if (result == -1) {
            // 현재 비밀번호 불일치 시, BAD_REQUEST 상태 코드와 메시지 본문 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        // 서버 오류가 발생했을 경우, INTERNAL_SERVER_ERROR 상태 코드와 메시지 본문 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }



    @PostMapping("departmentLogin")
    public ResponseEntity<DepartmentVo> departmentLogin(HttpSession httpSession, DepartmentVo departmentVo) {
        httpSession.removeAttribute("loginDeptVo");
        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");
        if (userVo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        departmentVo.setDeptCode(userVo.getDeptCode());
        DepartmentVo loginDeptVo = service.departmentLogin(departmentVo);
        if (loginDeptVo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        httpSession.setAttribute("loginDeptVo", loginDeptVo);
        return ResponseEntity.ok(loginDeptVo);
    }

}
