package com.groupware.orca.department.managementSupport;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("orca/managementSupport")
@RequiredArgsConstructor
public class MainController {

    @GetMapping("main")
    public String showHumanResourcesDepartmentMain() {
        return "managementSupport/main";
    }
}
