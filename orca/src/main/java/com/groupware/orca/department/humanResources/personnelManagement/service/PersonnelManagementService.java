package com.groupware.orca.department.humanResources.personnelManagement.service;

import com.groupware.orca.department.humanResources.personnelManagement.dao.PersonnelManagementDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonnelManagementService {
    private final PersonnelManagementDao dao;
}
