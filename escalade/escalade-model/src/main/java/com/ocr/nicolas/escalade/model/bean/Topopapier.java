package com.ocr.nicolas.escalade.model.bean;


import javax.validation.constraints.NotBlank;

/**
 * Bean representant un Topopapier
 */
public class Topopapier {

    // =========== Attributs ============
    private Integer id;
//    @NotBlank
    private String nomTopo;
//    @NotBlank
    private String description;
//    @NotBlank
    private String nomCreateur;
    private String dateCreation;
    private String dateMaj;
    private boolean disponibilite;
    private boolean demandeReservation;
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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(String dateMaj) {
        this.dateMaj = dateMaj;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
    public boolean isDemandeReservation() {
        return demandeReservation;
    }
    public void setDemandeReservation(boolean demandeReservation) {
        this.demandeReservation = demandeReservation;
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

