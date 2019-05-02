package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.UserDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.UtilisateurRowMapper;
import com.ocr.nicolas.escalade.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class UserDaoImpl extends AbstractDAoImpl implements UserDao {


    /**
     * For get User name of one comment.
     *
     * @param pElement_id -> user id
     * @return name String
     */
    @Override
    public List<Utilisateur> getUserNameOfComment(int pElement_id) {


        String vSQL
                = "SELECT * FROM utilisateur"
                + " JOIN commentaire ON commentaire.utilisateur_id = utilisateur.id"
                + "  WHERE element_id = :element_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("element_id", pElement_id, Types.INTEGER);

        RowMapper<Utilisateur> vRowMapper = new UtilisateurRowMapper();

        List<Utilisateur> vUtilisateur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vUtilisateur;
    }
}
