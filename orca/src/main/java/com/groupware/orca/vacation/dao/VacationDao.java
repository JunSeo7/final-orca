package com.groupware.orca.vacation.dao;

import com.groupware.orca.vacation.mapper.VacationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VacationDao {

    private final VacationMapper mapper;

}
