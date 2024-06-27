package com.groupware.orca.vacation.service;

import com.groupware.orca.vacation.dao.VacationDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacationService {

    private final VacationDao dao;

}
