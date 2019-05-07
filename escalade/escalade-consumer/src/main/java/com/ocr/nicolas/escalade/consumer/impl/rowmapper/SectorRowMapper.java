package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.Secteur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectorRowMapper implements RowMapper<Secteur> {


    @Override
    public Secteur mapRow(ResultSet resultSet, int i) throws SQLException {
        Secteur vSecteur = new Secteur(resultSet.getInt("id"));
        vSecteur.setNomSecteur(resultSet.getString("nomsecteur"));
        vSecteur.setDescriptionSecteur(resultSet.getString("descriptionsecteur"));
        vSecteur.setAcces(resultSet.getString("acces"));
        vSecteur.setAltitudeBase(resultSet.getInt("altitudebase"));
        vSecteur.setOrientation(resultSet.getString("orientation"));
        vSecteur.setTypeRoche(resultSet.getString("typeroche"));
        vSecteur.setNombreDeVoies(resultSet.getInt("nombredevoies"));
        vSecteur.setCotation(resultSet.getString("cotation"));
        vSecteur.setUrlPhotoSecteur(resultSet.getString("urlphotosecteur"));
        vSecteur.setCoordonneGps(resultSet.getString("coordonnegps"));
        vSecteur.setElement_id(resultSet.getInt("element_id"));
        vSecteur.setSite_id(resultSet.getInt("site_id"));

        return vSecteur;
    }
}
