package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.exception.UserException;

import java.util.List;

public interface UserDao {

    List<Utilisateur> getUserNameOfComment(int pElement_id);
    List<Utilisateur> checkUserEmailAndPassword(String pEmail, String pPassword);
    Utilisateur getUserBean(String pEmail);
    Utilisateur writeNewUser(Utilisateur pUser) throws UserException;

}
