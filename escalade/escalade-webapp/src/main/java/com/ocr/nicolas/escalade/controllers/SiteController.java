package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SectorManager;
import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.business.contract.WayManager;

import com.ocr.nicolas.escalade.model.bean.utilisateur.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@SessionAttributes("Utilisateur")
public class SiteController {

    static final Log logger = LogFactory.getLog(SiteController.class);

    @Inject
    private SiteManager siteManager;

    @Inject
    private SectorManager sectorManager;

    @Inject
    private WayManager wayManager;

    /**
     * For display generic climbing site page
     * @param model model
     * @param id -> for one climbing site
     * @return
     */
    @RequestMapping(value="/climbingSite/{id}", method = RequestMethod.GET )
    public String index(Model model, @PathVariable Integer id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur utilisateur) {


        // Models for display all information about one climbing site
        model.addAttribute("site", siteManager.getListOneSite(id));
        model.addAttribute("secteur", sectorManager.getListOneSector(id));
        model.addAttribute("voie", wayManager.getListAllWaysForOneSite(id));

        // model for "utilisateur"
        if (utilisateur != null) {
            model.addAttribute("message", utilisateur.getEmail());
        }

        return "climbingSite";
    }




//    @RequestMapping(value="/siteEscalade/{id}/addComment", method = RequestMethod.GET )
//    public String addComment...
//
//    @RequestMapping(value="/siteEscalade/{id}/showComment/{commentId}", method = RequestMethod.GET )
//    public String showComment(Model model, @PathVariable Integer id, @PathVariable Integer commentId)

//    mettre en attribut :, @SessionAttribute -> pour gérer les session utilisateur

}
