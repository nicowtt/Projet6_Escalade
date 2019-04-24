package com.ocr.nicolas.escalade.model.bean.commentaire;

import java.sql.Timestamp;

/**
 * Bean représentant un Commentaire.
 */
public class Commentaire {

    // ==================== Attributs ====================
    private Integer id;
    private Timestamp dateCommentaire;
    private Integer element_id;
    private String commentaire;
    private Integer utilisateur_id;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par defaut.
     */
    public Commentaire() {
    }

    /**
     * Constructeur
     *
     * @param pId
     */
    public Commentaire(Integer pId) {id = pId;}

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Timestamp getDateCommentaire() {
        return dateCommentaire;
    }
    public void setDateCommentaire(Timestamp dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }
    public Integer getElement_id() {
        return element_id;
    }
    public void setElement_id(Integer element_id) {
        this.element_id = element_id;
    }
    public String getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }
    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }


    // ==================== Méthodes ====================

}
