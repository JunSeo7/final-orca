package com.groupware.orca.department.humanResources.personnelManagement.mapper;

import com.groupware.orca.common.vo.DepartmentVo;
import com.groupware.orca.common.vo.Position;
import com.groupware.orca.common.vo.TeamVo;
import com.groupware.orca.user.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Insert({"<script>" +
            "INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, RELIGION ,SALARY, BANK_NUMBER" +
            "<if test='imgOriginName != null'>, IMG_ORIGIN_NAME</if>" +
            "<if test='imgChangeName != null'>, IMG_CHANGE_NAME</if>" +
            ")" +
            "VALUES (TO_CHAR(SYSDATE, 'YYYYMM') || LPAD(SEQ_EMP_NO.NEXTVAL, 4, '0'), #{name} ,#{positionCode}, #{deptCode}, #{teamCode}, #{gender}, #{socialSecurityNo}, #{password}, #{phone}, #{extensionCall}, #{email}, #{address}, #{height}, #{weight}, #{bloodType}, #{religion}, #{salary}, #{bankNumber}" +
            "<if test='imgOriginName != null'>, #{imgOriginName}</if>" +
            "<if test='imgChangeName != null'>, #{imgChangeName}</if>" +
            ")" +
            "</script>"
    })
    int employeeRegistration(UserVo newEmployeeVo);

    @Select("SELECT COUNT(EMP_NO) FROM PERSONNEL_INFORMATION \n" +
            "WHERE LEAVING_DATE IS NULL AND POSITION_CODE > 5")
    int getEmployeeCnt();

    @Select("SELECT\n" +
            "*\n" +
            "FROM\n" +
            "(\n" +
            "    SELECT\n" +
            "        A.*\n" +
            "        , ROWNUM AS RNUM\n" +
            "    FROM\n" +
            "    (\n" +
            "        SELECT \n" +
            "            E.EMP_NO\n" +
            "            , E.NAME\n" +
            "            , D.PARTNAME\n" +
            "            , P.NAME_OF_POSITION\n" +
            "            , E.IMG_CHANGE_NAME" +
            "        FROM PERSONNEL_INFORMATION E\n" +
            "        JOIN DEPARTMENT D ON D.DEPT_CODE = E.DEPT_CODE\n" +
            "        JOIN POSITION P ON P.POSITION_CODE = E.POSITION_CODE\n" +
            "        WHERE E.LEAVING_DATE IS NULL AND E.POSITION_CODE > 5\n" +
            "        ORDER BY E.POSITION_CODE ASC\n" +
            "    ) A\n" +
            ")\n" +
            "WHERE RNUM BETWEEN #{startNum} AND #{endNum}")
    List<UserVo> listEmployeeData(@Param("startNum") int startNum, @Param("endNum") int endNum);

    @Select("SELECT \n" +
            "    E.EMP_NO \n" +
            "    , E.NAME\n" +
            "    , E.GENDER\n" +
            "    , E.SOCIAL_SECURITY_NO\n" +
            "    , E.PHONE\n" +
            "    , E.EXTENSION_CALL\n" +
            "    , E.EMAIL\n" +
            "    , E.ADDRESS\n" +
            "    , E.DATE_OF_EMPLOYMENT\n" +
            "    , E.IMG_CHANGE_NAME\n" +
            "    , E.HEIGHT\n" +
            "    , E.WEIGHT\n" +
            "    , E.BLOOD_TYPE\n" +
            "    , E.RELIGION\n" +
            "    , E.BANK_NUMBER\n" +
            "    , D.PARTNAME\n" +
            "    , T.TEAM_NAME\n" +
            "    , P.NAME_OF_POSITION\n" +
            "FROM PERSONNEL_INFORMATION E\n" +
            "JOIN DEPARTMENT D ON D.DEPT_CODE = E.DEPT_CODE\n" +
            "JOIN DEPARTMENT_TEAM T ON T.TEAM_CODE = E.TEAM_CODE\n" +
            "JOIN POSITION P ON P.POSITION_CODE = E.POSITION_CODE\n" +
            "WHERE EMP_NO = #{empNo}")
    UserVo getEmployeeDetails(@Param("empNo") int empNo);
}
