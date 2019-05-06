package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.SectorManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.SectorDao;
import com.ocr.nicolas.escalade.model.bean.commentaire.Commentaire;
import com.ocr.nicolas.escalade.model.bean.secteur.Secteur;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class SectorManagerImpl extends AbstractManager implements SectorManager {

    @Inject
    private SectorDao sectorDao;

    /**
     * for get sectors List on Site
     *
     * @param pSite -> numero de site
     * @return liste de secteur
     */
    @Override
    public List<Secteur> getListOneSector(int pSite){

        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Secteur> vSecteur = vTransactionTemplate.execute(transactionStatus -> {

            List<Secteur> vSecteurTransaction = new ArrayList<>();
            vSecteurTransaction = sectorDao.getListOneSector(pSite);

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
}
