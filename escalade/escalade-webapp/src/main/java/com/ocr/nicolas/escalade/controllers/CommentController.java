package com.ocr.nicolas.escalade.controllers;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ocr.nicolas.escalade.business.contract.EscaladeManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class CommentController {

    static final Log logger = LogFactory.getLog(CommentController.class);
    
    @Inject
    private EscaladeDao escaladeDao;

    @Inject
    private EscaladeManager escaladeManager;

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
}
