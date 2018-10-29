package com.apap.sipeg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.sipeg.model.InstansiModel;
import com.apap.sipeg.model.JabatanModel;
import com.apap.sipeg.service.InstansiService;
import com.apap.sipeg.service.JabatanService;

/*
    PageController
*/

@Controller
public class PageController {
    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private InstansiService instansiService;

    @RequestMapping("/")
    public String home(Model model) {
        List<JabatanModel> listJabatan = jabatanService.getJabatanDb().findAll();
        List<InstansiModel> listInstansi = instansiService.getInstansiDb().findAll();

        model.addAttribute("listJabatan", listJabatan);
        model.addAttribute("listInstansi", listInstansi);
        return "home";
    }
}
