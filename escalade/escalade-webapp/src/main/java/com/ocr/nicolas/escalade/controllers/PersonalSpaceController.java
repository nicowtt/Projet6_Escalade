package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.BookingManager;
import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.business.impl.TopoPapierManagerImpl;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
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
public class PersonalSpaceController {

    static final Log logger = LogFactory.getLog(PersonalSpaceController.class);

    @Inject
    private TopoPapierManager topoPapierManager;

    @Inject
    private UserManager userManager;

    @Inject
    private BookingManager bookingManager;


    /**
     * For display personal space
     *
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value="/personalSpace", method = RequestMethod.GET)
    public String personalSpace(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Utilisateur userOnBdd = new Utilisateur();

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            //search for user id
            userOnBdd = userManager.getUserBean(userSession.getEmail());

            //display user "topoPapier"
            model.addAttribute("topoPapier", topoPapierManager.getListTopoPapier(userOnBdd.getId()));

            // display ask booking in progress
            model.addAttribute("reservationEnvoie", bookingManager.getListBookingAskForOneUser(userOnBdd.getId()));

            // display request booking (reception in french) only for user in session and if he have this paper topo and availability is ok and booking status on !
            model.addAttribute("reservationReception", bookingManager.getListAllTopoPapierWithBookingRequest(userOnBdd.getId()));

            return "personalSpace";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

    /**
     * For display change availability of topopapier function
     *
     * @param element_id -> id of "topoPapier"
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value="/availabilityTopoPapier/{element_id}", method = RequestMethod.GET)
    public String availabilityTopoPapier(@PathVariable Integer element_id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {


        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            //method for display only one topopapier
            model.addAttribute("topoPapier", topoPapierManager.getOneTopoPapier(element_id));
            model.addAttribute("updateTopoPapier", new Topopapier());

            return "topoPapierUpdateAvailability";
        } else {
            return "ErrorJsp/forceLogin";
        }

    }


    @RequestMapping(value="/changeAvailabilityTopoPapier/{id}", method = RequestMethod.POST)
    public String changeAvailabilityTopoPapier(@PathVariable Integer id, @Valid Topopapier topoForm, Model model, BindingResult bindingResult, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Utilisateur userOnBdd = new Utilisateur();

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            // new model for TopoPapier Form
            model.addAttribute("topoPapier", new Topopapier());

            //search for user id
            userOnBdd = userManager.getUserBean(userSession.getEmail());

            //method for change topoPapier "availability
            topoPapierManager.changeAvailabilityTopoPapier(topoForm, id);

            //display user "topoPapier"
            model.addAttribute("topoPapier", topoPapierManager.getListTopoPapier(userOnBdd.getId()));


            return "personalSpace";
        } else {
            return "ErrorJsp/forceLogin";
        }

    }

    /**
     * For accept booking topo paper
     *
     * @param model -> model
     * @param booking_Id -> id of booking
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/acceptBooking/{booking_Id}", method = RequestMethod.GET)
    public String acceptBooking(Model model, @PathVariable Integer booking_Id, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        Utilisateur userOnBdd = new Utilisateur();

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            //todo method pour accepter la reservation

            //search for user id
            userOnBdd = userManager.getUserBean(userSession.getEmail());

            //display user "topoPapier"
            model.addAttribute("topoPapier", topoPapierManager.getListTopoPapier(userOnBdd.getId()));

            // display ask booking in progress
            model.addAttribute("reservationEnvoie", bookingManager.getListBookingAskForOneUser(userOnBdd.getId()));

            // display request booking (reception in french) only for user in session and if he have this paper topo and availability is ok and booking status on !
            model.addAttribute("reservationReception", bookingManager.getListAllTopoPapierWithBookingRequest(userOnBdd.getId()));


            return "/personalSpace";
        } else {
            return "ErrorJsp/forceLogin";
        }

    }

    }
