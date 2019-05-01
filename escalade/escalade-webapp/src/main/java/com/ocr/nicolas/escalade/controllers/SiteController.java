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
     * @param model model
     * @return home.jsp
     */
    @RequestMapping(value="/home", method = RequestMethod.GET )
    public String home(Model model) {
        //Model for display all site on home.jsp
        model.addAttribute("site", escaladeDao.getListAllSite());
        return "home";
    }

    /**
     * For display generic climbing site page
     * @param model model
     * @param id -> for one climbing site
     * @return
     */
    @RequestMapping(value="/climbingSite/{id}", method = RequestMethod.GET )
    public String index(Model model, @PathVariable Integer id) {

        // Models for display all information about one climbing site
        model.addAttribute("site", escaladeDao.getListOneSite(id));
        model.addAttribute("secteur", escaladeDao.getListOneSector(id));
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

        return "comment";
    }

//    @RequestMapping(value="/siteEscalade/{id}/addComment", method = RequestMethod.GET )
//    public String addComment...
//
//    @RequestMapping(value="/siteEscalade/{id}/showComment/{commentId}", method = RequestMethod.GET )
//    public String showComment(Model model, @PathVariable Integer id, @PathVariable Integer commentId)

//    mettre en attribut :, @SessionAttribute -> pour gérer les session utilisateur

}
