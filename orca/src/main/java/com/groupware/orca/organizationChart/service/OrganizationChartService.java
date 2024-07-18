package com.groupware.orca.organizationChart.service;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.common.vo.TeamVo;
import com.groupware.orca.organizationChart.dao.OrganizationChartDao;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationChartService {

    private final OrganizationChartDao dao;

    public List<DepartmentVo> getDepartment() {
        return dao.getDepartment();
    }

    public List<TeamVo> getTeam(int deptCode) {
        return dao.getTeam(deptCode);
    }

    public List<UserVo> getOrganizationChartList(int deptCode, int teamCode) {
        return dao.getOrganizationChartList(deptCode, teamCode);
    }
}
