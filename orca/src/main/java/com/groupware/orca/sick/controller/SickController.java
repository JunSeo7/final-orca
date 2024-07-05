package com.groupware.orca.sick.controller;

import com.groupware.orca.sick.service.SickService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/sick")
public class SickController {

    private final SickService service;

    // 병가 신청 화면
    @GetMapping("sickLeave")
    public String sickWrite(){
        return "/sick/sickLeave";
    }


}
