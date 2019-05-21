package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Topopapier;


import java.util.List;

public interface TopoPapierManager {

    List<Topopapier> getListTopoPapier(int pIdUser);
    List<Topopapier> getOneTopoPapier(int pId);
    void changeAvailabilityTopoPapier(Topopapier pTopoPapier, int pElementId);
    void writeNewTopoPapier(Topopapier pTopopapier, int pUserId);
    List<Topopapier> getListAllTopoPapierAvailable();
}
