package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.UserDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.UserRowMapper;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.exception.UserException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class UserDaoImpl extends AbstractDAoImpl implements UserDao {

    static final Log logger = LogFactory.getLog(UserDaoImpl.class);


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
    public Utilisateur getUserBean(String pEmail) {
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

    /**
     * For write new user
     *
     * @param pUser -> bean who come from Vue
     * @return -> bean User
     * @throws UserException
     */
    @Override
    public Utilisateur writeNewUser(Utilisateur pUser) throws UserException {
        String vSQL
                = "INSERT INTO utilisateur (raisonsociale, nom, prenom, motdepasse, adresse, codepostal, ville, pays, numerotelephone, email, membreassociation, autre)"
                + " VALUES (:raisonsociale, :nom, :prenom, :motdepasse, :adresse, :codepostal, :ville, :pays, :numerotelephone, :email, :membreassociation, :autre)";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("raisonsociale", pUser.getRaisonSociale(), Types.VARCHAR);
        vParams.addValue("nom", pUser.getNom(), Types.VARCHAR);
        vParams.addValue("prenom", pUser.getPrenom(), Types.VARCHAR);
        vParams.addValue("motdepasse", pUser.getMotDePasse(), Types.VARCHAR);
        vParams.addValue("adresse", pUser.getAdresse(), Types.VARCHAR);
        vParams.addValue("codepostal", pUser.getCodePostal(), Types.INTEGER);
        vParams.addValue("ville", pUser.getVille(), Types.VARCHAR);
        vParams.addValue("pays", pUser.getPays(), Types.VARCHAR);
        vParams.addValue("numerotelephone", pUser.getNumeroTelephone(), Types.VARCHAR);
        vParams.addValue("email", pUser.getEmail(), Types.VARCHAR);
        vParams.addValue("membreassociation", pUser.isMembreAssociation(), Types.BOOLEAN);
        vParams.addValue("autre", pUser.getAutre(), Types.VARCHAR);

        try{
            vJdbcTemplate.update(vSQL, vParams);
        } catch (DuplicateKeyException vEx) {

            logger.debug("L'utilisateur existe deja ! pseudo=" );// todo ici il faudra rajouter le pseudo
            //retour
            throw new UserException("L'utilisateur existe deja ! pseudo=");
        }

        return pUser;
    }
}
