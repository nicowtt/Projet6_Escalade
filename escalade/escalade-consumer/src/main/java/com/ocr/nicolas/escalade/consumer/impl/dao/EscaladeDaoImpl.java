package com.ocr.nicolas.escalade.consumer.impl.dao;


import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;

import com.ocr.nicolas.escalade.consumer.impl.rowmapper.*;
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
}
