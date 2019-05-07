package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.UserDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.UserRowMapper;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
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

        RowMapper<Utilisateur> vRowMapper = new UserRowMapper();

        List<Utilisateur> vUtilisateur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vUtilisateur;
    }

    /**
     * for check if Email and password exist on bdd
     * @param pEmail
     * @param pPassword
     *
     * @return List of user
     */
    @Override
    public List<Utilisateur> checkUserEmailAndPassword(String pEmail, String pPassword) {

        String vSQL
                = "SELECT * FROM utilisateur"
                + " WHERE email = :pEmail"
                + "  AND motdepasse = :pPassword";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pEmail", pEmail, Types.VARCHAR);
        vParams.addValue("pPassword", pPassword, Types.VARCHAR);

        RowMapper<Utilisateur> vRowMapper = new UserRowMapper();

        List<Utilisateur> vUtilisateur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vUtilisateur;
    }

    /**
     * For find User_id with email
     * @param pEmail
     * @return -> bean Utilisateur
     */
    @Override
    public Utilisateur getUserIDWithEmail(String pEmail) {
        String vSQL
                = "SELECT * FROM utilisateur"
                + " WHERE email = :pEmail";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pEmail", pEmail, Types.VARCHAR);

        RowMapper<Utilisateur> vRowMapper = new UserRowMapper();

        Utilisateur vUtilisateur = vJdbcTemplate.queryForObject(vSQL,vParams,vRowMapper);

        return vUtilisateur;

    }
}
