package com.ocr.nicolas.escalade.consumer.impl.dao;


import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.SecteurRowMapper;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.VoieRowMapper;
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
     * Pour savoir combien il y a de Site d'escalade dans le site
     * @return
     */
    @Override
    public int getNombreSite() {

        // remplir une List avec les infos de la bdd
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDatasource());

        int vNbrSite =(int) vJdbcTemplate.queryForObject("SELECT COUNT (*) FROM public.site",
                Integer.class);

        return vNbrSite;

    }


    /**
     * pour aller chercher dans la BDD la liste (bean) des voies d'un secteur.
     *
     * @param pSecteur -> numero du secteur
     * @return liste de voies
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
     * Pour aller chercher dans la BDD la liste (bean) des secteurs d'un site.
     *
     * @param pSite -> numero de site
     * @return liste de secteur
     */
    @Override
    public List<Secteur> getListSecteur(int pSite) {

        String vSQL = "SELECT * FROM secteur WHERE site_id = :site_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("site_id", pSite, Types.INTEGER);

        RowMapper<Secteur> vRowMapper = new SecteurRowMapper();

        List<Secteur> vListSecteur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListSecteur;
    }

    /**
     * pour trouver le nombre de secteur sur un site d'escalade
     *
     * @param pNom nom du site d'escalade
     * @return nombre de secteur
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
     *  pour afficher une liste de voie d'un site entier
     *
     * @param pSite
     * @return
     */
    public List<Voie> getListVoieAllSite(int pSite) {

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
}
