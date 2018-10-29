package com.apap.sipeg.service;

import com.apap.sipeg.repository.JabatanDb;
import com.apap.sipeg.model.JabatanModel;

/*
    JabatanService
*/

public interface JabatanService {
    JabatanDb getJabatanDb();
    void addJabatan(JabatanModel jabatan);
    void updateJabatan(JabatanModel jabatan);
    void deleteJabatanById(Long id);
    JabatanModel getDataJabatanById(Long id);
}
