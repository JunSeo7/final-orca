package com.groupware.orca.vacation.service;

import com.groupware.orca.vacation.dao.VacationRefDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacationRefService {

    private final VacationRefDao dao;

}
