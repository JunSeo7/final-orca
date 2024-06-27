package com.groupware.orca.etc.dao;

import com.groupware.orca.etc.mapper.EtcRefMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EtcRefDao {

    private final EtcRefMapper mapper;
}
