package com.groupware.orca.department.humanResources.personnelManagement.controller;


import com.groupware.orca.department.humanResources.personnelManagement.service.PersonnelManagementService;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("orca/humanResources")
@RequiredArgsConstructor
public class PersonnelManagementController {
    private final PersonnelManagementService service;

    @GetMapping("showEmployeeRegistration")
    public String showEmployeeRegistration() {
        return "humanResources/personnelManagement/employeeRegistration";
    }
    @PostMapping("employeeRegistration")
    @ResponseBody
    public int employeeRegistration(UserVo employeeVo){
        System.out.println("employeeVo = " + employeeVo);
//        int result = service.employeeRegistration();

        return 1;
    }
}
