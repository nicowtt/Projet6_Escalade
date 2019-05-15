package com.ocr.nicolas.escalade.controllers;


import com.ocr.nicolas.escalade.business.contract.SectorManager;
import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.business.contract.WayManager;
import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.bean.Voie;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Inject
    private SectorManager sectorManager;

    @Inject
    private WayManager wayManager;


    /**
     * for display create site page.
     *
     * @param model -> model
     * @param userSession -> user Session
     * @return
     */
    @RequestMapping(value="/createClimbingSite", method = RequestMethod.GET)
    public String createClimbingSite(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            model.addAttribute("site", new Site());

            return "createNewSite";
        } else {
            return "ErrorJsp/forceLogin";
        }

    }

    /**
     * For write new climbing site on BDD
     *
     * @param newSite -> bean site for validation
     * @param bindingResult -> for error of validation
     * @param userSession -> userSession
     * @return
     */
    @RequestMapping(value="/createClimbingSite", method = RequestMethod.POST)
    public String createClimbingSite(@Valid Site newSite, BindingResult bindingResult, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Utilisateur userOnBdd = new Utilisateur();

        if (bindingResult.hasErrors()) {

            logger.info("*************");
            logger.info("erreur lors du remplissage du formulaire de creation d'un nouveau site");

            // for "log" session
            model.addAttribute("log", userSession.getEmail());

            return "createNewSite";
        } else {
            //search for userId
            userOnBdd = userManager.getUserBean(userSession.getEmail());
            //write new climbing site on bdd
            siteManager.writeSiteOnBdd(newSite, userOnBdd.getId());


            return "ComfirmationJsp/topoWebOk";
        }

    }


    /**
     * For display create sector page
     *
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value="/createNewSector/{site_id}", method = RequestMethod.GET)
    public String createNewSector(@PathVariable Integer site_id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            Secteur sector = new Secteur();
            sector.setSite_id(site_id);

            model.addAttribute("log", userSession.getEmail());
            model.addAttribute("secteur", sector);


            return "createNewSector";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    /**
     * for create new sector with form in
     * @param site_id -> for know on which site we create new sector
     * @param newSector -> bean new sector in
     * @param bindingResult -> list of error (whenn user fill in form)
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value="/createNewSectorPost/{site_id}", method = RequestMethod.POST)
    public String createNewSectorPost(@PathVariable Integer site_id, @Valid Secteur newSector, BindingResult bindingResult, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Utilisateur userOnBdd = new Utilisateur();

        if (bindingResult.hasErrors()) {

            logger.info("*************");
            logger.info("erreur lors du remplissage du formulaire de creation d'un nouveau secteur");

            // for "log" session
            model.addAttribute("log", userSession.getEmail());

            return "createNewSector";
        } else {
            //search for userId
            userOnBdd = userManager.getUserBean(userSession.getEmail());
            //write new sector on bdd (need new element (user_id needed) and site_id (where we create new sector)
            sectorManager.writeSectorOnBdd(newSector, userOnBdd.getId());

            // for "log" session
            model.addAttribute("log", userSession.getEmail());

            return "ComfirmationJsp/topoWebOk";
        }
    }

    /**
     * for display form for create new way
     * @param secteur_id -> where new way will be create
     * @param model -> model
     * @param userSession -> user Session
     * @return
     */
    @RequestMapping(value="/createNewWay/{secteur_id}", method = RequestMethod.GET)
    public String createNewWay(@PathVariable Integer secteur_id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            Voie way = new Voie();
            way.setSecteur_id(secteur_id);

            model.addAttribute("log", userSession.getEmail());
            model.addAttribute("voie", way);


            return "createNewWay";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }


    /**
     * For get new way form and create new way
     *
     * @param secteur_id -> sector who we create new way in
     * @param newWay -> bean with new way information
     * @param bindingResult -> list of errors
     * @param model -> model
     * @param userSession -> user Session
     * @return
     */
    @RequestMapping(value="/createNewWayPost/{secteur_id}", method = RequestMethod.POST)
    public String createNewWayPost(@PathVariable Integer secteur_id, @Valid Voie newWay, BindingResult bindingResult, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Utilisateur userOnBdd = new Utilisateur();

        if (bindingResult.hasErrors()) {

            logger.info("*************");
            logger.info("erreur lors du remplissage du formulaire de creation d'une nouvelle voie");

            // for "log" session
            model.addAttribute("log", userSession.getEmail());
            return "createNewWay";
        } else {
            //search for userId
            userOnBdd = userManager.getUserBean(userSession.getEmail());
            //Write new way on bdd (need new element (user_id needed) and sector_id (where we create new way)
            wayManager.writeNewWay(newWay, userOnBdd.getId());

            // for "log" session
            model.addAttribute("log", userSession.getEmail());

            return "ComfirmationJsp/topoWebOk";
        }
    }


    }
