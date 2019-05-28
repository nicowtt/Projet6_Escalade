package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.model.exception.SectorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ocr.nicolas.escalade.consumer.contract.dao.SectorDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.SectorRowMapper;
import com.ocr.nicolas.escalade.model.bean.Secteur;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class SectorDaoImpl extends AbstractDAoImpl implements SectorDao {

    static final Log logger = LogFactory.getLog(SectorDaoImpl.class);


    /**
     * for get sectors List on Site
     *
     * @param pSite -> numero de site
     * @return liste de secteur
     */
    @Override
    public List<Secteur> getListAllSectorForOneSite(int pSite) {

        String vSQL = "SELECT * FROM secteur WHERE site_id = :site_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("site_id", pSite, Types.INTEGER);

        RowMapper<Secteur> vRowMapper = new SectorRowMapper();

        List<Secteur> vListSecteur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListSecteur;
    }

    /**
     * for count sectors on site
     *
     * @param pNom name of site
     * @return sectors number
     */
    @Override
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
     * For write new sector on BDD
     *
     * @param pSector -> bean new sector
     * @throws SectorException
     */
    @Override
    public void writeSectorOnBdd(Secteur pSector) throws SectorException {
        String vSQL
                = "INSERT INTO public.secteur (nomsecteur, descriptionsecteur, acces, altitudebase, orientation, typeroche, nombredevoies, cotation, urlphotosecteur, coordonnegps, element_id, site_id)"
                + " VALUES (:nomsecteur, :descriptionsecteur, :acces, :altitudebase, :orientation, :typeroche, :nombredevoies, :cotation, :urlphotosecteur, :coordonnegps, :element_id, :site_id)";

        NamedParameterJdbcTemplate vJdbcTEmplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nomsecteur", pSector.getNomSecteur(), Types.VARCHAR);
        vParams.addValue("descriptionsecteur", pSector.getDescriptionSecteur(), Types.VARCHAR);
        vParams.addValue("acces", pSector.getAcces(), Types.VARCHAR);
        vParams.addValue("altitudebase", pSector.getAltitudeBase(), Types.INTEGER);
        vParams.addValue("orientation", pSector.getOrientation(), Types.VARCHAR);
        vParams.addValue("typeroche", pSector.getTypeRoche(), Types.VARCHAR);
        vParams.addValue("nombredevoies", pSector.getNombreDeVoies(), Types.INTEGER);
        vParams.addValue("cotation", pSector.getCotation(), Types.VARCHAR);
        vParams.addValue("urlphotosecteur", pSector.getUrlPhotoSecteur(), Types.VARCHAR);
        vParams.addValue("coordonnegps", pSector.getCoordonneGps(), Types.VARCHAR);
        vParams.addValue("element_id", pSector.getElement_id(), Types.INTEGER);
        vParams.addValue("site_id", pSector.getSite_id(), Types.INTEGER);

        try {
            vJdbcTEmplate.update(vSQL, vParams);
        } catch (DuplicateKeyException vEx) {
            logger.debug("Le secteur existe déjà !");
            //return for user
            throw new SectorException("Le secteur existe déjà !");
        }

    }

    /**
     * For get a list with one sector
     * @param pId
     * @return
     */
    @Override
    public List<Secteur> getOneSector(int pId) {
        String vSQL = "SELECT * FROM secteur WHERE id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);

        RowMapper<Secteur> vRowMapper = new SectorRowMapper();

        List<Secteur> vListSecteur = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListSecteur;
    }
}
