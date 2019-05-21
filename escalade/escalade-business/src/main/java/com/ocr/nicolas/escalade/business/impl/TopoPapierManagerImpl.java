package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.BookingDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.ElementDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.TopoPapierDao;
import com.ocr.nicolas.escalade.model.bean.Element;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.exception.SectorException;
import com.ocr.nicolas.escalade.model.exception.TopoPapierException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
public class TopoPapierManagerImpl extends AbstractManager implements TopoPapierManager {

    @Inject
    private TopoPapierDao topoPapierDao;

    @Inject
    private ElementDao elementDao;


    /**
     * For get topoPapier from bdd
     *
     * @param pIdUser-> user id
     * @return
     */
    @Override
    public List<Topopapier> getListTopoPapier(int pIdUser) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Topopapier> vTopoPapier = vTransactionTemplate.execute(transactionStatus -> {

            List<Topopapier> vTopoPapierTransaction = new ArrayList<>();
            vTopoPapierTransaction = topoPapierDao.getListTopoPapier(pIdUser);

            return vTopoPapierTransaction;
        });
        return vTopoPapier;
    }

    /**
     * For get only one topopapier
     * @param pId -> id of topoPapier
     * @return
     */
    @Override
    public List<Topopapier> getOneTopoPapier(int pId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Topopapier> vTopoPapier = vTransactionTemplate.execute(transactionStatus -> {

            List<Topopapier> vTopoPapierTransaction = new ArrayList<>();
            vTopoPapierTransaction = topoPapierDao.getOneTopoPapier(pId);

            return vTopoPapierTransaction;
        });
        return vTopoPapier;

    }

    /**
     * For update topoPapier
     *
     * @param pTopoPapier -> bean topoPapier
     * @param pElementId -> id of topoPapier
     */
    @Override
    public void changeAvailabilityTopoPapier(Topopapier pTopoPapier, int pElementId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    topoPapierDao.changeAvailabilityTopoPapier(pTopoPapier, pElementId);
                } catch (TopoPapierException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * For write new element and new topopapier on bdd
     * @param pTopopapier -> new bean topopapier
     * @param pUserId -> user ID
     */
    @Override
    public void writeNewTopoPapier(Topopapier pTopopapier, int pUserId) {
        final Element[] newElementOnBdd = {new Element()};

        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                //creating new element
                elementDao.writeNewElement(pUserId);
                //get this new element
                newElementOnBdd[0] = elementDao.getLastElement();
                //set element_id in bean "topopapier" -> pTopopapier
                pTopopapier.setElement_id(newElementOnBdd[0].getId());
                //finaly writing "topopapier" on BDD
                try {
                    topoPapierDao.writeNewTopoPapier(pTopopapier);
                } catch (TopoPapierException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * For get all paper topo available
     * @return
     */
    @Override
    public List<Topopapier> getListAllTopoPapierAvailable() {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Topopapier> vTopoPapierList = vTransactionTemplate.execute(transactionStatus -> {

            List<Topopapier> vTopoPapierListTransaction = new ArrayList<>();
            vTopoPapierListTransaction = topoPapierDao.getListAllTopoPapierAvailable();

            return vTopoPapierListTransaction;

        });
        return  vTopoPapierList;
    }
}
