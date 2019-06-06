package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.SiteDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.SiteRowMapper;
import com.ocr.nicolas.escalade.model.bean.Site;
import com.ocr.nicolas.escalade.model.exception.SiteException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Named
public class SiteDaoImpl extends AbstractDAoImpl implements SiteDao {

    static final Log logger = LogFactory.getLog(SiteDaoImpl.class);


    /**
     * For list all climbing site
     *
     * @return all site list
     */
    @Override
    public List<Site> getListAllSite() {

        String vSQL = "SELECT * FROM site ";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        RowMapper<Site> vRowMapper = new SiteRowMapper();

        List<Site> vListSite = vJdbcTemplate.query(vSQL, vRowMapper);

        return vListSite;
    }


    /**
     * For get one site
     *
     * @param pSite -> numero de site
     * @return liste de site
     */
    @Override
    public List<Site> getListOneSite(int pSite) {

        String vSQL = "SELECT * FROM site WHERE id = :site_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("site_id", pSite, Types.INTEGER);

        RowMapper<Site> vRowMapper = new SiteRowMapper();

        List<Site> vListSite = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListSite;
    }

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
     * For tag Official site of climbing friend
     *
     * @param pId -> site id
     */
    @Override
    public void addTagForOfficialSite(int pId) {

        String vSQL
                = "UPDATE site"
                + " SET officielsite = true"
                + "  WHERE id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);

        vJdbcTemplate.update(vSQL, vParams);

    }

    /**
     * For write climbing site on bdd
     * @param pSite -> bean Site
     * @throws SiteException
     */
    @Override
    public void writeSiteOnBdd(Site pSite) throws SiteException {
        String vSQL
                = "INSERT INTO site (nomsite, descriptionsite, localisationdepartement, localisationpays, urlphotosite, nombredesecteur, officielsite, element_id)"
                + " VALUES (:nomsite, :descriptionsite, :localisationdepartement, :localisationpays, :urlphotosite, :nombredesecteur, :officielsite, :element_id)";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nomsite", pSite.getNomSite(), Types.VARCHAR);
        vParams.addValue("descriptionsite", pSite.getDescriptionSite(), Types.VARCHAR);
        vParams.addValue("localisationdepartement", pSite.getLocalisationDepartement(), Types.VARCHAR);
        vParams.addValue("localisationpays", pSite.getLocalisationPays(), Types.VARCHAR);
        vParams.addValue("urlphotosite", pSite.getUrlPhotoSite(), Types.VARCHAR);
        vParams.addValue("nombredesecteur", pSite.getNombreDeSecteur(), Types.INTEGER);
        vParams.addValue("officielsite", pSite.isOfficelSite(), Types.BOOLEAN);
        vParams.addValue("element_id", pSite.getElement_id(), Types.INTEGER);

        try {
            vJdbcTemplate.update(vSQL, vParams);
        } catch (DuplicateKeyException vEx) {
            logger.debug("Le site existe déja !");
            //return for user
            throw new SiteException("Le site existe déja !");
        }
    }

    /**
     * For delete tag Official site of climbing friend
     *
     * @param pId -> site id
     */
    @Override
    public void deleteTagForOfficialSite(int pId) {

        String vSQL
                = "UPDATE site"
                + " SET officielsite = false"
                + "  WHERE id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);

        vJdbcTemplate.update(vSQL, vParams);

    }

    /**
     * For have a site List with filter
     *
     * @param pCountry -> filter by country
     * @param pDepartment -> filter by department
     * @param pNbrSectors -> filter by sectors numbers
     * @param pSiteName -> filter by SiteName
     * @param pNbrMax -> for take all sectors
     * @return -> list of site with filter
     */
    @Override
    public List<Site> getListSiteWithFilterMultiChoice(String pCountry, String pDepartment, Integer pNbrSectors, String pSiteName, int pNbrMax){

        String vSQL
                = "SELECT * FROM site"
                + " WHERE ( localisationpays like :Country OR localisationpays IS NULL)"
                + " AND (localisationdepartement like :Department OR localisationdepartement IS NULL) "
                + " AND (nombredesecteur BETWEEN :NbrSectors AND :NbrMax ) "
                + " AND (nomsite like :SiteName OR nomsite IS NULL) ";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("Country", pCountry, Types.VARCHAR);
        vParams.addValue("Department", pDepartment, Types.VARCHAR);
        vParams.addValue("NbrSectors", pNbrSectors, Types.INTEGER);
        vParams.addValue("NbrMax", pNbrMax, Types.INTEGER);
        vParams.addValue("SiteName", pSiteName, Types.VARCHAR);

        RowMapper<Site> vRowMapper = new SiteRowMapper();

        List<Site> vListeSite = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListeSite;
    }


