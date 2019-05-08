package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.contract.dao.CommentDao;
import com.ocr.nicolas.escalade.consumer.impl.rowmapper.CommentRowMapper;
import com.ocr.nicolas.escalade.model.bean.Commentaire;
import com.ocr.nicolas.escalade.model.exception.CommentException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class CommentDaoImpl extends AbstractDAoImpl implements CommentDao {

    static final Log logger = LogFactory.getLog(CommentDaoImpl.class);


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

    /**
     *  For write a comment
     *
     * @param pCommentaire -> bean comment
     * @return -> bean comment ?? to check if necessary ??
     * @throws CommentException
     */
    @Override
    public Commentaire writeComment(Commentaire pCommentaire) throws CommentException {
        String vSQL = "INSERT INTO commentaire (datecommentaire, element_id, commentaire, utilisateur_id )" +
                "VALUES (:date, :element_id, :commentaire, :utilisateur_id)";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("date", pCommentaire.getDateCommentaire(), Types.TIMESTAMP);
        vParams.addValue("element_id", pCommentaire.getElement_id(), Types.INTEGER);
        vParams.addValue("commentaire", pCommentaire.getCommentaire(), Types.VARCHAR);
        vParams.addValue("utilisateur_id", pCommentaire.getUtilisateur_id(), Types.INTEGER);

        try {
            vJdbcTemplate.update(vSQL,vParams);
//            vJdbcTemplate.update(vSQL, vParams, keyHolder, new String[] { "commentaire" });
//            pCommentaire.setId(keyHolder.getKey().intValue());
        } catch (DuplicateKeyException vEx) {
            //vEx.printStackTrace();
            logger.debug("Le commentaire existe déjà ! pseudo=" + pCommentaire.getCommentaire());
            //return pUtilisateur;
            throw new CommentException("Le commentaire existe déjà ! pseudo=" + pCommentaire.getCommentaire());
        }
        return pCommentaire;
    }


    /**
     * For delete one comment
     *
     * @param pId -> id of comment to delete
     */
    @Override
    public void deleteComment(Integer pId) {
        String vSQL = "DELETE FROM commentaire WHERE id= :id";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", pId, Types.INTEGER);

        vJdbcTemplate.update(vSQL, vParams);
    }

}
