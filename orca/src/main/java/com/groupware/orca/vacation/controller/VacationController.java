package com.groupware.orca.vacation.controller;

import com.groupware.orca.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/vacation")
public class VacationController {

    private final VacationService service;
}