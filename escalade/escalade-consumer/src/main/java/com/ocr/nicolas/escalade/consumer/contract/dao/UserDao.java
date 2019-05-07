package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Utilisateur;

import java.util.List;

public interface UserDao {

    List<Utilisateur> getUserNameOfComment(int pElement_id);
    List<Utilisateur> checkUserEmailAndPassword(String pEmail, String pPassword);
    Utilisateur getUserIDWithEmail(String pEmail);
}
