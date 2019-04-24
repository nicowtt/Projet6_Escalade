package com.ocr.nicolas.escalade.model.bean.reservation;

import java.sql.Date;

/**
 * Bean representant une Reservation
 */
public class Reservation {

    // ============ Attributs ============
    private Integer id;
    private Date dateDebut;
    private Date dateFin;
    private Integer topoPapier_id;
    private Integer utilisateur_id;

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
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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


    // ============= methodes =============
}
