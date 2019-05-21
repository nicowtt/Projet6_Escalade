package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.BookingManager;
import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;

@Controller
public class DeleteTopoPaperController {

    static final Log logger = LogFactory.getLog(DeleteTopoPaperController.class);

    @Inject
    private TopoPapierManager topoPapierManager;

    @Inject
    private BookingManager bookingManager;

    @Inject
    private UserManager userManager;


    @RequestMapping(value="/comfirmationForDeleteTopoPaper/{topoId}", method = RequestMethod.GET)
    public String comfirmationForDeleteTopoPaper(@PathVariable Integer topoId,  Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Topopapier deletingTopoPaper = new Topopapier();
        //set topoId in deletingTopoPaper
        deletingTopoPaper.setId(topoId);

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            model.addAttribute("topopapier", deletingTopoPaper);


            return "/ComfirmationJsp/deletingTopoPaper";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    @RequestMapping(value="/DeleteTopoPaper/{topoId}", method = RequestMethod.GET)
    public String DeleteTopoPaper(@PathVariable Integer topoId,  Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Topopapier deletingTopoPaper = new Topopapier();
        Utilisateur userOnBdd = new Utilisateur();

        //set topoId in deletingTopoPaper
        deletingTopoPaper.setId(topoId);

        //todo method for delete topoPapier

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            //for jsp display
            //search for user id
            userOnBdd = userManager.getUserBean(userSession.getEmail());
            //display user "topoPapier"
            model.addAttribute("topoPapier", topoPapierManager.getListTopoPapier(userOnBdd.getId()));
            // display ask booking in progress
            model.addAttribute("reservationEnvoie", bookingManager.getListBookingAskForOneUser(userOnBdd.getId()));
            // display request booking (reception in french) only for user in session and if he have this paper topo and availability is ok and booking status on !
            model.addAttribute("reservationReception", bookingManager.getListAllTopoPapierWithBookingRequest(userOnBdd.getId()));


            return "/ComfirmationJsp/deletingTopoPaper";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }


    }
