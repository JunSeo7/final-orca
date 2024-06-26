package com.groupware.orca.document.mapper;

import com.groupware.orca.document.vo.DocumentVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DocumentMapper {

    @Select("")
    List<DocumentVo> getDocumentList();
}
