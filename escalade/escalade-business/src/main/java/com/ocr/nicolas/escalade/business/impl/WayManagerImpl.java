package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.WayManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.WayDao;
import com.ocr.nicolas.escalade.model.bean.voie.Voie;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class WayManagerImpl extends AbstractManager implements WayManager {

    @Inject
    private WayDao wayDao;


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


}
