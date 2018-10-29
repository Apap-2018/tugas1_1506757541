package com.apap.sipeg.controller;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.ProvinsiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.sipeg.model.JabatanModel;
import com.apap.sipeg.model.PegawaiModel;
import com.apap.sipeg.service.InstansiService;
import com.apap.sipeg.service.JabatanService;
import com.apap.sipeg.service.PegawaiService;
import com.apap.sipeg.service.ProvinsiService;

/*
    PegawaiController
*/

@Controller
public class PegawaiController {
    @Autowired
    private ProvinsiService provinsiService;

    @Autowired
    private InstansiService instansiService;

    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private JabatanService jabatanService;

    private PegawaiModel archivePegawai;
    private Long id_pegawai;

    @RequestMapping(value = "/pegawai")
    private String viewPegawai(@RequestParam("nip") String nip, Model model) {
        PegawaiModel pegawai = pegawaiService.getDataPegawaiByNip(nip);

        double gajiPegawai = pegawaiService.hitungGajiPegawai(pegawai);
        String gaji = String.format("%.0f", gajiPegawai);

        model.addAttribute("gaji", gaji);
        model.addAttribute("pegawai", pegawai);

        return "viewPegawai";
    }

    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
    private String addPegawai(@ModelAttribute PegawaiModel pegawai, Model model) {
        archivePegawai = new PegawaiModel();
        archivePegawai.setListJabatan(new ArrayList<JabatanModel>());
        archivePegawai.getListJabatan().add(new JabatanModel());

        List<ProvinsiModel> listProvinsi = provinsiService.getProvinsiDb().findAll();
        List<InstansiModel> listInstansi = instansiService.getInstansiDb().findAll();
        List<JabatanModel> listJabatan = jabatanService.getJabatanDb().findAll();

        model.addAttribute("pegawai", archivePegawai);
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);

