package com.apap.sipeg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.sipeg.repository.InstansiDb;
import com.apap.sipeg.repository.ProvinsiDb;
import com.apap.sipeg.repository.PegawaiDb;
import com.apap.sipeg.repository.JabatanDb;

import com.apap.sipeg.model.PegawaiModel;

/*
    PegawaiServiceImpl
*/

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
    @Autowired
    private InstansiDb instansiDb;

    @Autowired
    private ProvinsiDb provinsiDb;

    @Autowired
    private PegawaiDb pegawaiDb;

    @Autowired
    private JabatanDb jabatanDb;

    @Override
    public PegawaiModel getDataPegawaiByNip(String nip) {
        return pegawaiDb.findByNip(nip);
    }

    @Override
    public PegawaiDb getPegawaiDb() {
        return pegawaiDb;
    }

    @Override
    public void addPegawai(PegawaiModel pegawai) {
        String result = "";

        String kodeInstansi = Long.toString(pegawai.getInstansi().getId());

        String kodeTanggalLahir = pegawai.getTanggalLahir().toString();
        String tanggal = kodeTanggalLahir.substring(8);
        String bulan = kodeTanggalLahir.substring(5,7);
        String tahun = kodeTanggalLahir.substring(2,4);

        kodeTanggalLahir = tanggal + bulan + tahun;

        String kodeTahunMasuk = pegawai.getTahunMasuk();

        String kodeUrutanMasuk = "";
        List<PegawaiModel> listPegawai = pegawaiDb.findByInstansiAndTahunMasukAndTanggalLahir(pegawai.getInstansi(), pegawai.getTahunMasuk(), pegawai.getTanggalLahir());
        listPegawai.add(pegawai);
        kodeUrutanMasuk = Integer.toString(listPegawai.size());
        if (Integer.parseInt(kodeUrutanMasuk) < 10) {
            kodeUrutanMasuk = "0" + kodeUrutanMasuk;
        }

        result = kodeInstansi + kodeTanggalLahir + kodeTahunMasuk + kodeUrutanMasuk;

        pegawai.setNip(result);

        pegawaiDb.save(pegawai);
    }

    @Override
    public void updatePegawai(PegawaiModel pegawai, Long id) {
        String result = "";

        String kodeInstansi = Long.toString(pegawai.getInstansi().getId());

        String kodeTanggalLahir = pegawai.getTanggalLahir().toString();
        String tanggal = kodeTanggalLahir.substring(8);
        String bulan = kodeTanggalLahir.substring(5,7);
        String tahun = kodeTanggalLahir.substring(2,4);
        kodeTanggalLahir = tanggal + bulan + tahun;

        String kodeTahunMasuk = pegawai.getTahunMasuk();

        String kodeUrutanMasuk = "";
        List<PegawaiModel> listPegawai = pegawaiDb.findByInstansiAndTahunMasukAndTanggalLahir(pegawai.getInstansi(), pegawai.getTahunMasuk(), pegawai.getTanggalLahir());
        for(int i = 0; i < listPegawai.size(); i++) {
            if (listPegawai.get(i).getId() == pegawai.getId()) {
                listPegawai.remove(i);
            }
        }
            listPegawai.add(pegawai);

        kodeUrutanMasuk = Integer.toString(listPegawai.size());
        if(Integer.parseInt(kodeUrutanMasuk) < 10) {
            kodeUrutanMasuk = "0" + kodeUrutanMasuk;
        }

        result = kodeInstansi + kodeTahunMasuk + kodeTanggalLahir + kodeUrutanMasuk;

        PegawaiModel pegawaiUpdate = pegawaiDb.getOne(pegawai.getId());
        pegawaiUpdate.setNama(pegawai.getNama());
        pegawaiUpdate.setTempatLahir(pegawai.getTempatLahir());
        pegawaiUpdate.setTanggalLahir(pegawai.getTanggalLahir());
        pegawaiUpdate.setTahunMasuk(pegawai.getTahunMasuk());
        pegawaiUpdate.setInstansi(pegawai.getInstansi());
        pegawaiUpdate.setListJabatan(pegawai.getListJabatan());

        pegawaiUpdate.setNip(result);

        pegawaiDb.save(pegawaiUpdate);
    }

    @Override
    public PegawaiModel getPegawaiTertuaInstansi(Long idInstansi) {
        List<PegawaiModel> listPegawai = instansiDb.getOne(idInstansi).getListPegawai();
        PegawaiModel pegawaiTua = listPegawai.get(0);

        for(int i = 1; i < listPegawai.size(); i++) {
            if(listPegawai.get(i).getTanggalLahir().before(pegawaiTua.getTanggalLahir())) {
                pegawaiTua = listPegawai.get(i);
            }
        }
        return pegawaiTua;
    }

    @Override
    public PegawaiModel getPegawaiTermudaInstansi(Long idInstansi) {
        List<PegawaiModel> listPegawai = instansiDb.getOne(idInstansi).getListPegawai();
        PegawaiModel pegawaiMuda = listPegawai.get(0);

        for(int i = 1; i < listPegawai.size(); i++) {
            if(listPegawai.get(i).getTanggalLahir().after(pegawaiMuda.getTanggalLahir())) {
                pegawaiMuda = listPegawai.get(i);
            }
        }
        return pegawaiMuda;
    }

    @Override
    public double hitungGajiPegawai(PegawaiModel pegawai) {
        double gajiPegawai = pegawai.getListJabatan().get(0).getGajiPokok();
        if (pegawai.getListJabatan().size() > 1) {
            for (int i = 1; i < pegawai.getListJabatan().size(); i++) {
                if (pegawai.getListJabatan().get(i).getGajiPokok() > gajiPegawai) {
                    gajiPegawai = pegawai.getListJabatan().get(i).getGajiPokok();
                }
            }
        }

        double tunjangan = pegawai.getInstansi().getProvinsi().getPresentaseTunjangan() / 100;
        gajiPegawai = gajiPegawai + (tunjangan * gajiPegawai);

        return gajiPegawai;
    }
}