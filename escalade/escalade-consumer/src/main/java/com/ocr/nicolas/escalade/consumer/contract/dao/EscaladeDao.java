package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.voie.Voie;

import java.util.List;

public interface EscaladeDao {
    int getCountVoie();
    List<Voie> getListVoie(int pSecteur);
}
