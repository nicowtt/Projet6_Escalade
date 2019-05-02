package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.secteur.Secteur;

import java.util.List;

public interface SectorDao {

    List<Secteur> getListOneSector(int pSite);
    int getNbrSecteur(String pNom);
}