    /**
     * For update one site
     *
     * @Param pSite -> site to update
     */
    @Override
    public void updateSite(Site pSite) throws SiteException {

        String vSQL
                = "UPDATE site SET"
                + " nomsite = :nomsite,"
                + " descriptionsite = :descriptionsite,"
                + " localisationdepartement = :localisationdepartement,"
                + " localisationpays = :localisationpays,"
                + " urlphotosite = :urlphotosite,"
                + " nombredesecteur = :nombredesecteur"
                + "  WHERE id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("nomsite", pSite.getNomSite(), Types.VARCHAR);
        vParams.addValue("descriptionsite", pSite.getDescriptionSite(), Types.VARCHAR);
        vParams.addValue("localisationdepartement", pSite.getLocalisationDepartement(), Types.VARCHAR);
        vParams.addValue("localisationpays", pSite.getLocalisationPays(), Types.VARCHAR);
        vParams.addValue("urlphotosite", pSite.getUrlPhotoSite(), Types.VARCHAR);
        vParams.addValue("nombredesecteur", pSite.getNombreDeSecteur(), Types.INTEGER);
        vParams.addValue("id", pSite.getId(), Types.INTEGER);

        try {
            vJdbcTemplate.update(vSQL,vParams);

        } catch (DataAccessException vEx) {

            //vEx.printStackTrace();
            logger.debug(" problème accés BDD");
            //return pUtilisateur;
            throw new SiteException(" problème accés BDD");
        }
    }

    /**
     * to get a no repeat list of country (all climbing site)
     *
     * @return
     */
    @Override
    public List<String> getListAllSiteCountryNoRepeat() {
        List<String> countryList = new ArrayList();

        String vSQL = "SELECT DISTINCT localisationpays FROM site ";

        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDatasource());

        countryList = vJdbcTemplate.queryForList(vSQL, String.class);

        return countryList;
    }

    /**
     * To get a no repeat list of department (all climbing site)
     * @return
     */
    @Override
    public List<String> getListAllSiteDepartmentNoRepeat() {
        List<String> departmentList = new ArrayList<>();

        String vSQL = "SELECT DISTINCT localisationdepartement FROM site";

        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDatasource());

        departmentList = vJdbcTemplate.queryForList(vSQL, String.class);

        return departmentList;

    }

    /**
     * To get a no repeat list of sector Number (all climbing site)
     * @return
     */
    @Override
    public List<Integer> getListAllSiteSectorNumberNoRepeat() {
        List<Integer> sectorNumberList = new ArrayList<>();

        String vSQL = "SELECT DISTINCT nombredesecteur FROM site";

        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDatasource());

        sectorNumberList = vJdbcTemplate.queryForList(vSQL, Integer.class);

        return sectorNumberList;
    }

    /**
     * To get a no repeat list of site name (all climbing site)
     * @return
     */
    @Override
    public List<String> getListAllSiteNameNoRepeat() {
        List<String> siteNameList = new ArrayList<>();

        String vSQL = "SELECT DISTINCT nomsite FROM site";

        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDatasource());

        siteNameList = vJdbcTemplate.queryForList(vSQL, String.class);

        return siteNameList;
    }

    /**
     * To check if element is a climbing site
     *
     * @param pElement_id -> element id
     * @return -> boolean
     */
    @Override
    public boolean checkIfElementIsSite(int pElement_id) {
        boolean elementIsSite = false;

        String vSQL
                = "SELECT * FROM public.site"
                + " WHERE element_id = :pElement_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pElement_id", pElement_id, Types.INTEGER);

        RowMapper vRowMapper = new SiteRowMapper();

        List<Site> vListOneSite = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        if (vListOneSite.isEmpty()) {
        } else {
            elementIsSite = true;
        }
        return elementIsSite;
    }

}
