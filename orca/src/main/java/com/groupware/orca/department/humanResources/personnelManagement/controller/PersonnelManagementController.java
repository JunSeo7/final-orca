package com.groupware.orca.department.humanResources.personnelManagement.controller;


import com.groupware.orca.board.vo.BoardFileVo;
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

            uploadDir += "/user";

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
}
