package com.ocr.nicolas.escalade.model.bean;

import javax.validation.constraints.*;

/**
 * Bean représentant un Site d'escalade
 */
public class Site {

    // ==================== Attributs ====================
    private Integer id;
    @NotBlank
    @Size(max=100)
    private String nomSite;
    @Size(max=500)
    private String descriptionSite;
    @NotBlank
    @Size(max=50)
    private String localisationDepartement;
    @NotBlank
    @Size(max=50)
    private String localisationPays;
    @Size(max=100)
    private String urlPhotoSite;
    @DecimalMin(value= "1")
    @NotNull
    private Integer nombreDeSecteur;
    private boolean officelSite;
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
    public String getNomSite() {
        return nomSite;
    }
    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }
    public String getDescriptionSite() {
        return descriptionSite;
    }
    public void setDescriptionSite(String descriptionSite) {
        this.descriptionSite = descriptionSite;
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
    public String getUrlPhotoSite() {
        return urlPhotoSite;
    }
    public void setUrlPhotoSite(String urlPhotoSite) {
        this.urlPhotoSite = urlPhotoSite;
    }
    public Integer getNombreDeSecteur() {
        return nombreDeSecteur;
    }
    public void setNombreDeSecteur(Integer nombreDeSecteur) {
        this.nombreDeSecteur = nombreDeSecteur;
    }
    public boolean isOfficelSite() {
        return officelSite;
    }
    public void setOfficelSite(boolean officelSite) {
        this.officelSite = officelSite;
    }
    public Integer getElement_id() {
        return element_id;
    }
    public void setElement_id(Integer element_id) {
        this.element_id = element_id;
    }


    // ==================== Méthodes ====================

}
