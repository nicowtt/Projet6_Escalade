package com.ocr.nicolas.escalade.controllers;



import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;


@Controller
public class CreateTopoPaperController {

    static final Log logger = LogFactory.getLog(CreateTopoPaperController.class);


    /**
     * For display create new topo jsp
     *
     * @param model       -> model
     * @param userSession -> user Session
     * @return
     */
    @RequestMapping(value = "/createTopoPaper/{siteId}", method = RequestMethod.GET)
    public String createTopoPaper(@PathVariable Integer siteId, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Topopapier newTopoPaper = new Topopapier();
        //set element_id in newTopoPaper bean
        newTopoPaper.setSite_id(siteId);

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            model.addAttribute("topopaper", newTopoPaper);

            return "createTopoPaper";
        } else {
            return "ErrorJsp/forceLogin";
        }

    }

    @RequestMapping(value = "/createTopoPaper/{siteId}", method = RequestMethod.POST)
    public String createTopoPaper(@Valid Topopapier newTopoPaper, BindingResult bindingResult, @PathVariable Integer siteId, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("topopaper", newTopoPaper);
            //set site_id
            newTopoPaper.setSite_id(siteId);
            logger.info("*********");
            logger.info("erreur lors du remplissage formulaire enregistrement nouveau topo papier");

            // for "log" session
            model.addAttribute("log", userSession.getEmail());

            return "/createTopoPaper";
        } else {

            return "ComfirmationJsp/topoWebOk";
        }
    }


}
