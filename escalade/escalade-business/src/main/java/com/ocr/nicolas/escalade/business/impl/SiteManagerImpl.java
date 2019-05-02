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

    @Override
    public List<Site> getListAllSite() {

        List<Site> vSite = new ArrayList<>();
        vSite = siteDao.getListAllSite();


        return  vSite;
    }
}
