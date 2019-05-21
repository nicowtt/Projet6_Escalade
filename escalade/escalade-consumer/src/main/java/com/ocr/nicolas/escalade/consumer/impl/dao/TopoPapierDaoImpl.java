package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.TopoPapierDao;

import javax.inject.Named;



import com.ocr.nicolas.escalade.consumer.impl.rowmapper.TopoPapierRowMapper;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.exception.TopoPapierException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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

    /**
     * For write new topo papier on BDD
     * @param pTopopapier -> bean in
     * @throws TopoPapierException
     */
    @Override
    public void writeNewTopoPapier(Topopapier pTopopapier) throws TopoPapierException {
        String vSQL
                = "INSERT INTO public.topopapier (nomtopo, description, nomcreateur, datecreation, datemaj, disponibilite, demandereservation, site_id, element_id)"
                + " VALUES (:nomtopo, :description, :nomcreateur, :datecreation, :datemaj, :disponibilite, :demandereservation, :site_id, :element_id)";

        NamedParameterJdbcTemplate vJvdcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nomtopo", pTopopapier.getNomTopo(), Types.VARCHAR);
        vParams.addValue("description", pTopopapier.getDescription(), Types.VARCHAR);
        vParams.addValue("nomcreateur", pTopopapier.getNomCreateur(), Types.VARCHAR);
        vParams.addValue("datecreation", pTopopapier.getDateCreation(), Types.VARCHAR);
        vParams.addValue("datemaj", pTopopapier.getDateMaj(), Types.VARCHAR);
        vParams.addValue("disponibilite", pTopopapier.isDisponibilite(), Types.BOOLEAN);
        vParams.addValue("demandereservation", pTopopapier.isDemandeReservation(), Types.BOOLEAN);
        vParams.addValue("site_id", pTopopapier.getSite_id(), Types.INTEGER);
        vParams.addValue("element_id", pTopopapier.getElement_id(), Types.INTEGER);

        try {
            vJvdcTemplate.update(vSQL,vParams);
        } catch (DuplicateKeyException vEx) {
            logger.debug("Le topo papier exite déjà !");
            //return for user
            throw new TopoPapierException("Le topo papier existe déja !");
        }
    }

    /**
     * For get all paper topo available
     * @return -> list of topo
     */
    @Override
    public List<Topopapier> getListAllTopoPapierAvailable() {
        String vSQL
                = "SELECT * FROM public.topopapier"
                + " JOIN element ON element.id = topopapier.element_id"
                + "  JOIN site on site.id = topopapier.site_id"
                + "    WHERE disponibilite = true";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        RowMapper<Topopapier> vRowMapper = new TopoPapierRowMapper();

        List<Topopapier> vListTopoPapier = vJdbcTemplate.query(vSQL, vRowMapper);

        return vListTopoPapier;
    }

    /**
     * For change booking request boolean
     *
     * @param pTopoPapier -> bean paper topo
     * @param pElementId -> id of topoPapier
     */
    @Override
    public void changeBookingRequest(Topopapier pTopoPapier, int pElementId) {
        String vSQL
                = "UPDATE topopapier"
                + " SET demandereservation = :demandereservation"
                + "  WHERE id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("demandereservation", pTopoPapier.isDemandeReservation(), Types.BOOLEAN);
        vParams.addValue("id", pElementId, Types.INTEGER);

        vJdbcTemplate.update(vSQL, vParams);

    }


    /**
     * For get one topo papier from BDD
     * @param pId -> id of topoPapier
     * @return -> list with one bean topoPapier
     */
    @Override
    public Topopapier getOnlyOneTopopaper(int pId) {
        Topopapier newTopoPaper = new Topopapier();

        String vSQL
                = "SELECT * FROM topopapier"
                + " JOIN element ON element.id = topopapier.element_id"
                + "  JOIN site on site.id = topopapier.site_id"
                + "   WHERE topopapier.id = :id";

        NamedParameterJdbcTemplate vJdbcTempalte = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER );

        RowMapper<Topopapier> vRowMapper = new TopoPapierRowMapper();

        List<Topopapier> vListTopoPapier = vJdbcTempalte.query(vSQL, vParams, vRowMapper);

        newTopoPaper = vListTopoPapier.get(0);

        return newTopoPaper;
    }

}
