package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Secteur;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import com.ocr.nicolas.escalade.model.exception.SectorException;

import java.util.List;

public interface SectorDao {

    List<Secteur> getListAllSectorForOneSite(int pSite);
    int getNbrSecteur(String pNom);
    void writeSectorOnBdd(Secteur pSector) throws SectorException;
    List<Secteur> getOneSector(int pId);
    void updateSector(Secteur pSector) throws SectorException;
}
