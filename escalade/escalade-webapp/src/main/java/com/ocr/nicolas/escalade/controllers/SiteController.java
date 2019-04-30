package com.ocr.nicolas.escalade.controllers;



import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class SiteController {

    static final Log logger = LogFactory.getLog(SiteController.class);

    @Inject
    private EscaladeDao escaladeDao;

    @RequestMapping(value="/siteEscalade", method = RequestMethod.GET )
    public String index(Model model) {
        String site = "";

        // je vais chercher mes tableaux pour le site 1 -> En beys:

        // les sites:
        model.addAttribute("site", escaladeDao.getListSite(1));
        // les secteurs:
        model.addAttribute("secteur", escaladeDao.getListSecteur(1));
        // les voies:
        model.addAttribute("voie", escaladeDao.getListVoieAllSite( 1));

        return "siteEscalade";
    }
}