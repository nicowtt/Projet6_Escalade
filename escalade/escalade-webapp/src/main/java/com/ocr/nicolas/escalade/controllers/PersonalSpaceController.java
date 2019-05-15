package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("Utilisateur")
public class PersonalSpaceController {

    static final Log logger = LogFactory.getLog(PersonalSpaceController.class);


    @RequestMapping(value="/personalSpace", method = RequestMethod.GET)
    public String personalSpace(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());


            return "personalSpace";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    }
