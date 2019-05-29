package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.WayManager;
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
public class UpdateWayController {

    static final Log logger = LogFactory.getLog(UpdateWayController.class);

    @Inject
    private WayManager wayManager;


    /**
     * For display updateWay.jsp
     *
     * @param model -> model
     * @param id -> way id to update
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/updateWay/{id}", method = RequestMethod.GET)
    public String updateWay(Model model, @PathVariable Integer id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // Model for display site (table)
            model.addAttribute("wayToUpdate", wayManager.getListOneWay(id));
            model.addAttribute("voie", new Voie());

            return "updateWay";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    /**
     * For update one way
     *
     * @param voie -> way updated
     * @param bindingResult -> list of errors
     * @param id -> way id to update
     * @param model -> model
     * @param userSession -> users session
     * @return
     */
    @RequestMapping(value = "/updateWay/{id}", method = RequestMethod.POST)
    public String updateWayPost(@Valid Voie voie, BindingResult bindingResult, @PathVariable Integer id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("voie", voie);

            logger.info("*************");
            logger.info("erreur lors de remplissage du formulaire de mise a jour d'une voie");

            //for "log" session
            model.addAttribute("log", userSession.getEmail());
            // Model for display site (table)
            model.addAttribute("wayToUpdate", wayManager.getListOneWay(id));

            return "updateWay";
        } else {

            //update new climbing site on bdd
            wayManager.updateWay(voie);

            //for "log" session
            model.addAttribute("log", userSession.getEmail());

            return "ComfirmationJsp/topoWebOk";
        }
    }
}
