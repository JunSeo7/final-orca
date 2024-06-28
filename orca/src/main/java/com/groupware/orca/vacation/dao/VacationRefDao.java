package com.groupware.orca.vacation.dao;

import com.groupware.orca.vacation.mapper.VacationRefMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VacationRefDao {

    private final VacationRefMapper mapper;

}
