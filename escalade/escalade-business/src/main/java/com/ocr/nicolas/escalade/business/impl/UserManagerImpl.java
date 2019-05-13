package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.UserDao;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.exception.UserException;
import org.springframework.transaction.support.TransactionTemplate;

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
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Utilisateur> vUtilisateur = vTransactionTemplate.execute(transactionStatus -> {

            List<Utilisateur> vUtilisateurTransaction = new ArrayList<>();
            vUtilisateurTransaction = userDao.getUserNameOfComment(pElement_id);

            return vUtilisateurTransaction;
        });
        return vUtilisateur;
    }

    /**
     * For find User_id with email
     * @param pEmail
     * @return -> bean Utilisateur
     */
    @Override
    public Utilisateur getUserBean(String pEmail) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        Utilisateur vUserId = vTransactionTemplate.execute(transactionStatus -> {

            Utilisateur vUserIdTransaction = null;
            vUserIdTransaction = userDao.getUserBean(pEmail);

            return vUserIdTransaction;
        });
        return vUserId;
    }

    /**
     * For write new user
     *
     * @param pUser -> bean who come from Vue
     * @return
     */
    @Override
    public Utilisateur writeNewUser(Utilisateur pUser) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        Utilisateur vUser = vTransactionTemplate.execute(transactionStatus -> {

            Utilisateur vUserTransaction = null;
            try {
                vUserTransaction = userDao.writeNewUser(pUser);
            } catch (UserException e) {
                e.printStackTrace();
            }

            return vUserTransaction;
        });
        return  vUser;
    }
}
