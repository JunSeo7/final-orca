package com.groupware.orca.organizationChart.dao;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.common.vo.TeamVo;
import com.groupware.orca.organizationChart.mapper.OrganizationChartMapper;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizationChartDao {
    private final OrganizationChartMapper mapper;

    public List<DepartmentVo> getDepartment() {
       return mapper.getDepartment();
    }

    public List<TeamVo> getTeam(int deptCode) {
        return mapper.getTeam(deptCode);
    }

    public List<UserVo> getOrganizationChartList(int deptCode, int teamCode) {
        return mapper.getOrganizationChartList(deptCode, teamCode);
    }
}
