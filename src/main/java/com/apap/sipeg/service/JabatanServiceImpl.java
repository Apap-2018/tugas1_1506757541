package com.apap.sipeg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.sipeg.repository.JabatanDb;
import com.apap.sipeg.model.JabatanModel;

/*
    JabatanServiceImpl
*/

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
    @Autowired
    private JabatanDb jabatanDb;

    @Override
    public JabatanDb getJabatanDb() {
        return jabatanDb;
    }

    @Override
    public void addJabatan (JabatanModel jabatan) {
        jabatanDb.save(jabatan);
    }

    @Override
    public void updateJabatan(JabatanModel jabatan) {
        JabatanModel jabatanUpdate = jabatanDb.getOne(jabatan.getId());
        jabatanUpdate.setNama(jabatan.getNama());
        jabatanUpdate.setDeskripsi(jabatan.getDeskripsi());
        jabatanUpdate.setGajiPokok(jabatan.getGajiPokok());

        jabatanDb.save(jabatan);
    }

    @Override
    public void deleteJabatanById(Long id) {
        jabatanDb.deleteById(id);
    }

    @Override
    public JabatanModel getDataJabatanById(Long id) {
        return jabatanDb.getOne(id);
    }
}