package com.ocr.nicolas.escalade.model.bean;

import java.sql.Date;
import java.util.List;

/**
 * Bean representant un Topopapier
 */
public class Topopapier {

    // =========== Attributs ============
    private Integer id;
    private String nomTopo;
    private String description;
    private String nomCreateur;
    private Date dateCreation;
    private Date dateMaj;
    private boolean disponibilite;
    private Integer site_id;
    private Integer element_id;

    //bean
    private Element element;
    private Site site;

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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public boolean isDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(boolean disponibilite) {
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
    public Element getElement() {
        return element;
    }
    public void setElement(Element element) {
        this.element = element;
    }
    public Site getSite() {
        return site;
    }
    public void setSite(Site site) {
        this.site = site;
    }
    // ============== methodes ============
}

