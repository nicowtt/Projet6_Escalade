package com.ocr.nicolas.escalade.model.bean;

import java.sql.Date;

/**
 * Bean representant un Topopapier
 */
public class Topopapier {

    // =========== Attributs ============
    private Integer id;
    private String nomTopo;
    private String nomCreateur;
    private Date dateCreation;
    private Date dateMaj;
    private String disponibilite;
    private Integer site_id;
    private Integer element_id;

    // =========== Constructeurs =========
    /**
     * Constructeur par defaut.
     */
    public Topopapier() {
    }

    /**
     * Constructeur
     *
     * @param pId
     */
    public Topopapier(Integer pId) {id = pId;}

    // ============= Getters et Setters =====
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNomTopo() {
        return nomTopo;
    }
    public void setNomTopo(String nomTopo) {
        this.nomTopo = nomTopo;
    }
    public String getNomCreateur() {
        return nomCreateur;
    }
    public void setNomCreateur(String nomCreateur) {
        this.nomCreateur = nomCreateur;
    }
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Date getDateMaj() {
        return dateMaj;
    }
    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }
    public String getDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }
    public Integer getSite_id() {
        return site_id;
    }
    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }
    public Integer getElement_id() {
        return element_id;
    }
    public void setElement_id(Integer element_id) {
        this.element_id = element_id;
    }

    // ============== methodes ============
}

