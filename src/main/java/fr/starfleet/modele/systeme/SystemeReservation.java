package fr.starfleet.modele.systeme;

import fr.starfleet.modele.mission.Mission;
import fr.starfleet.modele.personne.Personne;
import fr.starfleet.modele.reservation.Reservation;
import fr.starfleet.modele.vaisseau.Vaisseau;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SystemeReservation implements Serializable {
    private final List<Vaisseau> vaisseaux;
    private final List<Personne> personnes;
    private final List<Mission> missions;
    private final List<Reservation> reservations;

    public SystemeReservation() {
        this.vaisseaux = new ArrayList<>();
        this.personnes = new ArrayList<>();
        this.missions = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void ajouterVaisseau(Vaisseau vaisseau) { vaisseaux.add(vaisseau); }
    public void ajouterPersonne(Personne personne) { personnes.add(personne); }
    public void creerMission(Mission mission) { missions.add(mission); }

    public Reservation effectuerReservation(String idPersonne, String codeMission) {
        Personne personne = personnes.stream().filter(p -> p.getIdentifiant().equals(idPersonne)).findFirst().orElse(null);
        Mission mission = missions.stream().filter(m -> m.getCode().equals(codeMission)).findFirst().orElse(null);
        if (personne != null && mission != null && mission.getNombrePlacesDisponibles() > 0) {
            Reservation reservation = new Reservation("RES" + (reservations.size() + 1), personne, mission, null, false);
            if (mission.ajouterReservation(reservation)) {
                reservations.add(reservation);
                return reservation;
            }
        }
        return null;
    }

    public List<Mission> rechercherMissions(String destination) {
        return missions.stream()
                .filter(m -> m.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }

   
    public void sauvegarderDonnees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/sauvegarde.dat"))) {
            oos.writeObject(this);
            System.out.println("Données sauvegardées avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }

    public void chargerDonnees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/sauvegarde.dat"))) {
            SystemeReservation systemeReservation = (SystemeReservation) ois.readObject();
            this.vaisseaux.clear();
            this.vaisseaux.addAll(systemeReservation.getVaisseaux());
            this.personnes.clear();
            this.personnes.addAll(systemeReservation.getPersonnes());
            this.missions.clear();
            this.missions.addAll(systemeReservation.getMissions());
            this.reservations.clear();
            this.reservations.addAll(systemeReservation.getReservations());
            System.out.println("Données chargées avec succès !");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
        }
    }

    public List<Vaisseau> getVaisseaux() { return vaisseaux; }
     public List<Personne> getPersonnes() { return personnes; }
    public List<Mission> getMissions() { return missions; }
    public List<Reservation> getReservations() { return reservations; }

    public Mission trouverMissionParId(String idMission) {
        for (Mission mission : getMissions()) {
            if (mission.getCode().equals(idMission)) {
                return mission;
            }
        }
        return null;
    }

   
public Personne trouverPersonneParId(String idPersonne) {
    for (Personne personne : getPersonnes()) {
        if (personne.getIdentifiant().equals(idPersonne)) {
            return personne;
        }
    }
    return null;
}

    public boolean creerReservation(Mission mission, Personne personne) {
        if (mission.getNombrePlacesDisponibles() > 0) {
            Reservation reservation = new Reservation(
                "Reservation-" + System.currentTimeMillis(), // Example unique ID
                personne,
                mission,
                new java.sql.Date(System.currentTimeMillis()), // Current date as reservation date
                true // Example boolean value
            );
            mission.ajouterReservation(reservation);
            return true;
        }
        return false;
    }

    
public boolean annulerReservation(String idReservation) {
    for (Mission mission : getMissions()) {
        if (mission.annulerReservation(idReservation)) {
            return true;
        }
    }
    return false;
}


}