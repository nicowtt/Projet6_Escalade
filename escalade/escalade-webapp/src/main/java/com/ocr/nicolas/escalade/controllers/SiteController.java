package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class SiteController {

    static final Log logger = LogFactory.getLog(SiteController.class);

    @Inject
    private EscaladeDao escaladeDao;

    /**
     * Redirection index.jsp to home.jsp
     * @param model
     * @return home.jsp
     */
    @RequestMapping(value="/home", method = RequestMethod.GET )
    public String home(Model model) {
        //For display all site
        model.addAttribute("site", escaladeDao.getLitAllSite());
        return "home";
    }

    /**
     * For display generic climbing site
     * @param model
     * @param id -> for one climbing site
     * @return
     */
    @RequestMapping(value="/climbingSite/{id}", method = RequestMethod.GET )
    public String index(Model model, @PathVariable Integer id) {

        // Model for one site:
        model.addAttribute("site", escaladeDao.getListOneSite(id));
        // Model for all sectors on one site:
        model.addAttribute("secteur", escaladeDao.getListOneSector(id));
        // Model for all ways on one site:
        model.addAttribute("voie", escaladeDao.getListAllWaysForOneSite(id));

        return "climbingSite";
    }

//    @RequestMapping(value="/siteEscalade/{id}/addComment", method = RequestMethod.GET )
//    public String addComment...
//
//    @RequestMapping(value="/siteEscalade/{id}/showComment/{commentId}", method = RequestMethod.GET )
//    public String showComment(Model model, @PathVariable Integer id, @PathVariable Integer commentId)

//    mettre en attribut :, @SessionAttribute -> pour gérer les session utilisateur

}
