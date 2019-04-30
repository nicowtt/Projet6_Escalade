package com.ocr.nicolas.escalade.controllers;



import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class SiteController {

    static final Log logger = LogFactory.getLog(SiteController.class);

    @Inject
    private EscaladeDao escaladeDao;

    @RequestMapping(value="/siteEscalade/{id}", method = RequestMethod.GET )
    public String index(Model model, @PathVariable Integer id) {

        // je crée des models afin d'afficher mes tableaux pour le site 1 -> En beys:

        // Model pour les sites:
        model.addAttribute("site", escaladeDao.getListSite(id));
        // Model pour les secteurs:
        model.addAttribute("secteur", escaladeDao.getListSecteur(id));
        // Model pour les voies:
        model.addAttribute("voie", escaladeDao.getListVoieAllSite( id));

        return "siteEscalade";
    }

//    @RequestMapping(value="/siteEscalade/{id}/addComment", method = RequestMethod.GET )
//    public String addComment...
//
//    @RequestMapping(value="/siteEscalade/{id}/showComment/{commentId}", method = RequestMethod.GET )
//    public String showComment(Model model, @PathVariable Integer id, @PathVariable Integer commentId)

//    mettre en attribut :, @SessionAttribute -> pour gérer les session utilisateur

}
