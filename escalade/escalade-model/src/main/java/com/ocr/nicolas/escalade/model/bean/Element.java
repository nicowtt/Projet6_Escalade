package com.ocr.nicolas.escalade.model.bean;

/**
 * Bean representant un Element.
 */
public class Element {

    // ==================== Attributs ====================
    private Integer id;
    private Integer utilisateur_id;

    // ==================== Constructeurs =================
    /**
     * Constructeur par defaut
     */
    public Element() {
    }

    /**
     * Constructeur
     *
     * @param pId
     */
    public Element(Integer pId) {id = pId;}

    // ==================== getters et setters =============
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }
    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    // ==================== methodes ==================

}
