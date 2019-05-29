package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.ElementDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.SiteDao;
import com.ocr.nicolas.escalade.model.bean.Element;
import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import com.ocr.nicolas.escalade.model.exception.SiteException;
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
public class SiteManagerImpl extends AbstractManager implements SiteManager {

    @Inject
    private SiteDao siteDao;

    @Inject
    private ElementDao elementDao;

    static final Log logger = LogFactory.getLog(SiteManagerImpl.class);

    /**
     * For get All site on a list
     *
     * @return all site list
     */
    @Override
    public List<Site> getListAllSite() {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Site> vSite = vTransactionTemplate.execute(transactionStatus -> {

            List<Site> vSiteTransaction = new ArrayList<>();
            vSiteTransaction = siteDao.getListAllSite();

            return vSiteTransaction;
        });
        return  vSite;
    }

    /**
     * For get one site
     *
     * @param pSite -> numero de site
     * @return liste de site
     */
    @Override
    public List<Site> getListOneSite(int pSite) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Site> vSite = vTransactionTemplate.execute(transactionStatus -> {

            List<Site> vSiteTransaction = new ArrayList<>();
            vSiteTransaction = siteDao.getListOneSite(pSite);

            return vSiteTransaction;
        });
        return vSite;
    }

    /**
     * for count how many site exist
     * @return number of site
     */
    @Override
    public int getNbrAllSite(){
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        int nbrAllSite = vTransactionTemplate.execute(transactionStatus -> {

            int nbrAllSiteTransaction = siteDao.getNbrAllSite();

            return nbrAllSiteTransaction;
        });

        return nbrAllSite;
    }

    /**
     * For have a site List with filter
     *
     * @param pCountry -> filter by country
     * @param pDepartment -> filter by department
     * @param pNbrSectors -> filter by sectors numbers
     * @param pSiteName -> filter by SiteName
     * @return -> list of site with filter
     */
    @Override
    public List<Site> getListSiteWithFilter(String pCountry, String pDepartment, Integer pNbrSectors, String pSiteName) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Site> vListSite = vTransactionTemplate.execute(transactionStatus -> {

            List<Site> vListSiteTransaction = new ArrayList<>();
            vListSiteTransaction = siteDao.getListSiteWithFilter(pCountry, pDepartment, pNbrSectors, pSiteName);

            return vListSiteTransaction;
        });
        return vListSite;
    }

    /**
     * For tag Official site of climbing friend
     *
     * @param pId -> site id
     */
    @Override
    public void addTagForOfficialSite(int pId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {

                siteDao.addTagForOfficialSite(pId);
            }
        });
    }

    /**
     * For create new element and write new site
     *
     * @param pSite -> bean new climbing site
     * @param pUserId -> userId
     */
    @Override
    public void writeSiteOnBdd(Site pSite, int pUserId) {
        final Element[] newElementOnBdd = {new Element()};

        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // creating new element
                elementDao.writeNewElement(pUserId);
                // get this new element
                newElementOnBdd[0] = elementDao.getLastElement();
                // set element_id in bean "site"-> pSite
                pSite.setElement_id(newElementOnBdd[0].getId());
                // finaly writing "site" on bdd
                try {
                    siteDao.writeSiteOnBdd(pSite);
                } catch (SiteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * For delete tag Official site of climbing friend
     *
     * @param pId -> site id
     */
    @Override
    public void deleteTagForOfficialSite(int pId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {

                siteDao.deleteTagForOfficialSite(pId);
            }
        });
    }

    /**
     * For have a site List with filter
     *
     * @param pCountry -> filter by country
     * @param pDepartment -> filter by department
     * @param pNbrSectors -> filter by sectors numbers
     * @param pSiteName -> filter by SiteName
     * @return -> list of site with filter
     */
    @Override
    public List<Site> getListSiteWithFilterMultiChoice(String pCountry, String pDepartment, Integer pNbrSectors, String pSiteName, int pNbrMax) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Site> vListSite = vTransactionTemplate.execute(transactionStatus -> {

            List<Site> vListSiteTransaction = new ArrayList<>();
            vListSiteTransaction = siteDao.getListSiteWithFilterMultiChoice(pCountry, pDepartment, pNbrSectors, pSiteName, pNbrMax);

            return vListSiteTransaction;
        });
        return vListSite;
    }

    /**
     * For delete on site list country repetition
     * @param pListFull
     * @return
     */
    @Override
    public List<Site> getListwithoutRepetitionOfCountry(List<Site> pListFull) {
        List<Site> listwithoutRepetition = new ArrayList<>();
        Site site = new Site();
        String country = "";
        int count = 0;

        // create new list (same as pListFull)
        if (pListFull != null) {
            for (int i = 0; i < pListFull.size(); i++) {
                site = pListFull.get(i);
                listwithoutRepetition.add(site);
            }
        }

        // delete repetition
        if (pListFull != null) {
            for (int i = 0; i < pListFull.size(); i++) {
                country = pListFull.get(i).getLocalisationPays();
                //for remove repetition
                for (int j = 0; j < listwithoutRepetition.size() ; j++) {
                    if (listwithoutRepetition.get(j).getLocalisationPays().equals(country)) {

                        if (count >= 1) {
                            listwithoutRepetition.remove(j);
                            logger.info("repetition effacé (choix recherche): pays");
                        }
                        count++;
                    }
                }
                count = 0;
            }



        }

        return listwithoutRepetition;
    }

    /**
     * For delete on site list department repetition
     *
     * @param pListFull
     * @return
     */
    @Override
    public List<Site> getListwithoutRepetitionOfDepartment(List<Site> pListFull) {
        List<Site> listwithoutRepetition = new ArrayList<>();
        Site site = new Site();
        String department = "";
        int count = 0;

        // create new list (same as pListFull)
        if (pListFull != null) {
            for (int i = 0; i < pListFull.size(); i++) {
                site = pListFull.get(i);
                listwithoutRepetition.add(site);
            }
        }

        // delete repetition
        if (pListFull != null) {
            for (int i = 0; i < pListFull.size(); i++) {
                department = pListFull.get(i).getLocalisationDepartement();
                //for remove repetition
                for (int j = 0; j < listwithoutRepetition.size() ; j++) {
                    if (listwithoutRepetition.get(j).getLocalisationDepartement().equals(department)) {

                        if (count >= 1) {
                            listwithoutRepetition.remove(j);
                            logger.info("repetition effacé (choix recherche): departement");
                        }
                        count++;
                    }
                }
                count = 0;
            }



        }

        return listwithoutRepetition;
    }

    /**
     * for delete on site list sector number repetition
     * @param pListFull
     * @return
     */
    @Override
    public List<Site> getListwithoutRepetitionOfSectorNumber(List<Site> pListFull) {
        List<Site> listwithoutRepetition = new ArrayList<>();
        Site site = new Site();
        int nbrSecteur;
        int count = 0;

        // create new list (same as pListFull)
        if (pListFull != null) {
            for (int i = 0; i < pListFull.size(); i++) {
                site = pListFull.get(i);
                listwithoutRepetition.add(site);
            }
        }

        // delete repetition
        if (pListFull != null) {
            for (int i = 0; i < pListFull.size(); i++) {
                nbrSecteur = pListFull.get(i).getNombreDeSecteur();
                //for remove repetition
                for (int j = 0; j < listwithoutRepetition.size() ; j++) {
                    if (listwithoutRepetition.get(j).getNombreDeSecteur().equals(nbrSecteur)) {

                        if (count >= 1) {
                            listwithoutRepetition.remove(j);
                            logger.info("repetition effacé (choix recherche): nombre de secteur");
                        }
                        count++;
                    }
                }
                count = 0;
            }

        }

        return listwithoutRepetition;
    }

    /**
     * For update site
     *
     * @param pSite-> site to update
     */
    @Override
    public void updateSite(Site pSite) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    siteDao.updateSite(pSite);
                } catch (CommentException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
