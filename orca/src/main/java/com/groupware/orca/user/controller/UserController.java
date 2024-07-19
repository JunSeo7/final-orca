package com.groupware.orca.user.controller;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.user.service.UserService;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("orca/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("login")
    public String login() {
        return "user/login";
    }


    @PostMapping("login")
    public String login(UserVo vo, HttpSession httpSession, Model model) {

        httpSession.removeAttribute("loginUserVo");

        System.out.println("vo = " + vo);
        // 시연을 위한 임시 로그인 ~~
        if (vo.getEmpNo() == 2024070096 || vo.getEmpNo() == 2024070012 || vo.getEmpNo() == 2024070016 || vo.getEmpNo() == 2024070011  || vo.getEmpNo() == 2024070091) {
            UserVo loginUserVo = service.TestLogin(vo.getEmpNo());
            if (loginUserVo == null) {
                model.addAttribute("message", "아이디 또는 비밀번호를 다시 확인해주세요.");
                return "user/login";
            }
            httpSession.setAttribute("loginUserVo", loginUserVo);
            return "redirect:/orca/home";
        }

        // 시큐리티 로그인
        UserVo loginUserVo = service.login(vo);

        if (loginUserVo == null) {
            model.addAttribute("message", "아이디 또는 비밀번호를 다시 확인해주세요.");
            return "user/login";
        }

        httpSession.setAttribute("loginUserVo", loginUserVo);
        return "redirect:/orca/home";
    }

    @GetMapping("getUserVo")
    @ResponseBody
    public UserVo getUserVo(HttpSession httpSession) {
        int userNo = ((UserVo) httpSession.getAttribute("loginUserVo")).getEmpNo();

        UserVo userVo = service.getUserVo(userNo);
        return userVo;
    }

    @GetMapping("logout")
    @ResponseBody
    public int logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        boolean logout = false;
        if(session != null) {
            session.invalidate();
            logout = true;
        }
        return logout ? 1 : 0;
    }

    @GetMapping("showChangePassword")
    public String changePassword() {
        return "user/changePassword";
    }
    @PostMapping("changePassword")
    @ResponseBody
    public int changePassword(@RequestParam("currentPassword")String currentPassword, @RequestParam("newPassword") String newPassword, HttpSession httpSession) {
        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");

        int result = service.changePassword(currentPassword, newPassword, userVo);
        System.out.println(result);
        return result;
    }

    @GetMapping("showDepartmentLogin")
    public String showDepartmentLogin(){
        return "user/departmentLogin";
    }

    @PostMapping("departmentLogin")
    @ResponseBody
    public DepartmentVo departmentLogin(HttpSession httpSession, DepartmentVo departmentVo){
        UserVo userVo = (UserVo) httpSession.getAttribute("loginUserVo");
        departmentVo.setDeptCode(userVo.getDeptCode());

        System.out.println("departmentVo = " + departmentVo);

        DepartmentVo loginDepartVo = service.departmentLogin(departmentVo);

        return loginDepartVo;
    }

}
