package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.commentaire.Commentaire;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentaireRowMapper implements RowMapper<Commentaire> {

    @Override
    public Commentaire mapRow(ResultSet resultSet, int i) throws SQLException {
        Commentaire vCommentaire = new Commentaire(resultSet.getInt("id"));
        vCommentaire.setDateCommentaire(resultSet.getTimestamp("datecommentaire"));
        vCommentaire.setElement_id(resultSet.getInt("element_id"));
        vCommentaire.setCommentaire(resultSet.getString("commentaire"));
        vCommentaire.setUtilisateur_id(resultSet.getInt("utilisateur_id"));

        return vCommentaire;
    }
}
