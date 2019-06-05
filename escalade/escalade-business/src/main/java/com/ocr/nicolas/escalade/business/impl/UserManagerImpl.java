package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.CommentDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.UserDao;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import com.ocr.nicolas.escalade.model.exception.UserException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class UserManagerImpl extends AbstractManager implements UserManager {

    @Inject
    private UserDao userDao;

    @Inject
    private CommentDao commentDao;

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
            try {
                vUserIdTransaction = userDao.getUserBean(pEmail);
            } catch (UserException e) {
                e.printStackTrace();
                try {
                    throw new UserException("L'utilisateur ou le mot de passe n'as pas été reconnu");
                } catch (UserException e1) {
                    e1.printStackTrace();
                }
            }

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

    /**
     * to get all user list
     *
     * @return
     */
    @Override
    public List<Utilisateur> getAllUsers() {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Utilisateur> vUserList = vTransactionTemplate.execute(transactionStatus -> {

            List<Utilisateur> vUserTransactionList = null;
            vUserTransactionList = userDao.getAllUsers();

            return vUserTransactionList;
        });
        return vUserList;
    }

    /**
     * For delete user and comment associate
     *
     * @param pId -> user id to delete
     */
    @Override
    public void deleteUser(int pId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {

                // delete comment associate of user
                commentDao.deleteCommentUser(pId);

                // delete user
                userDao.deleteUser(pId);
            }
        });
    }

}
