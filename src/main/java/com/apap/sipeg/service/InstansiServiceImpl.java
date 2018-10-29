package com.apap.sipeg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.sipeg.repository.InstansiDb;

/*
    InstansiServiceImpl
*/

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
    @Autowired
    private InstansiDb instansiDb;

    @Override
    public InstansiDb getInstansiDb() {
        return instansiDb;
    }
}
