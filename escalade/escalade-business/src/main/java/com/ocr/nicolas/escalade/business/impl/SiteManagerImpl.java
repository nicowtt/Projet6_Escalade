package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.SiteDao;
import com.ocr.nicolas.escalade.model.bean.site.Site;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class SiteManagerImpl extends AbstractManager implements SiteManager {

    @Inject
    private SiteDao siteDao;

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
}
