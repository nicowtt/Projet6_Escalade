package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Site;

import java.util.List;

public interface SiteManager {

    List<Site> getListAllSite();
    List<Site> getListOneSite(int pSite);
    int getNbrAllSite();
    void addTagForOfficialSite(int pId);
    void writeSiteOnBdd(Site site, int pUserId);
    void deleteTagForOfficialSite(int pId);
    List<Site> getListSiteWithFilterMultiChoice(String pCountry, String pDepartment, Integer pNbrSectors, String pSiteName, int pNbrMax);
    void updateSite(Site pSite);
    List<String> getListAllSiteCountryNoRepeat();
    List<String> getListAllSiteDepartmentNoRepeat();
    List<Integer> getListAllSiteSectorNumberNoRepeat();
    List<String> getListAllSiteNameNoRepeat();
}
