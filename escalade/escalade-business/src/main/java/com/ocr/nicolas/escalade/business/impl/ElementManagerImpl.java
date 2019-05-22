package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.ElementManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.ElementDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.SectorDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.SiteDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.TopoPapierDao;
import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class ElementManagerImpl extends AbstractManager implements ElementManager {

    @Inject
    private TopoPapierDao topoPapierDao;

    @Inject
    private SiteDao siteDao;

    @Inject
    private SectorDao sectorDao;

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
    public void deleteOneElementLinkTopoPaper(int pId) {
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

    /**
     * For deleting site
     * (For information, when element (represent a climbing site here) is deleted
     * cascade on serveur delete all trace of site, sectors, ways, topoPapier, reservation)
     *
     * @param pId -> site id  to delete
     */
    @Override
    public void deleteOneElementLinkSite(int pId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                List<Site> fullSiteList = new ArrayList<>();
                List<Secteur> fullSectorList = new ArrayList<>();
                Site fullSite = new Site();

                //get element_id link of site for delete
                fullSiteList = siteDao.getListOneSite(pId);
                fullSite = fullSiteList.get(0);
                Integer element_id = fullSite.getElement_id();

                // delete element of site
                elementDao.deleteOneElement(element_id); // cascade on serveur for site/sectors/Ways/topoPaper/booking


                // reste element lié au site -> secteur /voie /commentaire
                //todo faire une liste d'element a suprimmer qui corresponde a ce site
                // pour les secteurs (method je lui envoi le siteId il me renvoi une liste d'element de secteur)
                fullSectorList = sectorDao.getListAllSectorForOneSite(pId);
            }
        });
    }
}
