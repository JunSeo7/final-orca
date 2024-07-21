package com.groupware.orca.department.humanResources.personnelManagement.controller;


import com.groupware.orca.board.vo.BoardFileVo;
import com.groupware.orca.calendar.vo.CalendarVo;
import com.groupware.orca.common.vo.PageVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.common.vo.SearchVo;
import com.groupware.orca.department.humanResources.personnelManagement.service.PersonnelManagementService;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Controller
@RequestMapping("orca/humanResources")
@RequiredArgsConstructor
public class PersonnelManagementController {

    @GetMapping("showEmployeeRegistration")
    public String showEmployeeRegistration() {
        return "humanResources/personnelManagement/employeeRegistration";
    }

    @GetMapping("showEmployeeList")
    public String showEmployeeList() {
        return "humanResources/personnelManagement/showEmployeeList";
    }

    @GetMapping("showEmployeeDetails")
    public String showEmployeeDetails() {
        return "humanResources/personnelManagement/showEmployeeDetails";
    }

    @GetMapping("showEmployeeEdit")
    public String showEmployeeEdit() {
        return "humanResources/personnelManagement/showEmployeeEdit";
    }

    @GetMapping("searchListEmployee")
    public String searchListCalendar() {
        return "humanResources/personnelManagement/showEmployeeList";
    }

}
