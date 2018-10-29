package com.apap.sipeg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.sipeg.model.JabatanModel;
import com.apap.sipeg.service.JabatanService;

/*
    JabatanController
*/

@Controller
public class JabatanController {
    @Autowired
    private JabatanService jabatanService;

    private Long kode_id_jabatan;

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
    private String addJabatan(Model model) {
        model.addAttribute("jabatan", new JabatanModel());
        return "add-jabatan";
    }

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
    private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
        jabatanService.addJabatan(jabatan);

        model.addAttribute("jabatan", jabatan);
        return "successAddJabatan";
    }

    @RequestMapping(value = "/pegawai/cekJabatan", method = RequestMethod.POST)
    public @ResponseBody Object cekJabatan (Model model) {
        List <JabatanModel> jabatan = jabatanService.getJabatanDb().findAll();
        return jabatan;
    }


    @RequestMapping(value = "/jabatan/view")
    private String viewJabatan(@RequestParam("idJabatan") Long idJabatan, Model model) {
        JabatanModel jabatan = jabatanService.getDataJabatanById(idJabatan);
        kode_id_jabatan = idJabatan;
        model.addAttribute("jabatan", jabatan);
        return "viewJabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    private String updateJabatan(@RequestParam("idJabatan") Long idJabatan, Model model) {
        JabatanModel jabatan = jabatanService.getDataJabatanById(idJabatan);
        model.addAttribute("jabatan", jabatan);
        return "updateJabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    private String updateJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
        jabatan.setId(kode_id_jabatan);
        jabatanService.updateJabatan(jabatan);

        model.addAttribute("jabatan", jabatan);
        return "successUpdateJabatan";
    }

    @RequestMapping(value = "/jabatan/delete", method = RequestMethod.POST)
    private String deleteJabatan() {
        jabatanService.deleteJabatanById(kode_id_jabatan);
        return "deleteJabatan";
    }

    @RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
    private String viewAllJabatan(Model model) {
        List<JabatanModel> listJabatan = jabatanService.getJabatanDb().findAll();
        model.addAttribute("listJabatan", listJabatan);
        return "viewAllJabatan";
    }
}
