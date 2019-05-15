package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Topopapier;

import java.util.List;

public interface TopoPapierDao {

    List<Topopapier> getListTopoPapier(int pIdUser);
    List<Topopapier> getOneTopoPapier(int pElementId);
}
