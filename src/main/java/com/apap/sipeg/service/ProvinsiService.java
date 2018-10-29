package com.apap.sipeg.service;

import com.apap.sipeg.repository.ProvinsiDb;

import com.apap.sipeg.model.ProvinsiModel;

/*
    ProvinsiService
*/

public interface ProvinsiService {
    ProvinsiDb getProvinsiDb();
    ProvinsiModel findProvinsiById(long id);
}
