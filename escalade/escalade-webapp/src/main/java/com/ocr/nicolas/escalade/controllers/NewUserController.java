package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.PasswordEncoder;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
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
     * For writing new user on bdd
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
            //and write newUser on bdd
            if (newUser.getRaisonSociale().isEmpty()) {newUser.setRaisonSociale(null);}
            if (newUser.getAdresse().isEmpty()) {newUser.setAdresse(null);}
            if (newUser.getVille().isEmpty()) {newUser.setVille(null);}
            if (newUser.getPays().isEmpty()) {newUser.setPays(null);}
            if (newUser.getNumeroTelephone().isEmpty()) {newUser.setNumeroTelephone(null);}
            userManager.writeNewUser(newUser);

            return "ComfirmationJsp/userWritingOk";
        }
    }
}
