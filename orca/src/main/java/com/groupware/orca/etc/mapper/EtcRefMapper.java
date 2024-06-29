package com.groupware.orca.etc.mapper;

import com.groupware.orca.etc.vo.EtcRefVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EtcRefMapper {

    @Insert("INSERT INTO ETC_REFERENCE (ETC_CODE, ETC_NAME) " +
            "VALUES (#{etcCode}, #{etcName})")
    int registrationEtcCode(EtcRefVo vo);

    @Update("UPDATE ETC_REFERENCE SET ETC_NAME = #{etcName} " +
            "WHERE ETC_CODE = #{etcCode}")
    void editEtcCode(EtcRefVo vo);

    @Delete("DELETE ETC_REFERENCE WHERE ETC_CODE = #{etcCode}")
    int deleteECode(String etcCode);
}
