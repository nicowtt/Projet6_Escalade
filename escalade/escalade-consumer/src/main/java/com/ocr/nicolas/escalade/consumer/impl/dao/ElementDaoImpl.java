package com.ocr.nicolas.escalade.consumer.impl.dao;


import com.ocr.nicolas.escalade.consumer.impl.rowmapper.ElementRowMapper;
import com.ocr.nicolas.escalade.model.bean.Element;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ocr.nicolas.escalade.consumer.contract.dao.ElementDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.inject.Named;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


@Named
public class ElementDaoImpl extends AbstractDAoImpl implements ElementDao {

    static final Log logger = LogFactory.getLog(ElementDaoImpl.class);


    /**
     * For write new element
     *
     * @param pUserId-> user id
     */
    @Override
    public void writeNewElement(Integer pUserId) {

        String vSQL
                = "INSERT INTO public.element (utilisateur_id) VALUES (:pId)";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("pId", pUserId, Types.INTEGER);

        vJdbcTemplate.update(vSQL, vParams);
    }


    /**
     * For take last element created
     *
     * @return
     */
    @Override
    public Element getLastElement() {
        String vSQL
                = "SELECT * FROM public.element"
                + " WHERE id = (SELECT MAX(id) FROM element)";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();

        RowMapper vRowMapper = new ElementRowMapper();

        Element vElement = new Element();

        vElement = (Element) vJdbcTemplate.query(vSQL, vParams, vRowMapper).get(0);


       return vElement;

    }

    /**
     * For delete one element
     * @param pId -> element id to delete
     */
    @Override
    public void deleteOneElement(Integer pId) {
        String vSQL
                = " DELETE FROM public.element"
                + "  WHERE id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);

        vJdbcTemplate.update(vSQL, vParams);

    }

    /**
     * For get one Element
     *
     * @param pId
     * @return
     */
    @Override
    public Element getOneElement(Integer pId) {
        String vSQL
                = "SELECT * FROM public.element"
                + " WHERE id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);

        RowMapper vRowMapper = new ElementRowMapper();

        Element vElement = new Element();

        vElement = (Element) vJdbcTemplate.query(vSQL, vParams, vRowMapper).get(0);


        return vElement;
    }

    /**
     * for have a list of element by user
     *
     * @param pId -> id of user
     * @return
     */
    @Override
    public List<Element> getListElementByUser (Integer pId) {
        List vListElement = new ArrayList<>();
        String vSQL
                = "SELECT * FROM public.element"
                + " WHERE utilisateur_id = :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);

        RowMapper vRowMapper = new ElementRowMapper();

        vListElement = vJdbcTemplate.query(vSQL, vParams, vRowMapper);

        return vListElement;
    }
}
