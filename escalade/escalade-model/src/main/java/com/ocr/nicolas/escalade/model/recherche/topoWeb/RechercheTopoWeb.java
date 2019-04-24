package com.ocr.nicolas.escalade.model.recherche.topoWeb;

/**
 * Classe de critère de recherche de topoWeb
 */
public class RechercheTopoWeb {

    // ========== Attributs ==========
    private String localisationPays;
    private String localisationDepartement;
    private String nomSite;

    // ========== Constructeurs =========
    /**
     * Constructeur par defaut.
     */
    public RechercheTopoWeb() {
    }

    // ========== Getters/Setters ==========
    public String getLocalisationPays() {
        return localisationPays;
    }

    /**
     * Assigne le critère de recherche: localisation pays.
     *
     * @param pLocalisationPays
     */
    public void setLocalisationPays(String pLocalisationPays) {
        localisationPays = pLocalisationPays;
    }

    public String getLocalisationDepartement() {
        return localisationDepartement;
    }

    /**
     * Assigne le critère de recherche: localisation Departement.
     * @param pLocalisationDepartement
     */
    public void setLocalisationDepartement(String pLocalisationDepartement) {
        localisationDepartement = pLocalisationDepartement;
    }

    public String getNomSite() {
        return nomSite;
    }

    /**
     * Assigne le critère de recherche: nom Site
     * @param pNomSite
     */
    public void setNomSite(String pNomSite) {
        nomSite = pNomSite;
    }


}
