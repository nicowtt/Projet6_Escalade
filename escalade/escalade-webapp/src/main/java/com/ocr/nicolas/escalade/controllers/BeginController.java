package com.ocr.nicolas.escalade.controllers;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BeginController {

    static final Log logger = LogFactory.getLog(BeginController.class);

    @RequestMapping(value="/index")
    public String index(Model model) {
        return "page2";
    }
}
