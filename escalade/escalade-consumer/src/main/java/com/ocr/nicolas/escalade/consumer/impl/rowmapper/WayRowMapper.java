package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.voie.Voie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WayRowMapper implements RowMapper<Voie> {

    @Override
    public Voie mapRow(ResultSet resultSet, int i) throws SQLException {
        Voie vVoie = new Voie(resultSet.getInt("id"));
        vVoie.setNumero(resultSet.getInt("numero"));
        vVoie.setNom(resultSet.getString("nom"));
        vVoie.setTempDescalade(resultSet.getInt("tempdescalade"));
        vVoie.setDescription(resultSet.getString("description"));
        vVoie.setLongueur(resultSet.getString("longueur"));
        vVoie.setCotation(resultSet.getString("cotation"));
        vVoie.setHauteur(resultSet.getInt("hauteur"));
        vVoie.setPrecisionEquipement(resultSet.getString("precisionequipement"));
        vVoie.setOuvertureEtEquipement(resultSet.getString("ouvertureetequipement"));
        vVoie.setDateOuverture(resultSet.getString("dateouverture"));
        vVoie.setStatut(resultSet.getString("statut"));
        vVoie.setElement_id(resultSet.getInt("element_id"));
        vVoie.setSecteur_id(resultSet.getInt("secteur_id"));
        return vVoie;
    }
}
