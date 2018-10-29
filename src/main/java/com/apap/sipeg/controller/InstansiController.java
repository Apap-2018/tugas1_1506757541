package com.apap.sipeg.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.ProvinsiModel;
import com.apap.sipeg.service.InstansiService;
import com.apap.sipeg.service.ProvinsiService;

/*
    InstansiController
*/

@Controller
public class InstansiController {
    @Autowired
    private ProvinsiService provinsiService;

    @Autowired
    private InstansiService instansiService;

    @RequestMapping(value = "/pegawai/cekInstansi", method = RequestMethod.GET)

    private @ResponseBody Object cekInstansiAndProvinsi(@RequestParam(value = "idProvinsi") String idProvinsi, Model model) {
        List<InstansiModel> listInstansi = new ArrayList<InstansiModel>();
        if(idProvinsi.equalsIgnoreCase("0")) {
            listInstansi = instansiService.getInstansiDb().findAll();
        }
        else {
            ProvinsiModel provinsi = provinsiService.findProvinsiById(Long.parseLong(idProvinsi));
            listInstansi = provinsi.getListInstansi();
        }

        Object instansi = listInstansi;
        return instansi;
    }
}
