package com.ocr.nicolas.escalade.consumer.impl.dao;


import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.VoieRowMapper;
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


    //methode test BDD

    @Override
    public int getCountVoie() {

        // remplir une List avec les infos de la bdd
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDatasource());

        int vNbrVoie =(int) vJdbcTemplate.queryForObject("SELECT COUNT (*) FROM public.voie",
                Integer.class);

        return vNbrVoie;

    }


    /**
     * pour aller chercher toutes les voie d'un secteur
     *
     * @pSecteur numero du secteur
     */
    @Override
    public List<Voie> getListVoie(int pSecteur) {
        String vSQL = "SELECT * FROM voie WHERE secteur_id = :secteur_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("secteur_id", pSecteur, Types.INTEGER);

        RowMapper<Voie> vRowMapper = new VoieRowMapper();

        List<Voie> vList = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vList;

    }

}
