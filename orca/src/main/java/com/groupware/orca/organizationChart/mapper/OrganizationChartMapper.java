package com.groupware.orca.organizationChart.mapper;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.common.vo.TeamVo;
import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrganizationChartMapper {

    @Select("SELECT DEPT_CODE, PARTNAME FROM DEPARTMENT ORDER BY DEPT_CODE ASC")
    List<DepartmentVo> getDepartment();

    @Select("SELECT TEAM_CODE, TEAM_NAME FROM DEPARTMENT D JOIN DEPARTMENT_TEAM T ON D.DEPT_CODE = T.DEPT_CODE WHERE D.DEPT_CODE = #{deptCode} AND T.DEL_YN = 'N' ORDER BY TEAM_CODE ASC ")
    List<TeamVo> getTeam(@Param("deptCode") int deptCode);

    @Select("SELECT E.NAME, P.NAME_OF_POSITION FROM PERSONNEL_INFORMATION E JOIN POSITION P ON P.POSITION_CODE = E.POSITION_CODE WHERE E.DEPT_CODE = #{deptCode} AND E.TEAM_CODE = #{teamCode} AND E.LEAVING_DATE IS NULL AND E.POSITION_CODE > 5 ORDER BY E.POSITION_CODE ASC")
    List<UserVo> getOrganizationChartList(@Param("deptCode")int deptCode,@Param("teamCode") int teamCode);
}
