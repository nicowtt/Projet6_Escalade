package com.ocr.nicolas.escalade.controllers;


import com.ocr.nicolas.escalade.business.contract.CommentManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Commentaire;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

@Controller
public class CommentController {

    static final Log logger = LogFactory.getLog(CommentController.class);

    @Inject
    private CommentManager commentManager;

    @Inject
    private UserManager userManager;

    /**
     * For display comments page (no login required) depend of element_id
     *
     * @param model model
     * @param element_Id -> for one element
     * @return Comments List
     */
    @RequestMapping(value="/commentRead/{element_Id}", method = RequestMethod.GET )
    public String commentRead(Model model, @PathVariable Integer element_Id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur utilisateur) throws CommentException {

        // Models for display comments
        model.addAttribute("commentaire", commentManager.getListAllCommentForOneElementId(element_Id));

        // model for "log"
        if (utilisateur != null) {
            model.addAttribute("log", utilisateur.getEmail());
        }

        return "commentRead";

    }

    /**
     * For display write comment (login required)  -> method get
     *
     * @param model model
     * @param element_Id -> for one element
     * @return commentWrite.jsp
     */
    @RequestMapping(value="/commentWrite/{element_Id}", method = RequestMethod.GET )
    public String commentWrite(Model model, @PathVariable Integer element_Id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur utilisateur) throws CommentException, UnsupportedEncodingException {

        // Models for display comments
        model.addAttribute("comment", new Commentaire());
        model.addAttribute("commentaire", commentManager.getListAllCommentForOneElementId(element_Id));

        // model for "log"
        if (utilisateur != null) {
            model.addAttribute("log", utilisateur.getEmail());
            // for write i set element_id user session
            utilisateur.setElement_id(element_Id);
        } else {
            return "ForceLogin";
        }
        return "commentWrite";

    }

    /**
     * For write comment (login required) -> method post
     *
     * @param commentaire -> for take information from jsp
     * @param bindingResult
     * @param model -> model
     * @param utilisateur -> user session
     * @return -> display all comment for element
     * @throws CommentException
     */
    @RequestMapping(value="/commentWrite", method = RequestMethod.POST)
    public String commentWritePost(@Valid Commentaire commentaire, BindingResult bindingResult, Model model, @SessionAttribute(value = "Utilisateur") Utilisateur utilisateur ) throws CommentException{

        //object
        Commentaire vCommentaire = new Commentaire();
        Timestamp datecommentaire = new Timestamp(System.currentTimeMillis());

        //i take user email session
        String emailUserSession = utilisateur.getEmail();
        //and i use it on a method who takes user_id on BDD
        Utilisateur utilisateurBdd = userManager.getUserBean(emailUserSession);

        //now i set vCommentaire object
        vCommentaire.setDateCommentaire(datecommentaire);
        vCommentaire.setElement_id(utilisateur.getElement_id());
        vCommentaire.setCommentaire(commentaire.getCommentaire());
        vCommentaire.setUtilisateur_id(utilisateurBdd.getId());

        //and send this object for write comment on BDD
        commentManager.writeComment(vCommentaire);

        // Models for display comments
        model.addAttribute("commentaire", commentManager.getListAllCommentForOneElementId(utilisateur.getElement_id()));
        // model for display"login"
        model.addAttribute("log", utilisateur.getEmail());
        return "commentRead";
    }

    /**
     * For delete one comment if user is member associative
     *
     * @param model -> model
     * @param element_id -> element_id of comment
     * @param id -> id of comment
     * @param utilisateur -> bean session
     * @return -> display all comment without deleted one
     * @throws CommentException
     */
    @RequestMapping(value="/commentDelete/{element_id}/{id}", method = RequestMethod.GET)
    public String commentDelete(Model model, @PathVariable Integer element_id, @PathVariable Integer id, @SessionAttribute(value="Utilisateur", required = false) Utilisateur utilisateur) throws CommentException {
        // model for "log"
        if (utilisateur != null) {
            model.addAttribute("log", utilisateur.getEmail());
            // for write i set element_id user session
            utilisateur.setElement_id(element_id);

            //check is user is member associative
            //i take user email session
            String emailUserSession = utilisateur.getEmail();
            //and i use it on a method who know if user is associative member(BDD)
            Utilisateur utilisateurBdd = userManager.getUserBean(emailUserSession);
            // finally i put information about member associative in user Session
            utilisateur.setMembreAssociation(utilisateurBdd.isMembreAssociation());

            // if user is associative member -> delete comment is ok
            if (utilisateur.isMembreAssociation()) {
                //-> Delete comment
                commentManager.deleteComment(id);
            } else {
                return "ErrorNotMember";
            }
        } else {
            return "ForceLogin";
        }
        // Models for display comments
        model.addAttribute("commentaire", commentManager.getListAllCommentForOneElementId(utilisateur.getElement_id()));
        return "commentRead";
    }
}
