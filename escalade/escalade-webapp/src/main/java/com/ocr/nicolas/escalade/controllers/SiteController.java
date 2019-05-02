package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SectorManager;
import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;

import com.ocr.nicolas.escalade.business.contract.EscaladeManager;

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

    @Inject
    private EscaladeManager escaladeManager;

    @Inject
    private SiteManager siteManager;

    @Inject
    private SectorManager sectorManager;


    /**
     * For display generic climbing site page
     * @param model model
     * @param id -> for one climbing site
     * @return
     */
    @RequestMapping(value="/climbingSite/{id}", method = RequestMethod.GET )
    public String index(Model model, @PathVariable Integer id) {

        // Models for display all information about one climbing site
        model.addAttribute("site", siteManager.getListOneSite(id));
        model.addAttribute("secteur", sectorManager.getListOneSector(id));
        //todo create manager for ways
        model.addAttribute("voie", escaladeDao.getListAllWaysForOneSite(id));
        return "climbingSite";
    }


    /**
     * For display generic comments page
     * @param model model
     * @param element_Id -> for one element
     * @return Comments List
     */
    @RequestMapping(value="/comment/{element_Id}", method = RequestMethod.GET )
    public String comment(Model model, @PathVariable Integer element_Id) {

//        // Models for display comments
        model.addAttribute("commentaire", escaladeDao.getListAllCommentForOneElementId(element_Id));

        //-->>exemple correct je prend de la classe Manager
        model.addAttribute("utilisateur", escaladeManager.getUserNameOfComment(element_Id));

        return "comment";
    }

//    @RequestMapping(value="/siteEscalade/{id}/addComment", method = RequestMethod.GET )
//    public String addComment...
//
//    @RequestMapping(value="/siteEscalade/{id}/showComment/{commentId}", method = RequestMethod.GET )
//    public String showComment(Model model, @PathVariable Integer id, @PathVariable Integer commentId)

//    mettre en attribut :, @SessionAttribute -> pour gérer les session utilisateur

}
