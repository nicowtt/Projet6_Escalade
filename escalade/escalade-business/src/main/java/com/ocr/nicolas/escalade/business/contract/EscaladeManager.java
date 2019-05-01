package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.utilisateur.Utilisateur;

import java.util.List;

public interface EscaladeManager {

    List<Utilisateur> getUserNameOfComment(int pElement_id);
}
