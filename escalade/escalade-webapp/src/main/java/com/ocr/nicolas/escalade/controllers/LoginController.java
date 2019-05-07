package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("Utilisateur")
public class LoginController {

    @Inject
    private SiteManager siteManager;

    @Inject
    private UserManager userManager;

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
     * @param utilisateur -> user bean
     * @param model -> model for display all climbing site.
     * @return
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("Utilisateur") Utilisateur utilisateur, WebRequest request, SessionStatus status, Model model) {

        // check if couple (email + password) exist exist on bdd
        if (utilisateur != null) {
            List<Utilisateur> vUtilisateur = new ArrayList<>();
            String email = utilisateur.getEmail();
            String password = utilisateur.getMotDePasse();

            vUtilisateur = userManager.checkUserEmailAndPassword(email, password);

            // if email and password is wrong -> Removing Utilisateur session
            if (vUtilisateur.size() == 0) {
                status.setComplete();
                request.removeAttribute("Utilisateur", WebRequest.SCOPE_SESSION);
                return "ErrorLogin";
            } else {

            }
            // if email and password are good,
            for (int i = 0; i < vUtilisateur.size(); i++) {
                model.addAttribute("log", utilisateur.getEmail());
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
    public String doLogout(@ModelAttribute("Utilisateur") Utilisateur utilisateur, Model model) {
        utilisateur.setEmail(null);
        utilisateur.setMotDePasse(null);

        //for display climbing site
        model.addAttribute("site", siteManager.getListAllSite());
        return "home";
    }

}
