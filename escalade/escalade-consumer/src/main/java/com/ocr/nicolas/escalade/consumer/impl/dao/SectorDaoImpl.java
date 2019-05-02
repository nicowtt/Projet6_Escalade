package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.SectorDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.SecteurRowMapper;
import com.ocr.nicolas.escalade.model.bean.secteur.Secteur;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class SectorDaoImpl extends AbstractDAoImpl implements SectorDao {


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
}
