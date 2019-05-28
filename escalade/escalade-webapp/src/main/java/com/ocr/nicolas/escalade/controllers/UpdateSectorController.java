package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SectorManager;
import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;

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
}
