package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CreateTopoWebController {

    static final Log logger = LogFactory.getLog(CreateTopoWebController.class);

    @RequestMapping(value="/createTopoWeb", method = RequestMethod.GET)
    public String createTopoWeb(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            return "createTopoWeb";
        } else {
            return "ErrorJsp/forceLogin";
        }



    }

}
