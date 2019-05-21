package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.exception.TopoPapierException;

import java.util.List;

public interface TopoPapierDao {

    List<Topopapier> getListTopoPapier(int pIdUser);
    List<Topopapier> getOneTopoPapier(int pElementId);
    void changeAvailabilityTopoPapier(Topopapier pTopoPapier, int pElementId) throws TopoPapierException;
    void writeNewTopoPapier(Topopapier pTopopapier) throws TopoPapierException;
    List<Topopapier> getListAllTopoPapierAvailable();
    void changeBookingRequest(Topopapier pTopoPapier, int pElementId);
    Topopapier getOnlyOneTopopaper(int pId);
    void deleteTopoPaper(int pId);
}
