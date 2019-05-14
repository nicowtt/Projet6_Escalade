package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;

@Controller
public class CreateTopoWebController {

    static final Log logger = LogFactory.getLog(CreateTopoWebController.class);

    @RequestMapping(value="/createTopoWeb", method = RequestMethod.GET)
    public String createTopoWeb(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            model.addAttribute("site", new Site());

            return "createTopoWeb";
        } else {
            return "ErrorJsp/forceLogin";
        }

    }

    @RequestMapping(value="/createTopoWeb", method = RequestMethod.POST)
    public String createTopoWeb(@Valid Site newSite, BindingResult bindingResult, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        if (bindingResult.hasErrors()) {

            logger.info("*************");
            logger.info("erreur lors du remplissage du formulaire de creation d'un nouveau site");
            return "createTopoWeb";
        }

        //todo method for write topoweb en BDD

        return "ComfirmationJsp/topoWebOk";
    }

}
