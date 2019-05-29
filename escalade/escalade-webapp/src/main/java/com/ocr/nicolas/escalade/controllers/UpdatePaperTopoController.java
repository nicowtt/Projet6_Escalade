package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.bean.Voie;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@SessionAttributes("Utilisateur")
public class UpdatePaperTopoController {

    static final Log logger = LogFactory.getLog(UpdatePaperTopoController.class);

    @Inject
    private TopoPapierManager topoPapierManager;


    /**
     * For display updateTopoPaper.jsp
     *
     * @param model       -> model
     * @param id          -> topo paper id to update
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/updatePaperTopo/{id}", method = RequestMethod.GET)
    public String updatePaperTopo(Model model, @PathVariable Integer id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // Model for display site (table)
            model.addAttribute("topoPaperToUpdate", topoPapierManager.getOneTopoPapier(id));
            model.addAttribute("topopapier", new Topopapier());

            return "updateTopoPaper";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    /**
     * For update one topo paper
     *
     * @param topopapier -> for validation
     * @param bindingResult -> list of potential errors
     * @param id -> topo paper id to update
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/updatePaperTopo/{id}", method = RequestMethod.POST)
    public String updatePaperTopo(@Valid Topopapier topopapier, BindingResult bindingResult, @PathVariable Integer id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("topopapier", topopapier);

            logger.info("*************");
            logger.info("erreur lors de remplissage du formulaire de mise a jour d'un topo papier");

            //for "log" session
            model.addAttribute("log", userSession.getEmail());
            // Model for display site (table)
            model.addAttribute("topoPaperToUpdate", topoPapierManager.getOneTopoPapier(id));

            return "updateTopoPaper";
        } else {

            //update new climbing site on bdd
            topoPapierManager.updateTopoPaper(topopapier);

            //for "log" session
            model.addAttribute("log", userSession.getEmail());

            return "ComfirmationJsp/topoWebOk";
        }

    }
}

