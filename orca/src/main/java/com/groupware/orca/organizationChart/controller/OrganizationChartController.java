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

    @GetMapping("getDepartment")
    @ResponseBody
    public List<DepartmentVo> getDepartment() {
        List<DepartmentVo> departmentVoList = service.getDepartment();
        return departmentVoList;
    }

    @GetMapping("getTeam")
    @ResponseBody
    public List<TeamVo> getTeam(@RequestParam("deptCode") int deptCode) {
        List<TeamVo> teamVoList = service.getTeam(deptCode);
        return teamVoList;
    }

    @GetMapping("getOrganizationChartList")
    @ResponseBody
    public List<UserVo> getOrganizationChartList(@RequestParam("deptCode") int deptCode, @RequestParam("teamCode")int teamCode) {
        List<UserVo> userVoList = service.getOrganizationChartList(deptCode, teamCode);
        return userVoList;
    }


}
