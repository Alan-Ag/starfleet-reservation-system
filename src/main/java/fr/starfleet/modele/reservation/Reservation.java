package fr.starfleet.modele.reservation;

import fr.starfleet.modele.mission.Mission;
import fr.starfleet.modele.personne.Personne;
import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
    private final String idReservation;
    private final Personne passager;
    private final Mission mission;
    private Date dateReservation;
    private boolean confirmee;

    public Reservation(String idReservation, Personne passager, Mission mission, Date dateReservation, boolean confirmee) {
        this.idReservation = idReservation;
        this.passager = passager;
        this.mission = mission;
        this.dateReservation = dateReservation;
        this.confirmee = confirmee;
    }

    public void confirmer() {
        this.confirmee = true;
    }

    public void annuler() {
        this.confirmee = false;
    }

    // Getters and Setters
    public String getIdReservation() {
        return idReservation;
    }

    public Personne getPassager() {
        return passager;
    }

    public Mission getMission() {
        return mission;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public boolean isConfirmee() {
        return confirmee;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setConfirmee(boolean confirmee) {
        this.confirmee = confirmee;
    }
    


}    