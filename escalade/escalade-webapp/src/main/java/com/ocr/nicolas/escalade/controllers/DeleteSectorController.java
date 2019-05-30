package com.ocr.nicolas.escalade.controllers;
import com.ocr.nicolas.escalade.business.contract.ElementManager;
import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;

@Controller
public class DeleteSectorController {

    static final Log logger = LogFactory.getLog(DeleteSectorController.class);

    @Inject
    private ElementManager elementManager;

    @Inject
    private SiteManager siteManager;

    @RequestMapping(value = "/deleteSector/{sectorId}", method = RequestMethod.GET)
    public String deleteWay(@PathVariable Integer sectorId, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        Secteur secteurToDelete = new Secteur();

        //set sector id in sectorToDelete
        secteurToDelete.setId(sectorId);

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // model display sector to delete
            model.addAttribute("secteur", secteurToDelete);

            return "/ComfirmationJsp/deletingSector";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }


    /**
     * For delete one sector (all way associate is deleted by serveur (CASCADE)
     * All sector and Way associate is deleted
     *
     * @param sectorId -> sector id to delete
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/confirmDeleteSector/{sectorId}", method = RequestMethod.GET)
    public String confirmDeleteWay(@PathVariable Integer sectorId, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            //delete element link to sector
            elementManager.deleteOneElementLinkSector(sectorId);

            //Model for display all site on home.jsp
            model.addAttribute("site", siteManager.getListAllSite());

            return "/home";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }


}
