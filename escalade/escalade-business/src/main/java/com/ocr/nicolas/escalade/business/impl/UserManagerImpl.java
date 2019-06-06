package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.ElementManager;
import com.ocr.nicolas.escalade.business.contract.UserManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.CommentDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.ElementDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.SiteDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.UserDao;
import com.ocr.nicolas.escalade.model.bean.Element;
import com.ocr.nicolas.escalade.model.bean.Site;
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

    @Inject
    private ElementDao elementDao;

    @Inject
    private SiteDao siteDao;

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

                List<Element> vListElement = new ArrayList<>();
                List<Element> vListElementCreatedSite = new ArrayList<>();
                boolean elementIsSite = false;

                // delete comment associate of user
                commentDao.deleteCommentUser(pId);

                // check user created site (3 parts):
                // 1/ list of element created for this user
                vListElement = elementDao.getListElementByUser(pId);

                // 2/ list of created site
                if (vListElement.isEmpty()) {
                } else {
                    for (int i = 0; i < vListElement.size(); i++) {
                        //check first element
                        elementIsSite = siteDao.checkIfElementIsSite(vListElement.get(i).getId());
                        if (elementIsSite) {
                            vListElementCreatedSite.add(vListElement.get(i));
                            vListElement.remove(i);
                        }
                    }
                }

                // 3/ delete site created if exist
                if (vListElementCreatedSite.isEmpty()) {
                } else {
                    for (int i = 0; i < vListElementCreatedSite.size(); i++) {
                        elementDao.deleteOneElement(vListElementCreatedSite.get(i).getId());
                    }
                }

                //delete other element created by user (topo papier, reservation)
                if (vListElement.isEmpty()) {
                } else {
                    for (int i = 0; i < vListElement.size(); i++) {
                        elementDao.deleteOneElement(vListElement.get(i).getId());
                    }
                }

                // finaly delete user
                userDao.deleteUser(pId);
            }
        });
    }

}
