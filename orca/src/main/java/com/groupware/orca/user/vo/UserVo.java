package com.groupware.orca.user.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserVo {

    private int empNo;
    private String name;
    private String positionCode;
    private String deptCode;
    private String teamCode;
    private String gender;
    private String socialSecurityNo;
    private String password;
    private String phone;
    private String extensionCall;
    private String email;
    private String address;
    private String dateOfEmployment;
    private String height;
    private String weight;
    private String bloodType;
    private String religion;
    private double salary;
    private String bankNumber;
    private String leavingDate;
    private String imgOriginName;
    private String imgChangeName;
    private String sickDate;
    private String vacationDate;
    private String partName;
    private String nameOfPosition;
    private String teamName;
    private MultipartFile image;
}