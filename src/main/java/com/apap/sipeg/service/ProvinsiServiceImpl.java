package com.apap.sipeg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.sipeg.repository.ProvinsiDb;

import com.apap.sipeg.model.ProvinsiModel;

/*
    ProvinsiServiceImpl
*/

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
    @Autowired
    private ProvinsiDb provinsiDb;

    @Override
    public ProvinsiDb getProvinsiDb() {
        return provinsiDb;
    }

    @Override
    public ProvinsiModel findProvinsiById(long id) {
        return provinsiDb.findProvinsiById(id);
    }

}
