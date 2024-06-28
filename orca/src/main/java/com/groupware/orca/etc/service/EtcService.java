package com.groupware.orca.etc.service;

import com.groupware.orca.etc.dao.EtcDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtcService {

    private final EtcDao dao;
}
