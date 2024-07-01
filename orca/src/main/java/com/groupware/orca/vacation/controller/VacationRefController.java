package com.groupware.orca.vacation.controller;

import com.groupware.orca.vacation.service.VacationRefService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/vacationRef")
public class VacationRefController {

    private final VacationRefService service;


}
