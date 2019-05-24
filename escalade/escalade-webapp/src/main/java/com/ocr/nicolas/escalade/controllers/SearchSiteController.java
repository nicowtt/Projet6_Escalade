package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes("Utilisateur")
public class SearchSiteController {

    static final Log logger = LogFactory.getLog(SearchSiteController.class);

    @Inject
    private SiteManager siteManager;


    /**
     * For display multi critere search of climbing site
     *
     * @param model
     * @return -> page for search
     */
    @RequestMapping(value="/searchSite", method = RequestMethod.GET)
    public String searchSite (Model model,  @SessionAttribute(value = "Utilisateur", required = false) Utilisateur utilisateur) {
        model.addAttribute("search", new Site());

        //model with no repetition system
        model.addAttribute("pays", siteManager.getListwithoutRepetitionOfCountry(siteManager.getListAllSite()));
        model.addAttribute("departements", siteManager.getListwithoutRepetitionOfDepartment(siteManager.getListAllSite()));
        model.addAttribute("sectorNumber", siteManager.getListwithoutRepetitionOfSectorNumber(siteManager.getListAllSite()));
        // model for siteName
        model.addAttribute("site", siteManager.getListAllSite());

        // model for "log"
        if (utilisateur != null) {
            model.addAttribute("log", utilisateur.getEmail());
        }
        return "searchSite";
    }


}
