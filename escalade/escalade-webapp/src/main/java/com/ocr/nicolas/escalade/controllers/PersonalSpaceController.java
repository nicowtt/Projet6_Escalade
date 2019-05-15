package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.inject.Inject;

@Controller
@SessionAttributes("Utilisateur")
public class PersonalSpaceController {

    static final Log logger = LogFactory.getLog(PersonalSpaceController.class);

    @Inject
    private TopoPapierManager topoPapierManager;

    @Inject
    private UserManager userManager;


    @RequestMapping(value="/personalSpace", method = RequestMethod.GET)
    public String personalSpace(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Utilisateur userOnBdd = new Utilisateur();

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            //search for user id
            userOnBdd = userManager.getUserBean(userSession.getEmail());

            //todo methode pour afficher les topo papier que l'utilisateur possède
            model.addAttribute("topoPapier", topoPapierManager.getListTopoPapier(userOnBdd.getId()));



            return "personalSpace";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    }
