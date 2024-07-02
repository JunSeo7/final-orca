package com.groupware.orca.etc.controller;

import com.groupware.orca.etc.service.EtcService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/etc")
public class EtcController {

    private final EtcService service;

    @GetMapping("etcWrite")
    public String etcWrite(){
        return "/etc/etcWrite";
    }
}
