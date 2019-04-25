package com.ocr.nicolas.escalade.controllers;



import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
public class BeginController {

    static final Log logger = LogFactory.getLog(BeginController.class);

    @Inject
    private EscaladeDao escaladeDao;

    @RequestMapping(value="/index")
    public String index(Model model) {
//        int nbrVoie = escaladeDao.getCountVoie();
//        logger.info(nbrVoie);
        model.addAttribute("voie", escaladeDao.getListVoie( 1));

        return "enbays";
    }
}
