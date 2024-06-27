package com.groupware.orca.etc.service;

import com.groupware.orca.etc.dao.EtcRefDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EtcRefService {

    private final EtcRefDao dao;
}
