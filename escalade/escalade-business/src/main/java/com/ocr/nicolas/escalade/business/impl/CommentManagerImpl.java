package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.CommentManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.CommentDao;
import com.ocr.nicolas.escalade.model.bean.commentaire.Commentaire;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class CommentManagerImpl implements CommentManager {

    @Inject
    private CommentDao commentDao;

    @Override
    public List<Commentaire> getListAllCommentForOneElementId(int pElement_id) {
        List<Commentaire> vCommentaire = new ArrayList<>();
        vCommentaire = commentDao.getListAllCommentForOneElementId(pElement_id);

        return vCommentaire;
    }
}
