package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.utilisateur.Utilisateur;

import java.util.List;
import java.util.Map;

public interface UserManager {

    List<Utilisateur> getUserNameOfComment(int pElement_id);
    List<Utilisateur> checkUserEmailAndPassword(String pEmail, String pPassword);
}
