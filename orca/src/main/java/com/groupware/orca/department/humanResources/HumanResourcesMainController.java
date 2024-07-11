package com.groupware.orca.department.humanResources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("orca/humanResources")
public class HumanResourcesMainController {

    @GetMapping("main")
    public String showHumanResourcesDepartmentMain() {
        return "humanResources/main";
    }

}
