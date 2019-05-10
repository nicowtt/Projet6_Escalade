package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.PasswordEncoder;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validator;


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
    @RequestMapping(value="/newUser", method = RequestMethod.GET)
    public String newUser(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        // Models for display new user form
        model.addAttribute("utilisateur", new Utilisateur());

        return "/newUser";
    }

    @RequestMapping(value="/newUserWrite", method = RequestMethod.POST)
    public String newUserPost(@Valid Utilisateur newUser, BindingResult bindingResult, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        System.out.println(bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("utilisateur", new Utilisateur());
            logger.info("*********");
            logger.info("erreur lors du remplissage formulaire enregistrement nouvel utilisateur");
            //todo je n'arrive pas a voir les erreur de mon bindingResult lorsque j'affiche la methode newUser (en get)
            return "/newUser";
        }

        // Hashing password in
        String hashingPassword = passwordEncoder.hashPassword(newUser.getMotDePasse());
        //je set le bean newUser
        newUser.setMotDePasse(hashingPassword);

        //todo method for write newUser on bdd



        return "/newUser";
    }

}
