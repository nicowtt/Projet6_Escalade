package com.ocr.nicolas.escalade.model.bean;

/**
 * Bean representant une Reservation
 */
public class Reservation {

    // ============ Attributs ============
    private Integer id;
    private boolean statusReservation;
    private String emailPretOk;
    private Integer topoPapier_id;
    private Integer utilisateur_id;

    //bean topoPapier
    private Topopapier topopapier;


    // ============ Constructeurs =========
    /**
     * Constructeur par defaut.
     */
    public Reservation() {
    }

    /**
     * Constructeur
     *
     * @param pId
     */
    public Reservation(Integer pId) {id = pId;}

    // ============= Getters et Setters =======
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public boolean isStatusReservation() {
        return statusReservation;
    }
    public void setStatusReservation(boolean statusReservation) {
        this.statusReservation = statusReservation;
    }
    public String getEmailPretOk() {
        return emailPretOk;
    }
    public void setEmailPretOk(String emailPretOk) {
        this.emailPretOk = emailPretOk;
    }
    public Integer getTopoPapier_id() {
        return topoPapier_id;
    }
    public void setTopoPapier_id(Integer topoPapier_id) {
        this.topoPapier_id = topoPapier_id;
    }
    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }
    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }
    public Topopapier getTopopapier() {
        return topopapier;
    }
    public void setTopopapier(Topopapier topopapier) {
        this.topopapier = topopapier;
    }
    // ============= methodes =============
}
