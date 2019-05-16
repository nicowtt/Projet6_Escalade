package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.TopoPapierDao;

import javax.inject.Named;


import com.ocr.nicolas.escalade.consumer.impl.rowmapper.TopoPapierRowMapper;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.exception.TopoPapierException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
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
                + "  JOIN site on site.id = topopapier.site_id"
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
                + "  JOIN site on site.id = topopapier.site_id"
                + "   WHERE topopapier.id = :pId";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pId", pElementId, Types.INTEGER);

        RowMapper<Topopapier> vRowMapper = new TopoPapierRowMapper();

        List<Topopapier> vListTopoPapier = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListTopoPapier;
    }

    /**
     * For change availability of one topoPapier
     *
     * @param pTopoPapier-> bean topoPapier
     * @param pElementId -> id of topoPapier
     * @throws TopoPapierException
     */
    @Override
    public void changeAvailabilityTopoPapier(Topopapier pTopoPapier,int pElementId) throws TopoPapierException {
        String vSQL
                = "UPDATE topopapier"
                + " SET disponibilite = :disponibilite"
                + "  WHERE id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("disponibilite", pTopoPapier.isDisponibilite(), Types.BOOLEAN);
        vParams.addValue("id", pElementId, Types.INTEGER);

        try {
            vJdbcTemplate.update(vSQL, vParams);
        } catch (DataAccessException vEx) {
            vEx.printStackTrace();
            logger.debug(" problème accés BDD");
            //return pUtilisateur;
            throw new TopoPapierException(" problème accés BDD");
        }
    }
}
