package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.business.impl.TopoPapierManagerImpl;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

            //display user "topoPapier"
            model.addAttribute("topoPapier", topoPapierManager.getListTopoPapier(userOnBdd.getId()));

            return "personalSpace";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    @RequestMapping(value="/availabilityTopoPapier/{element_id}", method = RequestMethod.GET)
    public String availabilityTopoPapier(@PathVariable Integer element_id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {


        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            //method for display only one topopapier
            model.addAttribute("topoPapier", new Topopapier());

            return "topoPapierUpdateAvailability";
        } else {
            return "ErrorJsp/forceLogin";
        }

    }


    @RequestMapping(value="/changeAvailabilityTopoPapier/{element_id}", method = RequestMethod.POST)
    public String changeAvailabilityTopoPapier(@PathVariable Integer element_id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Topopapier vTopoPapierIn = new Topopapier();
        Utilisateur userOnBdd = new Utilisateur();

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // new model for TopoPapier Form
            model.addAttribute("topoPapierIn", vTopoPapierIn);

            //search for user id
            userOnBdd = userManager.getUserBean(userSession.getEmail());

            //todo method for change topoPapier "disponibité"

            return "personalSpace";
        } else {
            return "ErrorJsp/forceLogin";
        }

    }

    }
