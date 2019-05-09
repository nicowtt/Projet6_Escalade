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


@Controller
@SessionAttributes("Utilisateur")
public class SearchSiteController {

    static final Log logger = LogFactory.getLog(SearchSiteController.class);

    @Inject
    private SiteManager siteManager;


    /**
     * For search a climbing site
     *
     * @param model
     * @return -> page for search
     */
    @RequestMapping(value="/searchSite", method = RequestMethod.GET)
    public String searchSite (Model model,  @SessionAttribute(value = "Utilisateur", required = false) Utilisateur utilisateur) {
        model.addAttribute("search", new Site());
        model.addAttribute("site", siteManager.getListAllSite());

        // model for "log"
        if (utilisateur != null) {
            model.addAttribute("log", utilisateur.getEmail());
        }
        return "searchSite";
    }


}
