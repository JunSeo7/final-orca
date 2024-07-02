package com.groupware.orca.vacation.mapper;

import com.groupware.orca.vacation.vo.VacationVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VacationMapper {

    @Insert("")
    int enrollVacation(VacationVo vo);
}
