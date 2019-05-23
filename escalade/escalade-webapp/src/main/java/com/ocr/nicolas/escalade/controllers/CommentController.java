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
    public String commentRead(Model model, @PathVariable Integer element_Id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) throws CommentException {

        // Models for display comments
        model.addAttribute("commentaire", commentManager.getListAllCommentForOneElementId(element_Id));

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
        }

        return "commentsRead";

    }

    /**
     * For display write comment (login required)  -> method get
     *
     * @param model model
     * @param element_Id -> for one element
     * @return commentWrite.jsp
     */
    @RequestMapping(value="/commentWrite/{element_Id}", method = RequestMethod.GET )
    public String commentWrite(Model model, @PathVariable Integer element_Id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) throws CommentException, UnsupportedEncodingException {

        // Models for display comments
        model.addAttribute("commentaire", new Commentaire());

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // for write i set element_id user session
            userSession.setElement_id(element_Id);
        } else {
            return "ErrorJsp/forceLogin";
        }
        return "commentWrite";

    }

    /**
     * For write comment (login required) -> method post
     *
     * @param commentaire -> for take information from jsp
     * @param bindingResult -> List of input errors
     * @param model -> model
     * @param userSession -> user session
     * @return -> display all comment for element
     * @throws CommentException
     */
    @RequestMapping(value="/commentWrite", method = RequestMethod.POST)
    public String commentWritePost(@Valid Commentaire commentaire, BindingResult bindingResult, Model model, @SessionAttribute(value = "Utilisateur") Utilisateur userSession ) throws CommentException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("commentaire", commentaire);
            logger.info("*********");
            logger.info("erreur lors du remplissage d'un nouveau commentaire");
            return "/commentWrite";
        } else {
            //object
            Commentaire vCommentaire = new Commentaire();
            Timestamp datecommentaire = new Timestamp(System.currentTimeMillis());

            //i take user email session
            String emailUserSession = userSession.getEmail();
            //and i use it on a method who takes user_id on BDD
            Utilisateur utilisateurBdd = userManager.getUserBean(emailUserSession);

            //now i set vCommentaire object
            vCommentaire.setDateCommentaire(datecommentaire);
            vCommentaire.setElement_id(userSession.getElement_id());
            vCommentaire.setCommentaire(commentaire.getCommentaire());
            vCommentaire.setUtilisateur_id(utilisateurBdd.getId());

            //and send this object for write comment on BDD
            commentManager.writeComment(vCommentaire);

            // Models for display comments
            model.addAttribute("commentaire", commentManager.getListAllCommentForOneElementId(userSession.getElement_id()));
            // model for display"login"
            model.addAttribute("log", userSession.getEmail());
            return "commentsRead";
        }
    }

    /**
     * For delete one comment if user is member associative
     *
     * @param model -> model
     * @param element_id -> element_id of comment
     * @param id -> id of comment
     * @param userSession -> bean session
     * @return -> display all comment without deleted one
     * @throws CommentException
     */
    @RequestMapping(value="/commentDelete/{element_id}/{id}", method = RequestMethod.GET)
    public String commentDelete(Model model, @PathVariable Integer element_id, @PathVariable Integer id, @SessionAttribute(value="Utilisateur", required = false) Utilisateur userSession) throws CommentException {
        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // for write i set element_id user session
            userSession.setElement_id(element_id);

            //check is user is member associative
            //i take user email session
            String emailUserSession = userSession.getEmail();
            //and i use it on a method who know if user is associative member(BDD)
            Utilisateur utilisateurBdd = userManager.getUserBean(emailUserSession);
            // finally i put information about member associative in user Session
            userSession.setMembreAssociation(utilisateurBdd.isMembreAssociation());

            // if user is associative member -> delete comment is ok
            if (userSession.isMembreAssociation()) {
                //-> Delete comment
                commentManager.deleteComment(id);
            } else {
                return "ErrorJsp/errorNotMember";
            }
        } else {
            return "ErrorJsp/forceLogin";
        }
        // Models for display comments
        model.addAttribute("commentaire", commentManager.getListAllCommentForOneElementId(userSession.getElement_id()));
        return "commentsRead";
    }


    /**
     * For display update comment page
     * @param model -> model
     * @param pId -> id of comment
     * @param userSession  -> userSession
     * @return
     */
    @RequestMapping(value="/oneCommentRead/{pId}", method = RequestMethod.GET)
    public String oneCommentRead(Model model, @PathVariable Integer pId, @SessionAttribute(value="Utilisateur", required = false) Utilisateur userSession) {

        // Model for log
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            //check if user is member associative
            //i take user email session
            String emailUserSession = userSession.getEmail();
            //and i use it on a method who know if user is associative member(BDD)
            Utilisateur utilisateurBdd = userManager.getUserBean(emailUserSession);
            // finally i put information about member associative in user Session
            userSession.setMembreAssociation(utilisateurBdd.isMembreAssociation());

            // if user is associative member -> function accés ok
            if (userSession.isMembreAssociation()) {

                model.addAttribute("commentaire", new Commentaire());
                model.addAttribute("comment", commentManager.displayOneComment(pId));
            } else {
                return "ErrorJsp/errorNotMember";
            }

        } else {
            return "ErrorJsp/forceLogin";
        }
        return "/commentUpdate";
    }


    /**
     * For update comment
     * @param commentaire -> new comment for update
     * @param model -> model
     * @param pId -> comment id
     * @param userSession -> sessionUser
     * @return comment updated
     * @throws CommentException
     */
    @RequestMapping(value="/updateComment/{pId}", method = RequestMethod.POST)
    public String updateComment(@Valid Commentaire commentaire, BindingResult bindingResult, Model model, @PathVariable Integer pId, @SessionAttribute(value="Utilisateur", required = false) Utilisateur userSession) throws CommentException {

        // model for "log"
        model.addAttribute("log", userSession.getEmail());

        if (bindingResult.hasErrors()) {
            model.addAttribute("commentaire", commentaire);
            logger.info("*********");
            logger.info("erreur lors du remplissage d'un nouveau commentaire");

            // model for display comment to update
            model.addAttribute("comment", commentManager.displayOneComment(pId));

            return "commentUpdate";
        } else {
            //-> set new updated comment
            Commentaire updatedComment = new Commentaire();
            updatedComment.setCommentaire(commentaire.getCommentaire());
            updatedComment.setId(pId);
            // -> method for update comment
            commentManager.updateComment(updatedComment);

            //model for display all comments
            model.addAttribute("commentaire", commentManager.displayOneComment(pId));

            return "/commentsRead";
        }
    }
}
