package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SectorManager;
import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@SessionAttributes("Utilisateur")
public class UpdateSectorController {

    static final Log logger = LogFactory.getLog(UpdateSectorController.class);

    @Inject
    private SectorManager sectorManager;

    @RequestMapping(value = "/updateSector/{id}", method = RequestMethod.GET)
    public String updateSector(Model model, @PathVariable Integer id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // Model for display site (table)
            model.addAttribute("sectorToUpdate", sectorManager.getOneSector(id));
            model.addAttribute("secteur", new Secteur());

            return "updateSector";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    @RequestMapping(value = "/updateSector/{id}", method = RequestMethod.POST)
    public String updateSector(@Valid Secteur secteur, BindingResult bindingResult, @PathVariable Integer id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("secteur", secteur);

            logger.info("*************");
            logger.info("erreur lors de remplissage du formulaire de mise a jour d'un secteur");

            //for "log" session
            model.addAttribute("log", userSession.getEmail());
            // Model for display sector (table)
            model.addAttribute("sectorToUpdate", sectorManager.getOneSector(id));

            return "updateSector";
        } else {

            //update new sector on bdd
            sectorManager.updateSector(secteur);

            //for "log" session
            model.addAttribute("log", userSession.getEmail());

            return "ComfirmationJsp/topoWebOk";
        }
    }
}
