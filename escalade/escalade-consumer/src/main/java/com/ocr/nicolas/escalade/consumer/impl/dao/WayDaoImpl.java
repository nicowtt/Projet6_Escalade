package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.WayDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.VoieRowMapper;
import com.ocr.nicolas.escalade.model.bean.voie.Voie;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class WayDaoImpl extends AbstractDAoImpl implements WayDao {


    /**
     *  for get Ways List on Site
     *
     * @param pSite
     * @return
     */
    @Override
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

}
