package com.groupware.orca.vacation.controller;

import com.groupware.orca.vacation.service.VacationRefService;
import com.groupware.orca.vacation.vo.VacationRefVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/re/vacationRef")
public class VacationRefRestController {

    private final VacationRefService service;

    // 휴가 코드 등록
    @PostMapping("registrationVCode")
    public void vCode(VacationRefVo vo){
        service.registrationVCode(vo);
    }

    // 휴가 코드 수정
    @PostMapping("editVCode")
    public void editVCode(VacationRefVo vo){
        service.editVCode(vo);
    }

    // 휴가 코드 삭제
    @DeleteMapping
    public ResponseEntity<VacationRefVo> deleteVCode(@RequestBody List<String> vacationCode) {
            service.deleteVCode(vacationCode);
            return ResponseEntity.ok().build();
    }

}
