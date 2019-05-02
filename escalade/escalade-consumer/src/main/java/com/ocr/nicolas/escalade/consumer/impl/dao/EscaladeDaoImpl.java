package com.ocr.nicolas.escalade.consumer.impl.dao;


import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;

import com.ocr.nicolas.escalade.consumer.impl.rowmapper.*;
import com.ocr.nicolas.escalade.model.bean.commentaire.Commentaire;
import com.ocr.nicolas.escalade.model.bean.secteur.Secteur;
import com.ocr.nicolas.escalade.model.bean.site.Site;
import com.ocr.nicolas.escalade.model.bean.utilisateur.Utilisateur;
import com.ocr.nicolas.escalade.model.bean.voie.Voie;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;


@Named
public class EscaladeDaoImpl extends AbstractDAoImpl implements EscaladeDao {

    static final Log logger = LogFactory.getLog(EscaladeDaoImpl.class);


    /**
     * for count how many site exist
     * @return number of site
     */
    @Override
    public int getNbrAllSite() {

        // remplir une List avec les infos de la bdd
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDatasource());

        int vNbrSite =(int) vJdbcTemplate.queryForObject("SELECT COUNT (*) FROM public.site",
                Integer.class);

        return vNbrSite;

    }


    /**
     * for get ways List on sectors
     *
     * @param pSecteur -> id of sector
     * @return Ways List
     */
    @Override
    public List<Voie> getListVoie(int pSecteur) {

        String vSQL = "SELECT * FROM voie WHERE secteur_id = :secteur_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("secteur_id", pSecteur, Types.INTEGER);

        RowMapper<Voie> vRowMapper = new VoieRowMapper();

        List<Voie> vListVoie = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListVoie;

    }



    /**
     * for count sectors on site
     *
     * @param pNom name of site
     * @return sectors number
     */
    public int getNbrSecteur(String pNom) {

        String vSQL
                = "SELECT nombredesecteur FROM site"
                + " WHERE nom = ?";

        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDatasource());

        int vNbrSite = vJdbcTemplate.queryForObject(
                vSQL,
                Integer.class,
                pNom);

        return vNbrSite;
    }







    /**
     * For get Comment List for one Element_id
     *
     * @param pElement_id -> Comment element_id
     * @return List of comments
     */
    @Override
    public List<Commentaire> getListAllCommentForOneElementId(int pElement_id) {
        String vSQL = "SELECT * FROM commentaire WHERE element_id = :element_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("element_id", pElement_id, Types.INTEGER);

        RowMapper<Commentaire> vRowMapper = new CommentaireRowMapper();

        List<Commentaire> vListCommentaire = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListCommentaire;
    }


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
