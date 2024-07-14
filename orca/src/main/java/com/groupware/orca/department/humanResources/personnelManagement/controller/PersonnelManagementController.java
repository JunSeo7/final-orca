package com.groupware.orca.department.humanResources.personnelManagement.controller;


import com.groupware.orca.department.humanResources.personnelManagement.service.PersonnelManagementService;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("orca/humanResources")
@RequiredArgsConstructor
public class PersonnelManagementController {
    private final PersonnelManagementService service;

    @GetMapping("showEmployeeRegistration")
    public String showEmployeeRegistration() {
        return "humanResources/personnelManagement/employeeRegistration";
    }

    @GetMapping("getSelects")
    @ResponseBody
    public List<List> getSelects(){
        List<List> selects = service.getSelects();
        return selects;
    }

    @PostMapping("employeeRegistration")
    @ResponseBody
    public int employeeRegistration(UserVo newEmployeeVo){
        System.out.println("employeeVo = " + newEmployeeVo);
        int result = service.employeeRegistration(newEmployeeVo);
        return result;
    }
}
