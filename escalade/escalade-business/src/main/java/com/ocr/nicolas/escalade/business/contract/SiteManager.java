package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.site.Site;

import java.util.List;

public interface SiteManager {

    List<Site> getListAllSite();
    List<Site> getListOneSite(int pSite);
    int getNbrAllSite();
}
