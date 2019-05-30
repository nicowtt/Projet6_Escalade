package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Element;

public interface ElementManager {
    void deleteOneElementLinkTopoPaper(int pId);
    void deleteElementsLinkSite(int pId);
    Element getOneElement(Integer pId);
    void deleteOneElementLinkWay(int pWayId);
}
