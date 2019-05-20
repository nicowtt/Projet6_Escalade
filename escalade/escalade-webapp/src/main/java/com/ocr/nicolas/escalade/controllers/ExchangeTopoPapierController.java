package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.BookingManager;
import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Reservation;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;

@Controller
public class ExchangeTopoPapierController {

    static final Log logger = LogFactory.getLog(ExchangeTopoPapierController.class);

    @Inject
    private TopoPapierManager topoPapierManager;

    @Inject
    private UserManager userManager;

    @Inject
    private BookingManager bookingManager;

    /**
     * For display paper topo available
     *
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/exchangeTopoPapier", method = RequestMethod.GET)
    public String exchangeTopoPapier(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            //for List all paper topo available
            model.addAttribute("topoPapier", topoPapierManager.getListAllTopoPapierAvailable());

            return "/personalSpace";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    /**
     * For write new booking of paper topo on bdd
     *
     * @param model -> model
     * @param topoId -> which topo user want to booking
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/askForBooking/{topoId}", method = RequestMethod.GET)
    public String askForBooking(Model model, @PathVariable Integer topoId, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession ) {

        Reservation vNewBooking = new Reservation();
        Utilisateur userOnBdd = new Utilisateur();

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            //search for userId
            userOnBdd = userManager.getUserBean(userSession.getEmail());
            //set new bean NewBooking
            vNewBooking.setStatusReservation(true); //booking asking
            vNewBooking.setTopoPapier_id(topoId);
            vNewBooking.setUtilisateur_id(userOnBdd.getId());
            // finaly write on bdd
            bookingManager.writeBooking(vNewBooking);

            return "ComfirmationJsp/bookingOk";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

}
