package com.ocr.nicolas.escalade.consumer.impl.dao;


import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;

import com.ocr.nicolas.escalade.consumer.impl.rowmapper.CommentaireRowMapper;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.SecteurRowMapper;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.SiteRowMapper;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.VoieRowMapper;
import com.ocr.nicolas.escalade.model.bean.commentaire.Commentaire;
import com.ocr.nicolas.escalade.model.bean.secteur.Secteur;
import com.ocr.nicolas.escalade.model.bean.site.Site;
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
     * for get sectors List on Site
     *
     * @param pSite -> numero de site
     * @return liste de secteur
     */
    @Override
    public List<Secteur> getListOneSector(int pSite) {

        String vSQL = "SELECT * FROM secteur WHERE site_id = :site_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("site_id", pSite, Types.INTEGER);

        RowMapper<Secteur> vRowMapper = new SecteurRowMapper();

        List<Secteur> vListSecteur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListSecteur;
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
     *  for get Ways List on Site
     *
     * @param pSite
     * @return
     */
    public List<Voie> getListAllWaysForOneSite(int pSite) {

        String vSQL
                = "SELECT * FROM voie"
                + " WHERE secteur_id IN ("
                + " SELECT secteur.id FROM secteur"
                + " WHERE site_id = :site_id)";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("site_id", pSite, Types.INTEGER);

        RowMapper<Voie> vRowMapper = new VoieRowMapper();

        List<Voie> vListSecteur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListSecteur;

    }

    /**
     * Pour aller chercher dans la BDD la liste (bean) d'un site.
     *
     * @param pSite -> numero de site
     * @return liste de site
     */
    @Override
    public List<Site> getListOneSite(int pSite) {

        String vSQL = "SELECT * FROM site WHERE id = :site_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("site_id", pSite, Types.INTEGER);

        RowMapper<Site> vRowMapper = new SiteRowMapper();

        List<Site> vListSite = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListSite;
    }


    @Override
    public List<Site> getListAllSite() {

        String vSQL = "SELECT * FROM site ";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        RowMapper<Site> vRowMapper = new SiteRowMapper();

        List<Site> vListSite = vJdbcTemplate.query(vSQL, vRowMapper);

        return vListSite;
    }


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


}
