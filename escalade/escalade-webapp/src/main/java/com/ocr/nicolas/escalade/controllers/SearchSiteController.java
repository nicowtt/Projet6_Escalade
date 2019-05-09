package com.ocr.nicolas.escalade.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("Utilisateur")
public class SearchSiteController {

    static final Log logger = LogFactory.getLog(SearchSiteController.class);


    /**
     * For search a climbing site
     *
     * @param model
     * @return -> page for search
     */
    @RequestMapping(value="/searchSite", method = RequestMethod.GET)
    public String searchSite (Model model) {

        return "searchSite";
    }
}
