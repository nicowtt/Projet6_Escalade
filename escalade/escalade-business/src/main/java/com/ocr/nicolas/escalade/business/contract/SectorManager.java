package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.bean.Site;

import java.util.List;

public interface SectorManager {

    List<Secteur> getListAllSectorForOneSite(int pSite);
    int getNbrSecteur(String pNom);
    void writeSectorOnBdd(Secteur pSector, int pUserId);
    List<Secteur> getOneSector(int pId);
    void updateSector(Secteur pSector);
}
