package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.SectorManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.ElementDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.SectorDao;
import com.ocr.nicolas.escalade.model.bean.Element;
import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.exception.SectorException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class SectorManagerImpl extends AbstractManager implements SectorManager {

    @Inject
    private SectorDao sectorDao;

    @Inject
    private ElementDao elementDao;

    /**
     * for get sectors List on Site
     *
     * @param pSite -> numero de site
     * @return liste de secteur
     */
    @Override
    public List<Secteur> getListAllSectorForOneSite(int pSite){

        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Secteur> vSecteur = vTransactionTemplate.execute(transactionStatus -> {

            List<Secteur> vSecteurTransaction = new ArrayList<>();
            vSecteurTransaction = sectorDao.getListAllSectorForOneSite(pSite);

            return vSecteurTransaction;
        });
        return vSecteur;
    }

    /**
     * for count sectors on site
     *
     * @param pNom name of site
     * @return sectors number
     */
    @Override
    public int getNbrSecteur(String pNom) {

        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        int nbrSector = vTransactionTemplate.execute(transactionStatus -> {

            int nbrSectorTransaction = sectorDao.getNbrSecteur(pNom);

            return nbrSectorTransaction;
        });
        return nbrSector;
    }

    /**
     * For create new element and new sector
     *
     * @param pSector -> bean sector in
     * @param pUserId -> user id for know who create this new sector
     */
    @Override
    public void writeSectorOnBdd(Secteur pSector, int pUserId) {
        final Element[] newElementOnBdd = {new Element()};

        TransactionTemplate vTransactionTEmplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTEmplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // creating new element
                elementDao.writeNewElement(pUserId);
                // get this new element
                newElementOnBdd[0] = elementDao.getLastElement();
                //set element_id in bean "secteur" -> pSector
                pSector.setElement_id(newElementOnBdd[0].getId());
                // finaly writing "secteur" on BDD
                try {
                    sectorDao.writeSectorOnBdd(pSector);
                } catch (SectorException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * For get one sector list
     * @param pId
     * @return
     */
    @Override
    public List<Secteur> getOneSector(int pId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Secteur> vListSector = vTransactionTemplate.execute(transactionStatus -> {

            List<Secteur> vListSectorTransaction = new ArrayList<>();
            vListSectorTransaction = sectorDao.getOneSector(pId);

            return vListSectorTransaction;
        });

        return vListSector;

    }
}
