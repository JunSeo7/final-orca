package com.groupware.orca.department.humanResources.personnelManagement.controller;

import com.groupware.orca.common.vo.PageVo;
import com.groupware.orca.common.vo.Pagination;
import com.groupware.orca.common.vo.SearchVo;
import com.groupware.orca.department.humanResources.personnelManagement.service.PersonnelManagementService;
import com.groupware.orca.user.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orca/humanResources")
@RequiredArgsConstructor
public class PersonnelManagementRestController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final Logger log = LoggerFactory.getLogger(PersonnelManagementController.class);
    private final PersonnelManagementService service;

    @GetMapping("getSelects")
    public ResponseEntity<List<List>> getSelects() {
        try {
            List<List> selects = service.getSelects();
            if (selects == null || selects.isEmpty()) {
                // 데이터가 없을 경우, 404 Not Found 상태 코드 반환
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            // 데이터가 정상적으로 존재할 경우, 200 OK 상태 코드와 함께 데이터 반환
            return ResponseEntity.ok(selects);
        } catch (Exception e) {
            // 예외 발생 시, 500 Internal Server Error 상태 코드와 함께 에러 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @PostMapping("employeeRegistration")
    public ResponseEntity<Integer> employeeRegistration(@ModelAttribute UserVo newEmployeeVo, HttpServletRequest req) {
        try {
            MultipartFile file = newEmployeeVo.getImage();
            if (file != null && !file.isEmpty()) {
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

                try (FileOutputStream fos = new FileOutputStream(new File(uploadDir, changeName))) {
                    byte[] buf = new byte[1024];
                    int size;
                    while ((size = is.read(buf)) != -1) {
                        fos.write(buf, 0, size);
                    }
                }
                newEmployeeVo.setImgOriginName(originFileName);
                newEmployeeVo.setImgChangeName(changeName);
            }
            int result = service.employeeRegistration(newEmployeeVo);
            if (result > 0) {
                // 성공적인 등록
                return ResponseEntity.ok(result);
            } else {
                // 등록 실패
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
        } catch (IOException e) {
            // 파일 처리 중 오류 발생
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1);
        } catch (Exception e) {
            // 기타 오류 발생
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1);
        }
    }


    @GetMapping("listEmployeePage")
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
    public List<UserVo> listEmployeeData(@RequestParam("startNum") int startNum,
                                         @RequestParam("endNum") int endNum) {
        List<UserVo> employeeVoList = service.listEmployeeData(startNum, endNum);
        return employeeVoList;
    }


    @GetMapping("getEmployeeDetails")
    public UserVo getEmployeeDetails(@RequestParam("empNo") int empNo) {
        UserVo employeeInfo = service.getEmployeeDetails(empNo);
        System.out.println(employeeInfo);
        return employeeInfo;
    }

    @PostMapping("updateEmployee")
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
        int result = service.updateEmployee(employeeVo);
        return result;
    }

    @PostMapping("deleteEmployee")
    public int deleteEmployee(@RequestParam("empNo") int empNo) {
        int result = service.deleteEmployee(empNo);
        return result;
    }


    @GetMapping("searchListEmployeePage")
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
