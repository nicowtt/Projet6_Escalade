package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@SessionAttributes("Utilisateur")
public class UpdateTopoWebController {

    static final Log logger = LogFactory.getLog(UpdateTopoWebController.class);

    @Inject
    private SiteManager siteManager;

    @Inject
    private UserManager userManager;


    /**
     * For display updateSite.jsp
     *
     * @param model -> model
     * @param id -> site id to display
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/updateSite/{id}", method = RequestMethod.GET)
    public String updateSite(Model model, @PathVariable Integer id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession ) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // Model for display site (table)
            model.addAttribute("siteToUpdate", siteManager.getListOneSite(id));
            model.addAttribute("site", new Site());

            return "updateSite";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    /**
     * For display updateSite.jsp
     *
     * @param model -> model
     * @param id -> site id to display
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/updateSite/{id}", method = RequestMethod.POST)
    public String updateSitePost(@Valid Site site, BindingResult bindingResult, @PathVariable Integer id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("site", site);

            logger.info("*************");
            logger.info("erreur lors de remplissage du formulaire de mise a jour d'un site");

            //for "log" session
            model.addAttribute("log", userSession.getEmail());
            // Model for display site (table)
            model.addAttribute("siteToUpdate", siteManager.getListOneSite(id));

            return "updateSite";
        } else {

            //update new climbin site on bdd
            siteManager.updateSite(site);

            //for "log" session
            model.addAttribute("log", userSession.getEmail());

            return "ComfirmationJsp/topoWebOk";
        }
    }
}
