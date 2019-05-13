package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.PasswordEncoder;
import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("Utilisateur")
public class LoginController {

    static final Log logger = LogFactory.getLog(LoginController.class);

    @Inject
    private SiteManager siteManager;

    @Inject
    private UserManager userManager;

    @Inject
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("Utilisateur")
    public Utilisateur setUpUserForm() {
        return new Utilisateur();
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * For User login
     * @param userSession -> user bean
     * @param model -> model for display all climbing site.
     * @return
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("Utilisateur") Utilisateur userSession, WebRequest request, SessionStatus status, Model model) {

        //variable
        boolean checkPassword = false;

        //Objet
        Utilisateur userBdd = new Utilisateur();

        //method for have UserBdd bean with sessionUser email
        if (userSession != null) {

            userBdd = userManager.getUserBean(userSession.getEmail());

            //method for check if password is good
            checkPassword = passwordEncoder.checkPassword(userSession.getMotDePasse(), userBdd.getMotDePasse());

            if (checkPassword) {
                model.addAttribute("log", userSession.getEmail());
            } else {
                status.setComplete();
                request.removeAttribute("Utilisateur", WebRequest.SCOPE_SESSION);
                return "ErrorJsp/errorLogin";
            }

        }
        //for display climbing site
        model.addAttribute("site", siteManager.getListAllSite());
        return "home";
    }

    /**
     * For logout
     * @param utilisateur -> user bean
     * @param model -> model for display all climbing site.
     * @return
     */
    @RequestMapping(value = "/dologout", method = RequestMethod.GET)
    public String doLogout(@ModelAttribute("Utilisateur") Utilisateur utilisateur, WebRequest request, SessionStatus status, Model model) {

        // remove session
        status.setComplete();
        request.removeAttribute("Utilisateur", WebRequest.SCOPE_SESSION);

        //for display climbing site
        model.addAttribute("site", siteManager.getListAllSite());
        return "home";
    }

}
