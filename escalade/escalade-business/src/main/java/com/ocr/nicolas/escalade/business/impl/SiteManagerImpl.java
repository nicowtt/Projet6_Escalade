package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.SiteManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.SiteDao;
import com.ocr.nicolas.escalade.model.bean.site.Site;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class SiteManagerImpl implements SiteManager {

    @Inject
    private SiteDao siteDao;

    /**
     * For get All site on a list
     *
     * @return all site list
     */
    @Override
    public List<Site> getListAllSite() {

        List<Site> vSite = new ArrayList<>();
        vSite = siteDao.getListAllSite();

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

        List<Site> vSite = new ArrayList<>();
        vSite = siteDao.getListOneSite(pSite);

        return vSite;
    }
}
