package com.apap.sipeg.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.PegawaiModel;

/*
    PegawaiDb
*/

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long> {
    PegawaiModel findByNip(String nip);

    List<PegawaiModel> findByInstansiAndTahunMasukAndTanggalLahir(InstansiModel instansi, String tahunMasuk, Date tanggalLahir);

    @Query(value = "SELECT p.* FROM pegawai p, provinsi prov, instansi i WHERE p.id_instansi = i.id  AND i.id_provinsi = prov.id AND prov.id = :idProvinsi", nativeQuery = true)
    List<PegawaiModel> findByProvinsi(@Param("idProvinsi") Long idProvinsi);

    List<PegawaiModel> findByInstansi(InstansiModel instansi);

    @Query(value = "SELECT p.* FROM pegawai p, jabatan j, jabatan_pegawai jp WHERE p.id = jp.id_pegawai AND j.id = jp.id_jabatan AND j.id = :idJabatan", nativeQuery = true)
    List<PegawaiModel> findByJabatan(@Param("idJabatan") Long idJabatan);

    @Query(value = "SELECT p.* FROM pegawai p, provinsi prov, instansi i WHERE p.id_instansi = i.id AND i.id_provinsi = prov.id AND prov.id = :idProvinsi AND i.id = :idInstansi", nativeQuery = true)
    List<PegawaiModel> findByProvinsiAndInstansi(@Param("idProvinsi") Long idProvinsi, @Param("idInstansi") Long idInstansi);

    @Query(value = "SELECT p.* FROM pegawai p, provinsi prov, instansi i, jabatan j, jabatan_pegawai jp WHERE p.id_instansi = i.id AND i.id_provinsi = prov.id AND j.id = jp.id_jabatan AND p.id = jp.id_pegawai AND prov.id = :idProvinsi AND j.id = :idJabatan", nativeQuery = true)
    List<PegawaiModel> findByProvinsiAndJabatan(@Param("idProvinsi") Long idProvinsi, @Param("idJabatan") Long idJabatan);

    @Query(value = "SELECT p.* FROM pegawai p, provinsi prov, instansi i, jabatan j, jabatan_pegawai jp WHERE p.id_instansi = i.id AND j.id = jp.id_jabatan AND p.id = jp.id_pegawai AND i.id = :idInstansi AND j.id = :idJabatan", nativeQuery = true)
    List<PegawaiModel> findByInstansiAndJabatan(@Param("idInstansi") Long idInstansi, @Param("idJabatan") Long idJabatan);

    @Query(value = "SELECT p.* FROM pegawai p, provinsi prov, instansi i, jabatan j, jabatan_pegawai WHERE p.id_instansi = i.id AND i.id_provinsi = prov.id AND j.id = j.id_jabatan AND p.id = jp.id_pegawai AND prov.id = :idProvinsi AND i.id = :idInstansi, j.id = :idJabatan", nativeQuery = true)
    List<PegawaiModel> findByProvinsiAndInstansiAndJabatan(@Param("idProvinsi") Long idPegawai, @Param("idInstansi") Long idInstansi, @Param("idJabatan") Long idJabatan);
}
