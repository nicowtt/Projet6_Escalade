package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Commentaire;
import com.ocr.nicolas.escalade.model.exception.CommentException;

import java.util.List;

public interface CommentDao {

    List<Commentaire> getListAllCommentForOneElementId(int pElement_id);
    Commentaire writeComment(Commentaire pCommentaire) throws CommentException;
    void deleteComment(Integer pId) throws CommentException;
    List<Commentaire> displayOneComment(int pId);
    Commentaire updateComment(Commentaire pCommentaire) throws CommentException;
    void deleteCommentUser(Integer pUserId);
}
