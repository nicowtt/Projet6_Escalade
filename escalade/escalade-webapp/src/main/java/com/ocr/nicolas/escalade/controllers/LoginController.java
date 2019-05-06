package com.ocr.nicolas.escalade.controllers;

import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.model.bean.utilisateur.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@SessionAttributes("Utilisateur")
public class LoginController {

    @Inject
    private SiteManager siteManager;

    @ModelAttribute("Utilisateur")
    public Utilisateur setUpUserForm() {
        return new Utilisateur();
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("Utilisateur") Utilisateur utilisateur, Model model) {

        //todo put in business Logic with bdd acces

        if (utilisateur.getEmail().equals("vincent.vega@gmail.com") && utilisateur.getMotDePasse().equals("pulpfiction")) {
            System.out.println("login OK");
            model.addAttribute("message", utilisateur.getEmail());
        } else {
            utilisateur.setEmail(null);
            utilisateur.setMotDePasse(null);
        }
        model.addAttribute("site", siteManager.getListAllSite());
        return "/home";
    }
}
