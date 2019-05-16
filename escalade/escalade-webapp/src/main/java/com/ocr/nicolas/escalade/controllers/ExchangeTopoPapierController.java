package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;

@Controller
public class ExchangeTopoPapierController {

    static final Log logger = LogFactory.getLog(ExchangeTopoPapierController.class);

    @Inject
    private TopoPapierManager topoPapierManager;

    @RequestMapping(value = "/exchangeTopoPapier", method = RequestMethod.GET)
    public String exchangeTopoPapier(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {

        // model for "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            //for List all paper topo available
            model.addAttribute("topoPapier", topoPapierManager.getListAllTopoPapierAvailable());

            return "exchangeTopoPapier";
        } else {
            return "ErrorJsp/forceLogin";
        }
    }

}
