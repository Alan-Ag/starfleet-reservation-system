package fr.starfleet.modele.personne;

import java.io.Serializable;

public abstract class Personne implements Serializable {
    private String nom;
    private String prenom;
    private String identifiant;

    public Personne(String nom, String prenom, String identifiant) {
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }   

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    // Méthodes abstraite
    public abstract String getDescription();

    @Override
    public String toString() {
        return "Personne [identifiant=" + identifiant + ", nom=" + nom + ", prenom=" + prenom + "]";
    }

}
