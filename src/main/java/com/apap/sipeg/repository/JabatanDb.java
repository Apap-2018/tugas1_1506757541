package com.apap.sipeg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.sipeg.model.JabatanModel;

/*
    JabatanDb
*/

public interface JabatanDb extends JpaRepository<JabatanModel, Long> {
}
