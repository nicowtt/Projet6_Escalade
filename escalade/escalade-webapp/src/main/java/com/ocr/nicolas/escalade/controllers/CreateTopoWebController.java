package com.ocr.nicolas.escalade.controllers;


import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;


import javax.inject.Inject;
import javax.validation.Valid;

@Controller
public class CreateTopoWebController {

    static final Log logger = LogFactory.getLog(CreateTopoWebController.class);

    @Inject
    private SiteManager siteManager;

    @Inject
    private UserManager userManager;



    @RequestMapping(value="/createTopoWeb", method = RequestMethod.GET)
    public String createTopoWeb(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            model.addAttribute("site", new Site());

            return "createTopoWeb";
        } else {
            return "ErrorJsp/forceLogin";
        }

    }

    /**
     * For write new climbing site on BDD
     *
     * @param newSite -> bean site for validation
     * @param bindingResult -> for error of validation
     * @param model -> model
     * @param userSession -> userSession
     * @return
     */
    @RequestMapping(value="/createTopoWeb", method = RequestMethod.POST)
    public String createTopoWeb(@Valid Site newSite, BindingResult bindingResult, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Utilisateur userOnBdd = new Utilisateur();

        if (bindingResult.hasErrors()) {

            logger.info("*************");
            logger.info("erreur lors du remplissage du formulaire de creation d'un nouveau site");
            return "createTopoWeb";
        } else {
            //search for userId
            userOnBdd = userManager.getUserBean(userSession.getEmail());
            //write new climbing site on bdd
            siteManager.writeSiteOnBdd(newSite, userOnBdd.getId());


            return "ComfirmationJsp/topoWebOk";
        }

    }

}
