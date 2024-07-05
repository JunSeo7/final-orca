package com.groupware.orca.etc.mapper;

import com.groupware.orca.etc.vo.EtcRefVo;
import com.groupware.orca.etc.vo.EtcVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EtcMapper {

    @Select("SELECT ETC_CODE , ETC_NAME FROM ETC_REFERENCE")
    List<EtcRefVo> loadEtcCode();

    @Insert("INSERT INTO ETC_MANAGEMENT ( ETC_NO , ETC_CODE , EMP_NO , DOC_NO , REG_DATE , START_DATE , EXPIRY_DATE ) " +
            "VALUES ( SEQ_ETC_MANAGEMENT.NEXTVAL , #{etcCode} , #{empNo} , #{docNo} , SYSDATE , #{startDate} , #{expiryDate} )")
    void enrollEtc(EtcVo vo);
}
