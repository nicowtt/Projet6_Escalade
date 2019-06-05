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

    /**
     * For display delete confirmation
     *
     * @param id -> user id to delete
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/comfirmationForDeleteUser/{id}", method = RequestMethod.GET)
    public String comfirmationForDeleteUser(@PathVariable Integer id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        Utilisateur userToDelete = new Utilisateur();

        //set user to delete
        userToDelete.setId(id);

        // model for display "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());
            //model for user to delete
            model.addAttribute("user", userToDelete);
        }

        return "/ComfirmationJsp/deletingUser";
    }

    /**
     * For delete user and comments associate
     * @param id -> id of user to delete
     * @param model -> model
     * @param userSession -> user session
     * @return
     */
    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id, Model model, @SessionAttribute(value = "Utilisateur", required = false) Utilisateur userSession) {
        Utilisateur newUser = new Utilisateur();

        // model for display "log"
        if (userSession != null) {
            model.addAttribute("log", userSession.getEmail());

            //delete user (and user comments)
            userManager.deleteUser(id);

            // for display member function
            newUser = userManager.getUserBean(userSession.getEmail());
            model.addAttribute("user", newUser);
            //for display user list
            model.addAttribute("listUser", userManager.getAllUsers());
        }

        return "userList";
    }

}