        return "addPegawai";
    }

    @RequestMapping(value = "/pegawai/tambah", params = {"addJabatan"})
    private String addJabatan(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
        pegawai.setListJabatan(archivePegawai.getListJabatan());
        pegawai.getListJabatan().add(new JabatanModel());

        List<ProvinsiModel> listProvinsi = provinsiService.getProvinsiDb().findAll();
        List<InstansiModel> listInstansi = instansiService.getInstansiDb().findAll();
        List<JabatanModel> listJabatan = jabatanService.getJabatanDb().findAll();

        model.addAttribute("pegawai", archivePegawai);
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);

        return "addPegawai";
    }

    @RequestMapping(value = "/pegawai/tambah", params = {"submit"}, method = RequestMethod.POST)
    private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
        pegawaiService.addPegawai(pegawai);

        model.addAttribute("pegawai", pegawai);
        return "successAddPegawai";
    }

    @RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
    private String updatePegawai(@RequestParam("nip") String nip, Model model) {
        PegawaiModel pegawai = pegawaiService.getDataPegawaiByNip(nip);
        id_pegawai = pegawai.getId();

        List<ProvinsiModel> listProvinsi = provinsiService.getProvinsiDb().findAll();
        List<InstansiModel> listInstansi = instansiService.getInstansiDb().findAll();
        List<JabatanModel> listJabatan = jabatanService.getJabatanDb().findAll();

        model.addAttribute("pegawai", pegawai);
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);
        return "updatePegawai";
    }

    @RequestMapping(value = "/pegawai/ubah", params = {"addJabatanUpdate"})
    private String addJabatanUpdate(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
        pegawai.getListJabatan().add(new JabatanModel());

        List<ProvinsiModel> listProvinsi = provinsiService.getProvinsiDb().findAll();
        List<InstansiModel> listInstansi = instansiService.getInstansiDb().findAll();
        List<JabatanModel> listJabatan = jabatanService.getJabatanDb().findAll();

        model.addAttribute("pegawai", pegawai);
        model.addAttribute("ListProvinsi", listProvinsi);
        model.addAttribute("ListInstansi", listInstansi);
        model.addAttribute("listJabatan",  listJabatan);
        return "updatePegawai";
    }

    @RequestMapping(value = "/pegawai/ubah", params = {"submit"}, method = RequestMethod.POST)
    private String updatePegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
        pegawai.setId(id_pegawai);
        pegawaiService.updatePegawai(pegawai, pegawai.getId());

        pegawai.setNip(pegawaiService.getPegawaiDb().getOne(id_pegawai).getNip());

        model.addAttribute("pegawai", pegawai);
        return "successUpdatePegawai";
    }

    @RequestMapping(value = "/pegawai/cari")
    private String cariPegawai(Model model) {
        List<PegawaiModel> listPegawai = pegawaiService.getPegawaiDb().findAll();
        List<ProvinsiModel> listProvinsi = provinsiService.getProvinsiDb().findAll();
        List<InstansiModel> listInstansi = instansiService.getInstansiDb().findAll();
        List<JabatanModel> listJabatan = jabatanService.getJabatanDb().findAll();

        model.addAttribute("text", null);
        model.addAttribute("listPegawai", listPegawai);
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);

        return "cariPegawai";
    }

    @RequestMapping(value = "/pegawai/cari", params = {"cari"})
    private String filterCariPegawai(@RequestParam(value = "idProvinsi", required = false) Long idProvinsi,
                                     @RequestParam(value = "idInstansi", required = false) Long idInstansi,
                                     @RequestParam(value = "idJabatan", required = false) Long idJabatan,
                                    Model model)
    {
        List<PegawaiModel> listPegawai = new ArrayList<PegawaiModel>();

        if(idProvinsi != null && idInstansi == null && idJabatan == null) {
            listPegawai = pegawaiService.getPegawaiDb().findByProvinsi(idProvinsi);
            model.addAttribute("text", "Hasil pencarian pegawai berdasarkan provinsi");
        }
        else if(idProvinsi == null && idInstansi != null && idJabatan == null) {
            listPegawai = pegawaiService.getPegawaiDb().findByInstansi(instansiService.getInstansiDb().getOne(idInstansi));
            model.addAttribute("text", "Hasil pencarian pegawai berdasarkan instansi");
        }
        else if(idProvinsi == null && idInstansi == null && idJabatan != null) {
            listPegawai = pegawaiService.getPegawaiDb().findByJabatan(idJabatan);
            model.addAttribute("text", "Hasil pencarian pegawai berdasarkan jabatan");
        }
        else if(idProvinsi != null && idInstansi != null && idJabatan == null) {
            listPegawai = pegawaiService.getPegawaiDb().findByProvinsiAndInstansi(idProvinsi, idInstansi);
            model.addAttribute("text", "Hasil pencarian pegawai berdasarkan provinsi dan instansi");
        }
        else if(idProvinsi != null && idInstansi == null && idJabatan != null) {
            listPegawai = pegawaiService.getPegawaiDb().findByProvinsiAndJabatan(idProvinsi, idJabatan);
            model.addAttribute("text", "Hasil pencarian pegawai berdasarkan provinsi dan jabatan");
        }
        else if(idProvinsi == null && idInstansi != null && idJabatan != null) {
            listPegawai = pegawaiService.getPegawaiDb().findByInstansiAndJabatan(idInstansi, idJabatan);
            model.addAttribute("text", "Hasil pencarian pegawai berdasarkan instansi dan jabatan");
        }
        else if(idProvinsi != null && idInstansi != null && idJabatan != null) {
            listPegawai = pegawaiService.getPegawaiDb().findByProvinsiAndInstansiAndJabatan(idProvinsi, idInstansi, idJabatan);
            model.addAttribute("text", "Hasil pencarian pegawai berdasarkan provinsi, instansi, dan jabatan");
        }

        List<ProvinsiModel> listProvinsi = provinsiService.getProvinsiDb().findAll();
        List<InstansiModel> listInstansi = instansiService.getInstansiDb().findAll();
        List<JabatanModel> listJabatan = jabatanService.getJabatanDb().findAll();

        model.addAttribute("listPegawai", listPegawai);
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);

        model.addAttribute("idProvinsi", idProvinsi);
        model.addAttribute("idInstansi", idInstansi);
        model.addAttribute("idJabatan", idJabatan);

        return "cariPegawai";
    }

    @RequestMapping(value = "/pegawai/tertua-termuda", method = RequestMethod.GET)
    private String getPegawaiTertuaTermuda(@RequestParam(value = "idInstansi") Long idInstansi, Model model) {
        PegawaiModel pegawaiTertua = pegawaiService.getPegawaiTertuaInstansi(idInstansi);
        PegawaiModel pegawaiTermuda = pegawaiService.getPegawaiTermudaInstansi(idInstansi);

        double gajiPegawaiTertua = pegawaiService.hitungGajiPegawai(pegawaiTertua);
        double gajiPegawaiTermuda = pegawaiService.hitungGajiPegawai(pegawaiTermuda);
        String gajiTertua = String.format ("%.0f", gajiPegawaiTertua);
        String gajiTermuda = String.format ("%.0f", gajiPegawaiTermuda);

        model.addAttribute("pegawaiTertua", pegawaiTertua);
        model.addAttribute("gajiTertua", gajiTertua);
        model.addAttribute("pegawaiTermuda", pegawaiTermuda);
        model.addAttribute("gajiTermuda", gajiTermuda);

        return "viewPegawaiTertuaTermuda";
    }

}
