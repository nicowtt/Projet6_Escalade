package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.EscaladeManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;
import com.ocr.nicolas.escalade.model.bean.utilisateur.Utilisateur;


import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class EscaladeManagerImpl implements EscaladeManager {
    
    @Inject
    private EscaladeDao escaladeDao;
    
    public List<Utilisateur> getUserNameOfComment(int pElement_id) {
        
        List<Utilisateur> vUtilisateur = new ArrayList<>();
        vUtilisateur = escaladeDao.getUserNameOfComment(pElement_id);
        
        return  vUtilisateur;
    }
}
