package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.ElementManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.*;
import com.ocr.nicolas.escalade.consumer.impl.dao.TopoPapierDaoImpl;
import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.bean.Voie;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
    private WayDao wayDao;

    @Inject
    private ElementDao elementDao;

    static final Log logger = LogFactory.getLog(ElementManagerImpl.class);


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
     * (when element (represent a climbing site here) is deleted
     * cascade on serveur delete all trace of site, sectors, ways, topoPapier, reservation)
     *
     * For deleting all comments link to this site, i need element id for each comments (
     *
     *
     * @param pId -> site id  to delete
     */
    @Override
    public void deleteElementsLinkSite(int pId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                List<Site> listSiteToDelete = new ArrayList<>();
                Site siteToDelete = new Site();
                List<Secteur> fullSectorList = new ArrayList<>();
                List<Voie> fullWaysList = new ArrayList<>();
                List<Integer> listElements = new ArrayList<>();
                Topopapier topoPaperToDelete = new Topopapier();
                Integer elementId;
                Integer elementIdSite;
                Integer elementIdTopoPapier = null;

                //1/ need site element id for delete climbing site -> with cascade all sectors/ways/topopaper is deleted
                listSiteToDelete = siteDao.getListOneSite(pId);
                siteToDelete = listSiteToDelete.get(0);
                elementIdSite = siteToDelete.getElement_id();

                //2/ need all comments element id for secteur /voie /topoPapier

                //2.1 -> get element id for all sectors of climbing site
                fullSectorList = sectorDao.getListAllSectorForOneSite(pId);
                //get element id for each sectors of site
                for (int i = 0; i < fullSectorList.size(); i++) {
                    elementId = fullSectorList.get(i).getElement_id();
                    listElements.add(elementId);
                }

                //2.2 -> get element id for all ways of climbing site
                fullWaysList = wayDao.getListAllWaysForOneSite(pId);
                for (int i = 0; i < fullWaysList.size(); i++) {
                    elementId = fullWaysList.get(i).getElement_id();
                    listElements.add(elementId);
                }

                //2.3 -> get topopaper element_id of climbing site
                topoPaperToDelete = topoPapierDao.getTopopaperLinkToOneSite(pId);
                elementIdTopoPapier = topoPaperToDelete.getElement_id();


                //3 deleting element
                //3.1 deleting element for site
                elementDao.deleteOneElement(elementIdSite); // cascade on serveur for site/sectors/Ways/topoPaper/booking
                //3.2 deleting element for comments of sectors/ways
                if (listElements.isEmpty()) {
                    logger.info("il n'y a aucun commentaire (sur site, secteur ou voie) a supprimer pour le site");
                } else {
                    for (int i = 0; i <  listElements.size(); i++) {
                        elementDao.deleteOneElement(listElements.get(i));
                    }
                }

                //3.3 delete element for comment of topoPaper
                if (elementIdTopoPapier != null) {
                    elementDao.deleteOneElement(elementIdTopoPapier);
                } else {
                    logger.info("il n'y a aucun commentaire (sur topo Papier) a supprimer pour le site");
                }

            }
        });
    }
}
