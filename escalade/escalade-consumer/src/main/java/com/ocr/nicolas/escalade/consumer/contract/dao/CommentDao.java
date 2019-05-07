package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Commentaire;

import java.util.List;

public interface CommentDao {

    List<Commentaire> getListAllCommentForOneElementId(int pElement_id);
}
