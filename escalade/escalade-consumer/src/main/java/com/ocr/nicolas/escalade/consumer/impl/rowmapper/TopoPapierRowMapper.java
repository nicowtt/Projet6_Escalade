package com.ocr.nicolas.escalade.consumer.impl.rowmapper;


import com.ocr.nicolas.escalade.model.bean.Element;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopoPapierRowMapper implements RowMapper<Topopapier> {

    @Override
    public Topopapier mapRow(ResultSet resultSet, int i) throws SQLException {
        Topopapier vTopoPapier = new Topopapier(resultSet.getInt("id"));
        vTopoPapier.setNomTopo(resultSet.getString("nomtopo"));
        vTopoPapier.setDescription(resultSet.getString("description"));
        vTopoPapier.setNomCreateur(resultSet.getString("nomcreateur"));
        vTopoPapier.setDateCreation(resultSet.getString("datecreation"));
        vTopoPapier.setDateMaj(resultSet.getString("datemaj"));
        vTopoPapier.setDisponibilite(resultSet.getBoolean("disponibilite"));
        vTopoPapier.setSite_id(resultSet.getInt("site_id"));
        vTopoPapier.setElement_id(resultSet.getInt("element_id"));

        //new bean"Element"
        Element vElement = new Element(resultSet.getInt("id"));
        vElement.setUtilisateur_id(resultSet.getInt("utilisateur_id"));

        //new bean "site"
        Site vSite = new Site(resultSet.getInt("id"));
        vSite.setNomSite(resultSet.getString("nomsite"));

        //-> add if needed

        //set bean
        vTopoPapier.setSite(vSite);
        vTopoPapier.setElement(vElement);

        return vTopoPapier;

    }
}


