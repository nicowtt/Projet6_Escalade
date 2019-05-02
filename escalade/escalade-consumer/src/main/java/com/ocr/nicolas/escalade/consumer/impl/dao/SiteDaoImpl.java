package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.SiteDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.SiteRowMapper;
import com.ocr.nicolas.escalade.model.bean.site.Site;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.util.List;

@Named
public class SiteDaoImpl extends AbstractDAoImpl implements SiteDao {


    /**
     * For get All site on a list
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
}
