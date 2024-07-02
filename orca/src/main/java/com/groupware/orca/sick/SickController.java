package com.groupware.orca.sick;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("orca/sick")
public class SickController {

    @GetMapping("sickWrite")
    public String sickWrite(){
        return "/sick/sickWrite";
    }

}
