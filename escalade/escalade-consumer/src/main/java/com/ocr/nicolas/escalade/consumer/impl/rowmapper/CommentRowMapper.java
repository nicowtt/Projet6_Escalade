package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.Commentaire;

import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import org.springframework.jdbc.core.RowMapper;



import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Commentaire> {


    @Override
    public Commentaire mapRow(ResultSet resultSet, int i) throws SQLException {
        Commentaire vCommentaire = new Commentaire(resultSet.getInt("id"));
        vCommentaire.setDateCommentaire(resultSet.getTimestamp("datecommentaire"));
        vCommentaire.setElement_id(resultSet.getInt("element_id"));
        vCommentaire.setCommentaire(resultSet.getString("commentaire"));

        //new bean "Utilisateur"
        Utilisateur vUtilisateur = new Utilisateur(resultSet.getInt("utilisateur_id"));
        //set utilisateur -> to be completed if needed
        vUtilisateur.setPrenom(resultSet.getString("prenom"));
        vUtilisateur.setMembreAssociation(resultSet.getBoolean("membreassociation"));

        //bean "Utilisateur" -> variable du bean "Commentaire"
        vCommentaire.setUtilisateur(vUtilisateur);


        return vCommentaire;
    }
}
