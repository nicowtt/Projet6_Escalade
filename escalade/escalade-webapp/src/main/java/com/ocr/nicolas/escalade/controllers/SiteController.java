package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.*;

import com.ocr.nicolas.escalade.model.bean.Site;
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

    @Inject
    private ElementManager elementManager;

    /**
     * For display generic climbing site page
     *
     * @param model model
     * @param id    -> for one climbing site
     * @return
     */
    @RequestMapping(value = "/climbingSite/{id}", method = RequestMethod.GET)
    public String index(Model model, @PathVariable Integer id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        Utilisateur newUser = new Utilisateur();

        // Models for display all information about one climbing site
        model.addAttribute("site", siteManager.getListOneSite(id));
        model.addAttribute("secteur", sectorManager.getListAllSectorForOneSite(id));
        model.addAttribute("voie", wayManager.getListAllWaysForOneSite(id));

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            // for display member function
            newUser = userManager.getUserBean(userSession.getEmail());
            model.addAttribute("user", newUser);
        }

        return "climbingSite";
    }


    /**
     * For tag site -> Official friend of climbing site
     *
     * @param model       -> model
     * @param id          -> id of climbing site
     * @param userSession -> user in session
     * @return
     */
    @RequestMapping(value = "/addTagForOfficialSite/{id}", method = RequestMethod.GET)
    public String addTagForOfficialSite(Model model, @PathVariable Integer id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        boolean associativeMember;
        Utilisateur newUser = new Utilisateur();

        // User must log and associative member
        if (userSession != null) {
            //base login
            model.addAttribute("log", userSession.getEmail());

            // for display member function
            newUser = userManager.getUserBean(userSession.getEmail());
            model.addAttribute("user", newUser);


            //check if associative member
            Utilisateur userInBdd = userManager.getUserBean(userSession.getEmail());
            associativeMember = userInBdd.isMembreAssociation();
            if (associativeMember) {
                // -> method for tag climbing site
                siteManager.addTagForOfficialSite(id);
            } else {
                return "ErrorJsp/errorNotMember";
            }

        } else {
            return "ErrorJsp/forceLogin";
        }

        // Models for display all information about one climbing site
        model.addAttribute("site", siteManager.getListOneSite(id));
        model.addAttribute("secteur", sectorManager.getListAllSectorForOneSite(id));
        model.addAttribute("voie", wayManager.getListAllWaysForOneSite(id));

        return "climbingSite";
    }

    /**
     * For display delete comfirmation
     *
     * @param model       -> model
     * @param siteId      -> site id to delete
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/comfirmDeleteSite/{siteId}", method = RequestMethod.GET)
    public String comfirmDeleteSite(Model model, @PathVariable Integer siteId, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        boolean associativeMember;
        Site newSite = new Site();
        // User must log and associative member
        if (userSession != null) {
            //base login
            model.addAttribute("log", userSession.getEmail());

            //check if associative member
            Utilisateur userInBdd = userManager.getUserBean(userSession.getEmail());
            associativeMember = userInBdd.isMembreAssociation();
            if (associativeMember) {
                newSite.setId(siteId);
                model.addAttribute("site", newSite);

            } else {
                return "ErrorJsp/errorNotMember";
            }

        } else {
            return "ErrorJsp/forceLogin";
        }

        return "/ComfirmationJsp/deletingSite";
    }

    /**
     * For delete climbing site (+ automatic cascade for delete sectors, ways and paper topo. + comments )
     *
     * @param model -> model
     * @param siteId -> site id to delete
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/deleteSite/{siteId}", method = RequestMethod.GET)
    public String deleteSite(Model model, @PathVariable Integer siteId, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        if (userSession != null) {
            //base login
            model.addAttribute("log", userSession.getEmail());

            //for delete site (+ automatic cascade(sectors/ways/topoPaper/booking + all comments
            elementManager.deleteElementsLinkSite(siteId);

            //Model for display all site on home.jsp
            model.addAttribute("site", siteManager.getListAllSite());
        }
        return "home";

    }
}

