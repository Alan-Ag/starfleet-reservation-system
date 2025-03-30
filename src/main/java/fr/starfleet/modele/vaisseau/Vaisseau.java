package fr.starfleet.modele.vaisseau;

import fr.starfleet.modele.mission.Mission;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vaisseau implements Serializable {
    private String nom;
    private String immatriculation;
    private int capaciteMaximale;
    private List<Mission> missions;

    public Vaisseau(String nom, String immatriculation, int capaciteMaximale) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom du vaisseau ne peut pas être null ou vide.");
        }
        if (immatriculation == null || immatriculation.isEmpty()) {
            throw new IllegalArgumentException("L'immatriculation ne peut pas être null ou vide.");
        }
        if (capaciteMaximale < 0) {
            throw new IllegalArgumentException("La capacité maximale ne peut pas être négative.");
        }
        this.nom = nom;
        this.immatriculation = immatriculation;
        this.capaciteMaximale = capaciteMaximale;
        this.missions = new ArrayList<>();
    }

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom du vaisseau ne peut pas être null ou vide.");
        }
        this.nom = nom;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        if (immatriculation == null || immatriculation.isEmpty()) {
            throw new IllegalArgumentException("L'immatriculation ne peut pas être null ou vide.");
        }
        this.immatriculation = immatriculation;
    }

    public int getCapaciteMaximale() {
        return capaciteMaximale;
    }

    public void setCapaciteMaximale(int capaciteMaximale) {
        if (capaciteMaximale < 0) {
            throw new IllegalArgumentException("La capacité maximale ne peut pas être négative.");
        }
        this.capaciteMaximale = capaciteMaximale;
    }

    public List<Mission> getMissions() {
        return new ArrayList<>(missions);
    }

    public void setMissions(List<Mission> missions) {
        if (missions == null) {
            throw new IllegalArgumentException("La liste des missions ne peut pas être null.");
        }
        this.missions = new ArrayList<>(missions);
    }

    public void ajouterMission(Mission mission) {
        if (mission == null) {
            throw new IllegalArgumentException("La mission ne peut pas être null.");
        }
        this.missions.add(mission);
    }
}