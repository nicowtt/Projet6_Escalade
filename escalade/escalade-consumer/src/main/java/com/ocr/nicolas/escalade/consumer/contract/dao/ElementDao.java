package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Element;

import java.util.List;

public interface ElementDao {
    void writeNewElement(Integer pId);
    Element getLastElement();
    void deleteOneElement(Integer pId);
    Element getOneElement(Integer pId);
    List<Element> getListElementByUser (Integer pId);
}
