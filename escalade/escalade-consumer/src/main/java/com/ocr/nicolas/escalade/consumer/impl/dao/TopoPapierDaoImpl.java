package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.TopoPapierDao;

import javax.inject.Named;


import com.ocr.nicolas.escalade.consumer.impl.rowmapper.TopoPapierRowMapper;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import java.sql.Types;
import java.util.List;



@Named
public class TopoPapierDaoImpl extends AbstractDAoImpl implements TopoPapierDao {

    static final Log logger = LogFactory.getLog(TopoPapierDaoImpl.class);

    /**
     * For get list of TopoPapier
     * @param pIdUser -> user id
     * @return list of topopapier
     */
    @Override
    public List<Topopapier> getListTopoPapier(int pIdUser){
        String vSQL
                = "SELECT * FROM public.topopapier"
                + " JOIN element ON element.id = topopapier.element_id"
                + "  WHERE utilisateur_id = :IdUser";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("IdUser", pIdUser, Types.INTEGER);

        RowMapper<Topopapier> vRowMapperTopoPapier = new TopoPapierRowMapper();

        List<Topopapier> vListTopoPapier = vJdbcTemplate.query(vSQL, vParams, vRowMapperTopoPapier);

        return vListTopoPapier;
    }


    /**
     * For get one topo papier from BDD
     * @param pElementId -> id of topoPapier
     * @return -> list with one bean topoPapier
     */
    @Override
    public List<Topopapier> getOneTopoPapier(int pElementId) {
        String vSQL
                = "SELECT * FROM public.topopapier"
                + " JOIN element ON element.id = topopapier.element_id"
                + "  WHERE topopapier.id = :pId";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pId", pElementId, Types.INTEGER);

        RowMapper<Topopapier> vRowMapper = new TopoPapierRowMapper();

        List<Topopapier> vListTopoPapier = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListTopoPapier;
    }
}
