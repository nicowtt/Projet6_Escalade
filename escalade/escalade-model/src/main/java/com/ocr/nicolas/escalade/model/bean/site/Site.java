package com.ocr.nicolas.escalade.model.bean.site;

/**
 * Bean représentant un Site d'escalade
 */
public class Site {

    // ==================== Attributs ====================
    private Integer id;
    private String nom;
    private String description;
    private String localisationDepartement;
    private String localisationPays;
    private String nomPhoto;
    private Integer nombreDeSecteur;
    private Integer element_id;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par défaut.
     */
    public Site() {
    }

    /**
     * Constructeur
     *
     * @param pId
     */
    public Site(Integer pId) { id = pId;}

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLocalisationDepartement() {
        return localisationDepartement;
    }
    public void setLocalisationDepartement(String localisationDepartement) {
        this.localisationDepartement = localisationDepartement;
    }
    public String getLocalisationPays() {
        return localisationPays;
    }
    public void setLocalisationPays(String localisationPays) {
        this.localisationPays = localisationPays;
    }
    public String getNomPhoto() {
        return nomPhoto;
    }
    public void setNomPhoto(String nomPhoto) {
        this.nomPhoto = nomPhoto;
    }
    public Integer getNombreDeSecteur() {
        return nombreDeSecteur;
    }
    public void setNombreDeSecteur(Integer nombreDeSecteur) {
        this.nombreDeSecteur = nombreDeSecteur;
    }
    public Integer getElement_id() {
        return element_id;
    }
    public void setElement_id(Integer element_id) {
        this.element_id = element_id;
    }


    // ==================== Méthodes ====================

}
