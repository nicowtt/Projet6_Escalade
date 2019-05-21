package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.ElementManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.ElementDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.TopoPapierDao;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ElementManagerImpl extends AbstractManager implements ElementManager {

    @Inject
    private TopoPapierDao topoPapierDao;

    @Inject
    private ElementDao elementDao;


    /**
     * For deleting paper topo
     * (For information, when element (represent a paper topo here) is deleted
     * cascade on serveur delete all trace of booking and paper topo)
     *
     * @param pId -> paper topo id  to delete
     */
    @Override
    public void deleteOneElement(int pId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Topopapier fullTopoPapier = new Topopapier();

                //get element_id link of topoPapier for delete
                fullTopoPapier = topoPapierDao.getOnlyOneTopopaper(pId);
                Integer element_id = fullTopoPapier.getElement_id();

                // delete element of topo paper
                elementDao.deleteOneElement(element_id); // cascade on serveur -> link to booking and topopapier deleted
            }
        });
    }
}
