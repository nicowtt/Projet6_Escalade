package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.exception.UserException;

import java.util.List;

public interface UserDao {

    List<Utilisateur> getUserNameOfComment(int pElement_id);
    Utilisateur getUserBean(String pEmail) throws UserException;
    Utilisateur writeNewUser(Utilisateur pUser) throws UserException;
    String getUserBean(Topopapier pTopoPapier);
    List<Utilisateur> getAllUsers();
    void deleteUser(int pId);

}
