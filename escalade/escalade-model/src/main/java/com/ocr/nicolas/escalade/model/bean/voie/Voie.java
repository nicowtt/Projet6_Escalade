package com.ocr.nicolas.escalade.model.bean.voie;


/**
 * Bean representant une Voie d'escalade
 */
public class Voie {

    // ==================== Attributs ====================
    private Integer id;
    private Integer numero;
    private String nom;
    private Integer tempDescalade;
    private String description;
    private String longueur;
    private String cotation;
    private Integer hauteur;
    private String precisionEquipement;
    private String ouvertureEtEquipement;
    private String dateOuverture;
    private String statut;
    private Integer element_id;
    private Integer secteur_id;

    // ==================== Constructeurs ====================
    /**
     * Constructeur par defaut.
     */
    public Voie() {
    }

    /**
     * Contructeur
     * @param pId
     */
    public Voie(Integer pId) {id = pId;}

    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Integer getTempDescalade() {
        return tempDescalade;
    }
    public void setTempDescalade(Integer tempDescalade) {
        this.tempDescalade = tempDescalade;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLongueur() {
        return longueur;
    }
    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }
    public String getCotation() {
        return cotation;
    }
    public void setCotation(String cotation) {
        this.cotation = cotation;
    }
    public Integer getHauteur() {
        return hauteur;
    }
    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }
    public String getPrecisionEquipement() {
        return precisionEquipement;
    }
    public void setPrecisionEquipement(String precisionEquipement) {
        this.precisionEquipement = precisionEquipement;
    }
    public String getOuvertureEtEquipement() {
        return ouvertureEtEquipement;
    }
    public void setOuvertureEtEquipement(String ouvertureEtEquipement) {
        this.ouvertureEtEquipement = ouvertureEtEquipement;
    }
    public String getDateOuverture() {
        return dateOuverture;
    }
    public void setDateOuverture(String dateOuverture) {
        this.dateOuverture = dateOuverture;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public Integer getElement_id() {
        return element_id;
    }
    public void setElement_id(Integer element_id) {
        this.element_id = element_id;
    }
    public Integer getSecteur_id() {
        return secteur_id;
    }
    public void setSecteur_id(Integer secteur_id) {
        this.secteur_id = secteur_id;
    }


    // ==================== Méthodes ====================
}
