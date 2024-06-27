package com.groupware.orca.etc.dao;

import com.groupware.orca.etc.mapper.EtcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EtcDao {

    private final EtcMapper mapper;
}
