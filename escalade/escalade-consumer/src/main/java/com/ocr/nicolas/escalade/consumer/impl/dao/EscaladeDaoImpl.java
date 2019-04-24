package com.ocr.nicolas.escalade.consumer.impl.dao;


import com.ocr.nicolas.escalade.consumer.contract.dao.EscaladeDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Named;


@Named
public class EscaladeDaoImpl extends AbstractDAoImpl implements EscaladeDao {

    static final Log logger = LogFactory.getLog(EscaladeDaoImpl.class);

    //todo methode pour aller chercher toutes les voie d'un secteur afin de les affiché sur une page

    @Override
    public int getCountVoie() {

        // remplir une List avec les infos de la bdd
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDatasource());

        int vNbrVoie =(int) vJdbcTemplate.queryForObject("SELECT COUNT (*) FROM public.voie",
                Integer.class);

        return vNbrVoie;

    }


}
