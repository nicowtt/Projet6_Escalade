package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.TopoPapierDao;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
public class TopoPapierManagerImpl extends AbstractManager implements TopoPapierManager {

    @Inject
    private TopoPapierDao topoPapierDao;


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
     * @param pElementId -> id of topoPapier
     * @return
     */
    @Override
    public List<Topopapier> getOneTopoPapier(int pElementId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Topopapier> vTopoPapier = vTransactionTemplate.execute(transactionStatus -> {

            List<Topopapier> vTopoPapierTransaction = new ArrayList<>();
            vTopoPapierTransaction = topoPapierDao.getOneTopoPapier(pElementId);

            return vTopoPapierTransaction;
        });
        return vTopoPapier;

    }
}
