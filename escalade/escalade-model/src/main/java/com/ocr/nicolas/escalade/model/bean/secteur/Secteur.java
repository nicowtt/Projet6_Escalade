package com.ocr.nicolas.escalade.model.bean.secteur;

/**
 * Bean représentant un Secteur d'escalade
 */
public class Secteur {

    // ==================== Attributs ====================
    private Integer id;
    private String nom;
    private String description;
    private String acces;
    private Integer altitudeBase;
    private String orientation;
    private String typeRoche;
    private Integer nombreDeVoies;
    private String cotation;
    private String urlPhoto;
    private String coordonneGps;
    private Integer element_id;
    private Integer site_id;


    // ==================== Constructeurs ====================
    /**
     * Contructeur par defaut.
     */
    public Secteur() {
    }

    public Secteur(Integer pId) { id = pId;}

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
    public String getAcces() {
        return acces;
    }
    public void setAcces(String acces) {
        this.acces = acces;
    }
    public Integer getAltitudeBase() {
        return altitudeBase;
    }
    public void setAltitudeBase(Integer altitudeBase) {
        this.altitudeBase = altitudeBase;
    }
    public String getOrientation() {
        return orientation;
    }
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    public String getTypeRoche() {
        return typeRoche;
    }
    public void setTypeRoche(String typeRoche) {
        this.typeRoche = typeRoche;
    }
    public Integer getNombreDeVoies() {
        return nombreDeVoies;
    }
    public void setNombreDeVoies(Integer nombreDeVoies) {
        this.nombreDeVoies = nombreDeVoies;
    }
    public String getCotation() {
        return cotation;
    }
    public void setCotation(String cotation) {
        this.cotation = cotation;
    }
    public String getUrlPhoto() {
        return urlPhoto;
    }
    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    public String getCoordonneGps() {
        return coordonneGps;
    }
    public void setCoordonneGps(String coordonneGps) {
        this.coordonneGps = coordonneGps;
    }
    public Integer getElement_id() {
        return element_id;
    }
    public void setElement_id(Integer element_id) {
        this.element_id = element_id;
    }
    public Integer getSite_id() {
        return site_id;
    }
    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }


    // ==================== Méthodes ====================
}
