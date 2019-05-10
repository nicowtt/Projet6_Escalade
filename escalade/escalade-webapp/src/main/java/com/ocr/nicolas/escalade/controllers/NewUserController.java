package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.PasswordEncoder;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.omg.CORBA.Request;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.validation.Valid;


@Controller
public class NewUserController {

    static final Log logger = LogFactory.getLog(NewUserController.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserManager userManager;

    /**
     * For display new user inscrption form
     *
     * @param model -> model
     * @param userSession -> sessionUser
     * @return
     */
    @RequestMapping(value="/newUserGet", method = RequestMethod.GET)
    public String newUser(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        // Models for display new user form
        model.addAttribute("utilisateur", new Utilisateur());

        return "/newUser";
    }

    /**
     * For valid new user
     * @param newUser -> bean user to validate
     * @param bindingResult -> error List
     * @param model -> model
     * @param userSession -> user Session
     * @return
     */
    @RequestMapping(value="/newUser", method = RequestMethod.POST)
    public String newUserPost(@Valid Utilisateur newUser, BindingResult bindingResult, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("utilisateur", newUser);
            logger.info("*********");
            logger.info("erreur lors du remplissage formulaire enregistrement nouvel utilisateur");
            return "/newUser";
        } else {

            // Hashing password in
            String hashingPassword = passwordEncoder.hashPassword(newUser.getMotDePasse());
            //je set le bean newUser
            newUser.setMotDePasse(hashingPassword);
            //todo method for write newUser on bdd
        }








        return "/newUser";
    }

}
