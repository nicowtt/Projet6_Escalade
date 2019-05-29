package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Voie;

import java.util.List;

public interface WayManager {

    List<Voie> getListAllWaysForOneSite (int pSite);
    List<Voie> getListVoie(int pSecteur);
    void writeNewWay(Voie pWay, int pUserId);
    List<Voie> getListOneWay(int pId);
    void updateWay(Voie pWay);
}
