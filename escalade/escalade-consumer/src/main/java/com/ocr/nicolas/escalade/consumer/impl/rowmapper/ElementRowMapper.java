package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.Element;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementRowMapper implements RowMapper<Element> {


    @Override
    public Element mapRow(ResultSet resultSet, int i) throws SQLException {
        Element vElement = new Element(resultSet.getInt("id"));
        vElement.setUtilisateur_id(resultSet.getInt("utilisateur_id"));

        return vElement;
    }
}
