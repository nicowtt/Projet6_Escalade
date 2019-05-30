package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.ElementManager;
import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.bean.Voie;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;

@Controller
public class DeleteWayController {

    @Inject
    private SiteManager siteManager;

    @Inject
    private ElementManager elementManager;



    static final Log logger = LogFactory.getLog(DeleteWayController.class);

    /**
     *  For display confirmation page for delete one way
     * @param wayId -> way id to comfirm deleting
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/deleteWay/{wayId}", method = RequestMethod.GET)
    public String deleteWay(@PathVariable Integer wayId, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        Voie wayToDelete = new Voie();

        //set way id in wayToDelete
        wayToDelete.setId(wayId);

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // model display way to delete
            model.addAttribute("voie", wayToDelete);

            return "/ComfirmationJsp/deleteWay";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    /**
     * For delete element link to one way
     * @param wayId -> way id
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/confirmDeleteWay/{wayId}", method = RequestMethod.GET)
    public String confirmDeleteWay(@PathVariable Integer wayId, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            //delete element link to way
            elementManager.deleteOneElementLinkWay(wayId);

            //Model for display all site on home.jsp
            model.addAttribute("site", siteManager.getListAllSite());

            return "/home";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }
}
