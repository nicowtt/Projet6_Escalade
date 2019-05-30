package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.model.exception.CommentException;
import com.ocr.nicolas.escalade.model.exception.WayException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ocr.nicolas.escalade.consumer.contract.dao.WayDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.WayRowMapper;
import com.ocr.nicolas.escalade.model.bean.Voie;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class WayDaoImpl extends AbstractDAoImpl implements WayDao {

    static final Log logger = LogFactory.getLog(WayDaoImpl.class);


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
                + " JOIN secteur ON secteur.id = voie.secteur_id"
                + "  WHERE secteur_id IN ("
                + "   SELECT secteur.id FROM secteur"
                + "    WHERE site_id = :site_id)";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("site_id", pSite, Types.INTEGER);

        RowMapper<Voie> vRowMapper = new WayRowMapper();

        List<Voie> vListSecteur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListSecteur;

    }

    /**
     * for get ways List on sectors
     *
     * @param pSecteur -> id of sector
     * @return Ways List
     */
    @Override
    public List<Voie> getListVoie(int pSecteur) {

        String vSQL
                = "SELECT * FROM voie "
                + "JOIN secteur ON secteur.id = voie.secteur_id"
                + " WHERE secteur_id = :secteur_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("secteur_id", pSecteur, Types.INTEGER);

        RowMapper<Voie> vRowMapper = new WayRowMapper();

        List<Voie> vListVoie = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListVoie;

    }


    /**
     * For write new way on bdd
     * @param pWay -> bean way in
     * @throws WayException
     */
    @Override
    public void writeWayOnBdd(Voie pWay) throws WayException {
        String vSQL
                = "INSERT INTO public.voie (numero, nomvoie, tempdescalade, descriptionvoie, longueur, cotation, hauteur, precisionequipement, ouvertureetequipement, dateouverture, statut, element_id, secteur_id)"
                + " VALUES (:numero, :nomvoie, :tempdescalade, :descriptionvoie, :longueur, :cotation, :hauteur, :precisionequipement, :ouvertureetequipement, :dateouverture, :statut, :element_id, :secteur_id)";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("numero", pWay.getNumero(), Types.INTEGER);
        vParams.addValue("nomvoie", pWay.getNomVoie(),Types.VARCHAR);
        vParams.addValue("tempdescalade", pWay.getTempDescalade(), Types.INTEGER);
        vParams.addValue("descriptionvoie", pWay.getDescriptionVoie(), Types.VARCHAR);
        vParams.addValue("longueur", pWay.getLongueur(), Types.VARCHAR);
        vParams.addValue("cotation", pWay.getCotation(), Types.VARCHAR);
        vParams.addValue("hauteur", pWay.getHauteur(), Types.INTEGER);
        vParams.addValue("precisionequipement", pWay.getPrecisionEquipement(), Types.VARCHAR);
        vParams.addValue("ouvertureetequipement", pWay.getOuvertureEtEquipement(), Types.VARCHAR);
        vParams.addValue("dateouverture", pWay.getDateOuverture(), Types.VARCHAR);
        vParams.addValue("statut", pWay.getStatut(), Types.VARCHAR);
        vParams.addValue("element_id", pWay.getElement_id(), Types.INTEGER);
        vParams.addValue("secteur_id", pWay.getSecteur_id(), Types.INTEGER);

        try {
            vJdbcTemplate.update(vSQL, vParams);
        } catch (DuplicateKeyException vEx) {
            logger.debug("La voie existe déjà !");
            //return for user
            throw new WayException("La voie existe déjà !");
        }

    }

    /**
     * For get One Way
     * @param pId -> id of way
     * @return
     */
    @Override
    public List<Voie> getListOneWay(int pId) {
        String vSQL
                = "SELECT * FROM voie"
                + " JOIN secteur ON secteur.id = voie.secteur_id"
                + "  WHERE voie.id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);

        RowMapper<Voie> vRowMapper = new WayRowMapper();

        List<Voie> vListSecteur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListSecteur;

    }

    /**
     * For update one way
     * @param pWay -> bean way to update
     * @throws CommentException
     */
    @Override
    public void updateWay(Voie pWay) throws CommentException {
        String vSQL
                = "UPDATE voie SET"
                + " numero = :numero,"
                + " nomvoie = :nomvoie,"
                + " tempdescalade = :tempdescalade,"
                + " descriptionvoie = :descriptionvoie,"
                + " longueur = :longueur,"
                + " cotation = :cotation,"
                + " hauteur = :hauteur,"
                + " precisionequipement = :precisionequipement,"
                + " ouvertureetequipement = :ouvertureetequipement,"
                + " dateouverture = :dateouverture,"
                + " statut = :statut"
                + "  WHERE id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("numero", pWay.getNumero(), Types.INTEGER);
        vParams.addValue("nomvoie", pWay.getNomVoie(),Types.VARCHAR);
        vParams.addValue("tempdescalade", pWay.getTempDescalade(), Types.INTEGER);
        vParams.addValue("descriptionvoie", pWay.getDescriptionVoie(), Types.VARCHAR);
        vParams.addValue("longueur", pWay.getLongueur(), Types.VARCHAR);
        vParams.addValue("cotation", pWay.getCotation(), Types.VARCHAR);
        vParams.addValue("hauteur", pWay.getHauteur(), Types.INTEGER);
        vParams.addValue("precisionequipement", pWay.getPrecisionEquipement(), Types.VARCHAR);
        vParams.addValue("ouvertureetequipement", pWay.getOuvertureEtEquipement(), Types.VARCHAR);
        vParams.addValue("dateouverture", pWay.getDateOuverture(), Types.VARCHAR);
        vParams.addValue("statut", pWay.getStatut(), Types.VARCHAR);
        vParams.addValue("id", pWay.getId(), Types.INTEGER);

        try {
            vJdbcTemplate.update(vSQL,vParams);

        } catch (DataAccessException vEx) {

            //vEx.printStackTrace();
            logger.debug(" problème accés BDD");
            //return pUtilisateur;
            throw new CommentException(" problème accés BDD");
        }

    }

}
