package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.WayManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.ElementDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.WayDao;
import com.ocr.nicolas.escalade.model.bean.Element;
import com.ocr.nicolas.escalade.model.bean.Voie;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import com.ocr.nicolas.escalade.model.exception.WayException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class WayManagerImpl extends AbstractManager implements WayManager {

    @Inject
    private WayDao wayDao;

    @Inject
    private ElementDao elementDao;


    /**
     *  for get Ways List on Site
     *
     * @param pSite
     * @return
     */
    @Override
    public List<Voie> getListAllWaysForOneSite (int pSite) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Voie> vVoie = vTransactionTemplate.execute(transactionStatus -> {

            List<Voie> vVoieTransaction = new ArrayList<>();
            vVoieTransaction = wayDao.getListAllWaysForOneSite(pSite);

            return vVoieTransaction;
        });
        return vVoie;
    }


    /**
     * for get ways List on sectors
     *
     * @param pSecteur -> id of sector
     * @return Ways List
     */
    @Override
    public List<Voie> getListVoie(int pSecteur) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Voie> vVoie = vTransactionTemplate.execute(transactionStatus -> {

            List<Voie> vVoieTransaction = new ArrayList<>();
            vVoieTransaction = wayDao.getListVoie(pSecteur);

            return vVoieTransaction;
        });
        return vVoie;
    }

    /**
     * For create one new element and new way.
     * @param pWay -> bean new way
     * @param pUserId -> user_id
     */
    @Override
    public void writeNewWay(Voie pWay, int pUserId) {
        final Element[] newElementOnBdd = {new Element()};

        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // create new element
                elementDao.writeNewElement(pUserId);
                // get this new element
                newElementOnBdd[0] = elementDao.getLastElement();
                // set element_id in bean "Voie" -> pWay
                pWay.setElement_id(newElementOnBdd[0].getId());
                // finaly write "Voie" on BDD
                try {
                    wayDao.writeWayOnBdd(pWay);
                } catch (WayException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * for get a list with one way
     * @param pId
     * @return
     */
    @Override
    public List<Voie> getListOneWay(int pId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Voie> vVoie = vTransactionTemplate.execute(transactionStatus -> {

            List<Voie> vVoieTransaction = new ArrayList<>();
            vVoieTransaction = wayDao.getListOneWay(pId);

            return vVoieTransaction;
        });
        return vVoie;
    }

    /**
     * For update one way
     *
     * @param pWay -> way to update
     */
    @Override
    public void updateWay(Voie pWay) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    wayDao.updateWay(pWay);
                } catch (CommentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
