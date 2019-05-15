package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Topopapier;

import java.util.List;

public interface TopoPapierManager {

    List<Topopapier> getListTopoPapier(int pIdUser);
    List<Topopapier> getOneTopoPapier(int pElementId);
}
