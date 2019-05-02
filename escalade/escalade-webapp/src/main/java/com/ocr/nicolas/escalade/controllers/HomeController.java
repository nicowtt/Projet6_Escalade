package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SiteManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class HomeController {

    @Inject
    private SiteManager siteManager;

    static final Log logger = LogFactory.getLog(HomeController.class);


    /**
     * Redirection index.jsp to home.jsp
     * @param model model
     * @return home.jsp
     */
    @RequestMapping(value="/home", method = RequestMethod.GET )
    public String home(Model model) {
        //Model for display all site on home.jsp
        model.addAttribute("site", siteManager.getListAllSite());
        return "home";
    }

}
