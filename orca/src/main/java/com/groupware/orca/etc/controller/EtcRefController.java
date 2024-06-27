package com.groupware.orca.etc.controller;

import com.groupware.orca.etc.service.EtcRefService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("orca/etcRef")
public class EtcRefController {

    private final EtcRefService service;

}
