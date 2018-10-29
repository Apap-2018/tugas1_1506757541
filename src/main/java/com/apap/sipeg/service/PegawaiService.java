package com.apap.sipeg.service;

import com.apap.sipeg.model.PegawaiModel;
import com.apap.sipeg.repository.PegawaiDb;

public interface PegawaiService {
    PegawaiDb getPegawaiDb();
    PegawaiModel getDataPegawaiByNip(String nip);
    void addPegawai(PegawaiModel pegawai);
    void updatePegawai(PegawaiModel pegawai, Long id);
    PegawaiModel getPegawaiTertuaInstansi(Long idInstansi);
    PegawaiModel getPegawaiTermudaInstansi(Long idInstansi);
    double hitungGajiPegawai(PegawaiModel pegawai);
}
