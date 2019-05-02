package com.ocr.nicolas.escalade.consumer.impl.dao;


import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Named;



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
