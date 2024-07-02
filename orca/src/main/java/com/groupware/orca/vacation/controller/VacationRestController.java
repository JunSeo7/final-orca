package com.groupware.orca.vacation.controller;

import com.groupware.orca.vacation.service.VacationService;
import com.groupware.orca.vacation.vo.VacationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("orca/re/vacation")
public class VacationRestController {

    private final VacationService service;

    @PostMapping
    public int enrollVacation(VacationVo vo){
        int result = service.enrollVacation(vo);
        return result;
    }

}
