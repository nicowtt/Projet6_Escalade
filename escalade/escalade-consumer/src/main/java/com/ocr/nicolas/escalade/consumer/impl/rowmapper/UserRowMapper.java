package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<Utilisateur> {

    @Override
    public Utilisateur mapRow(ResultSet resultSet, int i) throws SQLException {
        Utilisateur vUtilisateur = new Utilisateur(resultSet.getInt("id"));
        vUtilisateur.setRaisonSociale(resultSet.getString("raisonsociale"));
        vUtilisateur.setNom(resultSet.getString("nom"));
        vUtilisateur.setPrenom(resultSet.getString("prenom"));
        vUtilisateur.setMotDePasse(resultSet.getString("motdepasse"));
        vUtilisateur.setAdresse(resultSet.getString("adresse"));
        vUtilisateur.setCodePostal(resultSet.getInt("codepostal"));
        vUtilisateur.setVille(resultSet.getString("ville"));
        vUtilisateur.setPays(resultSet.getString("pays"));
        vUtilisateur.setNumeroTelephone(resultSet.getString("numerotelephone"));
        vUtilisateur.setEmail(resultSet.getString("email"));
        vUtilisateur.setMembreAssociation(resultSet.getBoolean("membreassociation"));
        vUtilisateur.setAutre(resultSet.getString("autre"));

        return vUtilisateur;

    }
}
