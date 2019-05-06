package com.ocr.nicolas.escalade.controllers;


import com.ocr.nicolas.escalade.business.contract.CommentManager;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private CommentManager commentManager;

    /**
     * For display generic comments page
     * @param model model
     * @param element_Id -> for one element
     * @return Comments List
     */
    @RequestMapping(value="/comment/{element_Id}", method = RequestMethod.GET )
    public String comment(Model model, @PathVariable Integer element_Id) throws CommentException {

        // Models for display comments
        model.addAttribute("commentaire", commentManager.getListAllCommentForOneElementId(element_Id));
        return "comment";
    }
}