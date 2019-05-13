package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Utilisateur;


import java.util.List;

public interface UserManager {

    List<Utilisateur> getUserNameOfComment(int pElement_id);
    Utilisateur getUserBean(String pEmail);
    Utilisateur writeNewUser(Utilisateur pUser);
}
