package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Site;

import java.util.List;

public interface SiteDao {

    List<Site> getListAllSite();
    List<Site> getListOneSite(int pSite);
    int getNbrAllSite();
    List<Site> getListSiteWithFilter(String pCountry, String pDepartment, Integer pNbrSectors, String pSiteName);
    void addTagForOfficialSite(int pId);
}
