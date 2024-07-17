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

    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final Logger log = LoggerFactory.getLogger(PersonnelManagementController.class);
    private final PersonnelManagementService service;

    @GetMapping("showEmployeeRegistration")
    public String showEmployeeRegistration() {
        return "humanResources/personnelManagement/employeeRegistration";
    }

    @GetMapping("getSelects")
    @ResponseBody
    public List<List> getSelects() {
        List<List> selects = service.getSelects();
        return selects;
    }

    @PostMapping("employeeRegistration")
    @ResponseBody
    public int employeeRegistration(UserVo newEmployeeVo, HttpServletRequest req) throws IOException {
        MultipartFile file = newEmployeeVo.getImage();
        System.out.println(file);
        if (file != null && !file.isEmpty()) {
            System.out.println("통과");
            String originFileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();

            if (!uploadDir.contains("user")) {
                uploadDir += "/user";
            }

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String random = UUID.randomUUID().toString();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String changeName = System.currentTimeMillis() + "_" + random + ext;

            FileOutputStream fos = new FileOutputStream(uploadDir + "/" + changeName);
            byte[] buf = new byte[1024];
            int size;
            while ((size = is.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }

            is.close();
            fos.close();
            newEmployeeVo.setImgOriginName(originFileName);
            newEmployeeVo.setImgChangeName(changeName);
        }
        System.out.println("employeeVo = " + newEmployeeVo);
        int result = service.employeeRegistration(newEmployeeVo);
        return result;
    }

    @GetMapping("showEmployeeList")
    public String showEmployeeList() {
        return "humanResources/personnelManagement/showEmployeeList";
    }

    @GetMapping("listEmployeePage")
    @ResponseBody
    public Pagination listEmployeePage(@RequestParam("page") int page) {
        PageVo pageVo = new PageVo();
        pageVo.setPage(page);
        pageVo.setPageSize(10);
        pageVo.setRecordSize(7);
        int totalRecordCount = service.getEmployeeCnt();
        Pagination pagination = new Pagination(totalRecordCount, pageVo);
        return pagination;
    }

    @GetMapping("listEmployeeData")
    @ResponseBody
    public List<UserVo> listEmployeeData(@RequestParam("startNum") int startNum,
                                         @RequestParam("endNum") int endNum) {
        List<UserVo> employeeVoList = service.listEmployeeData(startNum, endNum);
        return employeeVoList;
    }

    @GetMapping("showEmployeeDetails")
    public String showEmployeeDetails() {
        return "humanResources/personnelManagement/showEmployeeDetails";
    }

    @GetMapping("getEmployeeDetails")
    @ResponseBody
    public UserVo getEmployeeDetails(@RequestParam("empNo") int empNo) {
        UserVo employeeInfo = service.getEmployeeDetails(empNo);
        System.out.println(employeeInfo);
        return employeeInfo;
    }

    @GetMapping("showEmployeeEdit")
    public String showEmployeeEdit() {
        return "humanResources/personnelManagement/showEmployeeEdit";
    }

    @PostMapping("updateEmployee")
    @ResponseBody
    public int updateEmployee(UserVo employeeVo) throws IOException {
        MultipartFile file = employeeVo.getImage();
        System.out.println(file);
        if (file != null && !file.isEmpty()) {
            System.out.println("통과");
            String originFileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();

            if (!uploadDir.contains("user")) {
                uploadDir += "/user";
            }

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String random = UUID.randomUUID().toString();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String changeName = System.currentTimeMillis() + "_" + random + ext;

            FileOutputStream fos = new FileOutputStream(uploadDir + "/" + changeName);
            byte[] buf = new byte[1024];
            int size;
            while ((size = is.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }

            is.close();
            fos.close();
            employeeVo.setImgOriginName(originFileName);
            employeeVo.setImgChangeName(changeName);
        }
        System.out.println("employeeVo = " + employeeVo);
        int result = service.updateEmployee(employeeVo);
        return result;
    }

    @PostMapping("deleteEmployee")
    @ResponseBody
    public int deleteEmployee(@RequestParam("empNo") int empNo) {
        int result = service.deleteEmployee(empNo);
        return result;
    }

    @GetMapping("searchListEmployee")
    public String searchListCalendar() {
        System.out.println("요청 넘어옴");
        return "humanResources/personnelManagement/showEmployeeList";
    }

    @GetMapping("searchListEmployeePage")
    @ResponseBody
    public Pagination searchListEmployeePage(SearchVo searchVo) {
        System.out.println("searchVo = " + searchVo);
        searchVo.setPageSize(10);
        searchVo.setRecordSize(7);
        if (searchVo.getKeyword() != null) {
            searchVo.setKeyword(searchVo.getKeyword().replaceAll("\\s+", ""));
        }
        int totalRecordCount = service.getSearchEmployeeCnt(searchVo);
        Pagination pagination = new Pagination(totalRecordCount, searchVo);
        return pagination;
    }

    @GetMapping("searchListEmployeeData")
    @ResponseBody
    public List<UserVo> searchListEmployeeData(@RequestParam("startNum") int startNum,
                                               @RequestParam("endNum") int endNum,
                                               @RequestParam("keyword") String keyword,
                                               @RequestParam("searchType") String searchType) {
        if (keyword != null) {
            keyword = keyword.replaceAll("\\s+", "");
        }

        List<UserVo> employeeVoList = service.searchListEmployeeData(keyword, startNum, endNum, searchType);
        return employeeVoList;
    }
}
