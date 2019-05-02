package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.secteur.Secteur;

import java.util.List;

public interface SectorManager {

    List<Secteur> getListOneSector(int pSite);
}
