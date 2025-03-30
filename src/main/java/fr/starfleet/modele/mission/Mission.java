package fr.starfleet.modele.mission;

import fr.starfleet.modele.reservation.Reservation;
import fr.starfleet.modele.vaisseau.Vaisseau;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mission implements Serializable {
    private String code;
    private String description;
    private Date dateDepart;
    private Date dateRetour;
    private String destination;
    private Vaisseau vaisseau;
    private List<Reservation> reservations;
    private int capaciteMaximale;

    public Mission(String code, String description, Date dateDepart, Date dateRetour, String destination, Vaisseau vaisseau, int capaciteMaximale) {
        this.code = code;
        this.description = description;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.destination = destination;
        this.vaisseau = vaisseau;
        this.reservations = new ArrayList<>();
        if (capaciteMaximale < 0) {
            throw new IllegalArgumentException("La capacité maximale ne peut pas être négative.");
        }
        this.capaciteMaximale = capaciteMaximale;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public String getDestination() {
        return destination;
    }

    public Vaisseau getVaisseau() {
        return vaisseau;
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    public int getCapaciteMaximale() {
        return capaciteMaximale;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setVaisseau(Vaisseau vaisseau) {
        this.vaisseau = vaisseau;
    }

    public void setReservations(List<Reservation> reservations) {
        if (reservations == null) {
            throw new IllegalArgumentException("La liste des réservations ne peut pas être null.");
        }
        this.reservations = reservations;
    }

    public void setCapaciteMaximale(int capaciteMaximale) {
        if (capaciteMaximale < 0) {
            throw new IllegalArgumentException("La capacité maximale ne peut pas être négative.");
        }
        this.capaciteMaximale = capaciteMaximale;
    }

    // Méthodes
    public boolean ajouterReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("La réservation ne peut pas être null.");
        }
        if (this.reservations.size() < this.capaciteMaximale) {
            this.reservations.add(reservation);
            return true;
        }
        return false;
    }

    public boolean annulerReservation(String idReservation) {
        return reservations.removeIf(reservation -> reservation.getIdReservation().equals(idReservation));
    }

    public int getNombrePlacesDisponibles() {
        return this.capaciteMaximale - this.reservations.size();
    }

    @Override
    public String toString() {
        return "Mission{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", dateDepart=" + dateDepart +
                ", dateRetour=" + dateRetour +
                ", destination='" + destination + '\'' +
                ", vaisseau=" + vaisseau +
                ", capaciteMaximale=" + capaciteMaximale +
                ", reservations=" + reservations.size() +
                '}';
    }
}