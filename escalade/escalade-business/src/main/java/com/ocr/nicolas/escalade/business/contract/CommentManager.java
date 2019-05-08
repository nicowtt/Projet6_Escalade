package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Commentaire;
import com.ocr.nicolas.escalade.model.exception.CommentException;

import java.util.List;

public interface CommentManager {

    List<Commentaire> getListAllCommentForOneElementId(int pElement_id) throws CommentException;
    Commentaire writeComment(Commentaire pCommentaire);
    void deleteComment(Integer pId);

}
