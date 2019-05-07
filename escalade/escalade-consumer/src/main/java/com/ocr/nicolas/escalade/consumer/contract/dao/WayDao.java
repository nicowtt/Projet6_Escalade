package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Voie;

import java.util.List;

public interface WayDao {

    List<Voie> getListAllWaysForOneSite (int pSite);
    List<Voie> getListVoie(int pSecteur);
}
