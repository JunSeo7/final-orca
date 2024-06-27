package com.groupware.orca.home.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("orca")
public class HomeController {

    @GetMapping("home")
    public String home(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("loginUserVo") == null){
            model.addAttribute("message", "로그인 후 이용해주세요.");
            return "redirect:/orca/user/login";
        }
        return "home";
    }
}

