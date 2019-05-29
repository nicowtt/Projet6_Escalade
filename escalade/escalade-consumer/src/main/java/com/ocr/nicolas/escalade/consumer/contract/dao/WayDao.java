package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Voie;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import com.ocr.nicolas.escalade.model.exception.WayException;

import java.util.List;

public interface WayDao {

    List<Voie> getListAllWaysForOneSite (int pSite);
    List<Voie> getListVoie(int pSecteur);
    void writeWayOnBdd(Voie pWay) throws WayException;
    List<Voie> getListOneWay(int pId);
    void updateWay(Voie pWay) throws CommentException;
}
