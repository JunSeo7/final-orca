package com.groupware.orca.organizationChart.controller;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.common.vo.TeamVo;
import com.groupware.orca.organizationChart.service.OrganizationChartService;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orca/organizationChart")
@RequiredArgsConstructor
public class OrganizationChartRestController {

    private final OrganizationChartService service;

    @GetMapping("getDepartment")
    public ResponseEntity<List<DepartmentVo>> getDepartment() {
        List<DepartmentVo> departmentVoList = service.getDepartment();
        if (departmentVoList == null || departmentVoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departmentVoList);
    }


    @GetMapping("getTeam")
    public ResponseEntity<List<TeamVo>> getTeam(@RequestParam("deptCode") int deptCode) {
        List<TeamVo> teamVoList = service.getTeam(deptCode);
        if (teamVoList == null || teamVoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(teamVoList);
    }


    @GetMapping("getOrganizationChartList")
    public ResponseEntity<List<UserVo>> getOrganizationChartList(@RequestParam("deptCode") int deptCode, @RequestParam("teamCode") int teamCode) {
        List<UserVo> userVoList;
        userVoList = service.getOrganizationChartList(deptCode, teamCode);
        if (userVoList == null || userVoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userVoList);
    }

}
