package com.groupware.orca.sick;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("orca/sick")
public class SickController {

    // 병가 신청 화면
    @GetMapping("sickLeave")
    public String sickWrite(){
        return "/sick/sickLeave";
    }

}
