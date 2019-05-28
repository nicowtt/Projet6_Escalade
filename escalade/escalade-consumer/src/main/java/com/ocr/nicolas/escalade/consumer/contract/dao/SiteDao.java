package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import com.ocr.nicolas.escalade.model.exception.SiteException;

import java.util.List;

public interface SiteDao {

    List<Site> getListAllSite();
    List<Site> getListOneSite(int pSite);
    int getNbrAllSite();
    List<Site> getListSiteWithFilter(String pCountry, String pDepartment, Integer pNbrSectors, String pSiteName);
    void addTagForOfficialSite(int pId);
    void writeSiteOnBdd(Site site) throws SiteException;
    void deleteTagForOfficialSite(int pId);
    List<Site> getListSiteWithFilterMultiChoice(String pCountry, String pDepartment, Integer pNbrSectors, String pSiteName, int pNbrMax);
    void updateSite(Site pSite) throws CommentException;
}
