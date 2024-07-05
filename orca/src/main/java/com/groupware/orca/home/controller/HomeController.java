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
        return "home";
    }
}

