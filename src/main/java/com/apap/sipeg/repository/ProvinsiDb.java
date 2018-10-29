package com.apap.sipeg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.sipeg.model.ProvinsiModel;

/*
    ProvinsiDb
*/

@Repository
public interface ProvinsiDb extends JpaRepository<ProvinsiModel, Long> {
    ProvinsiModel findProvinsiById (long id);
}
