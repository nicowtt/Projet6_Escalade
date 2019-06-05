package com.ocr.nicolas.escalade.controllers;


import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@Controller
@SessionAttributes("Utilisateur")
public class UserController {

    static final Log logger = LogFactory.getLog(UserController.class);

    @Inject
    private UserManager userManager;


    /**
     * For display userList.jsp
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/displayListMember", method = RequestMethod.GET)
    public String displayListMember(Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        Utilisateur newUser = new Utilisateur();

        // model for display "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            // for display member function
            newUser = userManager.getUserBean(userSession.getEmail());
            model.addAttribute("user", newUser);
            // for see all users
            model.addAttribute("listUser", userManager.getAllUsers());
        }

        return "userList";
    }

}
