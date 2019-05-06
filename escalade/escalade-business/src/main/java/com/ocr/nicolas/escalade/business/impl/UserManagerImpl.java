package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.UserDao;
import com.ocr.nicolas.escalade.model.bean.commentaire.Commentaire;
import com.ocr.nicolas.escalade.model.bean.utilisateur.Utilisateur;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * for check if Email and password exist on bdd
     * @param pEmail
     * @param pPassword
     *
     * @return List of user
     */
    @Override
    public List<Utilisateur> checkUserEmailAndPassword(String pEmail, String pPassword) {
        //transaction (rollback automatique en cas d'exception)
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Utilisateur> vUtilisateur = vTransactionTemplate.execute(transactionStatus -> {

            List<Utilisateur> vUtilisateurTransaction = null;
            vUtilisateurTransaction = userDao.checkUserEmailAndPassword(pEmail, pPassword);

            return vUtilisateurTransaction;
        });

        return vUtilisateur;
    }
}
