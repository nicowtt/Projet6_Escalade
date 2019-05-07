package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.Site;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteRowMapper implements RowMapper<Site> {

    public Site mapRow(ResultSet resultSet, int i) throws SQLException {
        Site vSite = new Site(resultSet.getInt("id"));
        vSite.setNomSite(resultSet.getString("nomsite"));
        vSite.setDescriptionSite(resultSet.getString("descriptionsite"));
        vSite.setLocalisationDepartement(resultSet.getString("localisationdepartement"));
        vSite.setLocalisationPays(resultSet.getString("localisationpays"));
        vSite.setUrlPhotoSite(resultSet.getString("urlphotosite"));
        vSite.setNombreDeSecteur(resultSet.getInt("nombredesecteur"));
        vSite.setElement_id(resultSet.getInt("element_id"));
        return vSite;
    }


}
