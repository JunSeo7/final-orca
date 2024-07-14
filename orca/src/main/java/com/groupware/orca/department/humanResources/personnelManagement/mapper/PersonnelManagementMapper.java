package com.groupware.orca.department.humanResources.personnelManagement.mapper;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.common.vo.Position;
import com.groupware.orca.common.vo.TeamVo;
import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonnelManagementMapper {

    @Select("SELECT DEPT_CODE, PARTNAME FROM DEPARTMENT ORDER BY DEPT_CODE")
    List<DepartmentVo> getDepartment();

    @Select("SELECT TEAM_CODE, TEAM_NAME ,DEPT_CODE FROM DEPARTMENT_TEAM WHERE DEL_YN = 'N' ORDER BY TEAM_CODE")
    List<TeamVo> getTeam();

    @Select("SELECT POSITION_CODE, NAME_OF_POSITION FROM POSITION ORDER BY POSITION_CODE")
    List<Position> getPosition();

    @Insert("INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, RELIGION ,SALARY, BANK_NUMBER)\n" +
            "VALUES (TO_CHAR(SYSDATE, 'YYYYMMDD') || LPAD(SEQ_EMP_NO.NEXTVAL, 4, '0'), #{name} ,#{positionCode}, #{deptCode}, #{teamCode}, #{gender}, #{socialSecurityNo}, #{password}, #{phone}, #{extensionCall}, #{email}, #{address}, #{height}, #{weight}, #{bloodType}, #{religion}, #{salary}, #{bankNumber})")
    int employeeRegistration(UserVo newEmployeeVo);
}
