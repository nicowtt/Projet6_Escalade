package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.voie.Voie;

import java.util.List;

public interface WayManager {

    List<Voie> getListAllWaysForOneSite (int pSite);
    List<Voie> getListVoie(int pSecteur);
}
