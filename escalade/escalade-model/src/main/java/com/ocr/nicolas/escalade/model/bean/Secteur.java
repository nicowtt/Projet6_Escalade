package com.ocr.nicolas.escalade.model.bean;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Bean représentant un Secteur d'escalade
 */
public class Secteur {

    // ==================== Attributs ====================
    private Integer id;

    @Size(max=100)
    @NotBlank
    private String nomSecteur;

    @Size(max=500)
    private String descriptionSecteur;

    @Size(max=200)
    private String acces;

    @DecimalMax(value="100000")
    @DecimalMin(value="1")
    private Integer altitudeBase;

    @Size(max=100)
    private String orientation;

    @Size(max=100)
    private String typeRoche;

    @DecimalMax(value="100000")
    @DecimalMin(value="1")
    @NotNull
    private Integer nombreDeVoies;

    @Size(max=50)
    @NotBlank
    private String cotation;

    @Size(max=100)
    private String urlPhotoSecteur;

    @Size(max=100)
    private String coordonneGps;

    private Integer element_id;

    private Integer site_id;
    //ajout bean voie
    private List<Voie> ways;

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
    public String getNomSecteur() {
        return nomSecteur;
    }
    public void setNomSecteur(String nomSecteur) {
        this.nomSecteur = nomSecteur;
    }
    public String getDescriptionSecteur() {
        return descriptionSecteur;
    }
    public void setDescriptionSecteur(String descriptionSecteur) {
        this.descriptionSecteur = descriptionSecteur;
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
    public String getUrlPhotoSecteur() {
        return urlPhotoSecteur;
    }
    public void setUrlPhotoSecteur(String urlPhotoSecteur) {
        this.urlPhotoSecteur = urlPhotoSecteur;
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
    public List<Voie> getWays() {
        return ways;
    }
    public void setWays(List<Voie> ways) {
        this.ways = ways;
    }
// ==================== Méthodes ====================
}
