package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.CommentDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.CommentRowMapper;
import com.ocr.nicolas.escalade.model.bean.Commentaire;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class CommentDaoImpl extends AbstractDAoImpl implements CommentDao {


    /**
     * For get Comment List for one Element_id
     *
     * @param pElement_id -> Comment element_id
     * @return List of comments
     */
    @Override
    public List<Commentaire> getListAllCommentForOneElementId(int pElement_id) {
        String vSQL
                = "SELECT * FROM commentaire"
                + " JOIN utilisateur ON utilisateur.id = utilisateur_id"
                + "  WHERE element_id = :element_id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("element_id", pElement_id, Types.INTEGER);

        RowMapper<Commentaire> vRowMapperComment = new CommentRowMapper();

        List<Commentaire> vListCommentaire = vJdbcTemplate.query(vSQL, vParams, vRowMapperComment);

        return vListCommentaire;
    }

}
