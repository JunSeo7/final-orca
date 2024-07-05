package com.groupware.orca.sick.mapper;

import com.groupware.orca.sick.vo.SickVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SickMapper {

    @Insert("")
    void enrollSick(SickVo vo);
}
