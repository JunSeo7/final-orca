package com.groupware.orca.department.humanResources.personnelManagement.dao;

import com.groupware.orca.department.humanResources.personnelManagement.mapper.PersonnelManagementMapper;
import com.groupware.orca.department.humanResources.personnelManagement.service.PersonnelManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonnelManagementDao {
    private final PersonnelManagementMapper mapper;
}
