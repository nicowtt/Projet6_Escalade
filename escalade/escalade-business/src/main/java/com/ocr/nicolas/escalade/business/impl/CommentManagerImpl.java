package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.CommentManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.CommentDao;
import com.ocr.nicolas.escalade.model.bean.Commentaire;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import org.springframework.transaction.support.TransactionTemplate;


import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class CommentManagerImpl extends AbstractManager implements CommentManager {

    @Inject
    private CommentDao commentDao;


    /**
     * For get Comment List for one Element_id
     *
     * @param pElement_id -> Comment element_id
     * @return List of comments
     */
    @Override
    public List<Commentaire> getListAllCommentForOneElementId(int pElement_id) throws CommentException {

        //transaction methode confortable (rollback automatique en cas d'exception)
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Commentaire> vCommentaire = vTransactionTemplate.execute(transactionStatus -> {

            List<Commentaire> vCommentaireTransaction = null;
            vCommentaireTransaction = commentDao.getListAllCommentForOneElementId(pElement_id);

            return vCommentaireTransaction;
        });
        return vCommentaire;
    }
}

//        //transaction avec methode plus detaillé
//        DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
//        vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//        vDefinition.setTimeout(30); // 30 secondes
//        TransactionStatus vTransactionStatus = getPlatformTransactionManager().getTransaction(vDefinition);
//
//        List<Commentaire> vCommentaire = new ArrayList<>();
//        try {
//            //Method
//            vCommentaire = commentDao.getListAllCommentForOneElementId(pElement_id);
//
//            TransactionStatus vTScommit = vTransactionStatus;
//            vTransactionStatus = null;
//            getPlatformTransactionManager().commit(vTScommit);
//        } finally {
//            if (vTransactionStatus != null) {
//                getPlatformTransactionManager().rollback(vTransactionStatus);
//                throw new CommentException("Impossible de verifier les commentaires en BDD");
//            }
//        }
//
//        return vCommentaire;
