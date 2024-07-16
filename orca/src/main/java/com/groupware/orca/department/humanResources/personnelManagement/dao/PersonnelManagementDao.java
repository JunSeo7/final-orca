package com.groupware.orca.department.humanResources.personnelManagement.dao;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.common.vo.Position;
import com.groupware.orca.common.vo.TeamVo;
import com.groupware.orca.department.humanResources.personnelManagement.mapper.PersonnelManagementMapper;
import com.groupware.orca.department.humanResources.personnelManagement.service.PersonnelManagementService;
import com.groupware.orca.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonnelManagementDao {
    private final PersonnelManagementMapper mapper;

    public List<List> getSelects() {

        List<List> selects = new ArrayList<>();
        List<DepartmentVo> departmentVoList = mapper.getDepartment();
        List<TeamVo> teamVoList = mapper.getTeam();
        List<Position> positionVoList = mapper.getPosition();

        selects.add(departmentVoList);
        selects.add(teamVoList);
        selects.add(positionVoList);
        return selects;

    }

    public int employeeRegistration(UserVo newEmployeeVo) {
        return mapper.employeeRegistration(newEmployeeVo);
    }

    public int getEmployeeCnt() {
        return mapper.getEmployeeCnt();
    }

    public List<UserVo> listEmployeeData(int startNum, int endNum) {
        return mapper.listEmployeeData(startNum, endNum);
    }

    public UserVo getEmployeeDetails(int empNo) {
        return mapper.getEmployeeDetails(empNo);
    }
}
