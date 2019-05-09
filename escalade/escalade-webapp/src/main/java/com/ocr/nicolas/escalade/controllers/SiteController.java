package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SectorManager;
import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.business.contract.WayManager;

import com.ocr.nicolas.escalade.model.bean.Utilisateur;
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

    @Inject
    private UserManager userManager;

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

        // model for "log"
        if (utilisateur != null) {
            model.addAttribute("log", utilisateur.getEmail());
        }

        return "climbingSite";
    }


    /**
     * For tag site -> Official friend of climbing site
     * @param model -> model
     * @param id -> id of climbing site
     * @param userSession -> user in session
     * @return
     */
    @RequestMapping(value="/addTagForOfficialSite/{id}", method = RequestMethod.GET)
    public String addTagForOfficialSite(Model model, @PathVariable Integer id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        boolean associativeMember;

        // User must log and associative member
        if (userSession != null) {
            //base login
            model.addAttribute("log", userSession.getEmail());
            //check if associative member
            Utilisateur userInBdd = userManager.getUserBean(userSession.getEmail());
            associativeMember = userInBdd.isMembreAssociation();
            if (associativeMember) {
                // -> method for tag climbing site
                siteManager.addTagForOfficialSite(id);
            } else {return "ErrorNotMember";}
        } else {return "ForceLogin";}

        // Models for display all information about one climbing site
        model.addAttribute("site", siteManager.getListOneSite(id));
        model.addAttribute("secteur", sectorManager.getListOneSector(id));
        model.addAttribute("voie", wayManager.getListAllWaysForOneSite(id));

        return "climbingSite";
    }




//    @RequestMapping(value="/siteEscalade/{id}/addComment", method = RequestMethod.GET )
//    public String addComment...
//
//    @RequestMapping(value="/siteEscalade/{id}/showComment/{commentId}", method = RequestMethod.GET )
//    public String showComment(Model model, @PathVariable Integer id, @PathVariable Integer commentId)

//    mettre en attribut :, @SessionAttribute -> pour gérer les session utilisateur

}
