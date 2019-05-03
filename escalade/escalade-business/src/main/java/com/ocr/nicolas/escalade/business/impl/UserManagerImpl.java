package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.UserDao;
import com.ocr.nicolas.escalade.model.bean.utilisateur.Utilisateur;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class UserManagerImpl extends AbstractManager implements UserManager {

    @Inject
    private UserDao userDao;

    /**
     * For get User name of one comment.
     *
     * @param pElement_id -> user id
     * @return name String
     */
    @Override
    public List<Utilisateur> getUserNameOfComment(int pElement_id) {
        List<Utilisateur> vUtilisateur = new ArrayList<>();
        vUtilisateur = userDao.getUserNameOfComment(pElement_id);

        return vUtilisateur;
    }
}
