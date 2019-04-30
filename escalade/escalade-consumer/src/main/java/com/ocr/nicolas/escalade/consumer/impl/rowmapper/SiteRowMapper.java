package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.site.Site;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteRowMapper implements RowMapper<Site> {

    public Site mapRow(ResultSet resultSet, int i) throws SQLException {
        Site vSite = new Site(resultSet.getInt("id"));
        vSite.setNom(resultSet.getString("nom"));
        vSite.setDescription(resultSet.getString("description"));
        vSite.setLocalisationDepartement(resultSet.getString("localisationdepartement"));
        vSite.setLocalisationPays(resultSet.getString("localisationpays"));
        vSite.setUrlPhoto(resultSet.getString("urlphoto"));
        vSite.setNombreDeSecteur(resultSet.getInt("nombredesecteur"));
        vSite.setElement_id(resultSet.getInt("element_id"));
        return vSite;
    }


}
