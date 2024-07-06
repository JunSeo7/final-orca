package com.groupware.orca.department;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("humanResources")
public class humanResources {

    @GetMapping("main")
    public void showHumanResourcesDepartmentMain(){
        
    }
}
