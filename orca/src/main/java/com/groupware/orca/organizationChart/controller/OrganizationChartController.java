package com.groupware.orca.organizationChart.controller;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.common.vo.TeamVo;
import com.groupware.orca.organizationChart.service.OrganizationChartService;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("orca/organizationChart")
@RequiredArgsConstructor
public class OrganizationChartController {

    private final OrganizationChartService service;

    @GetMapping("showOrganizationChart")
    public String showOrganizationChart() {
        return "organizationChart/showOrganizationChart";
    }

}
