package fr.starfleet.ui;

import fr.starfleet.modele.mission.Mission;
import fr.starfleet.modele.personne.Civil;
import fr.starfleet.modele.personne.Officier;
import fr.starfleet.modele.personne.Personne;
import fr.starfleet.modele.reservation.Reservation;
import fr.starfleet.modele.systeme.SystemeReservation;
import fr.starfleet.modele.vaisseau.Vaisseau;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfaceConsole {
    private SystemeReservation systeme;
    private final Scanner scanner;
    private final List<Personne> personnes;
    private final List<Mission> missions;

    public InterfaceConsole(SystemeReservation systeme) {
        this.systeme = systeme;
        this.scanner = new Scanner(System.in);
        this.personnes = new ArrayList<>();
        this.missions = new ArrayList<>();
    }

    public void demarrer() {
        System.out.println("Bienvenue dans le système de réservation de Starfleet");
        while (true) {
           afficherMenu();
           String choix = scanner.nextLine();
           switch (choix) {
               case "1" -> gererVaisseaux();
               case "2" -> gererPersonnes();
               case "3" -> gererMissions();
               case "4" -> gererReservations();
               case "5" -> sauvegarderDonnees();
               case "6" -> chargerDonnees();
               case "7" -> {
                   System.out.println("Au revoir !");
                   return;
               }
               default -> System.out.println("Choix invalide");
           }
        }
    }
    
    // Méthodes pour les differentes fonctionnalités
    private void afficherMenu() {
        System.out.println("1. Gérer les vaisseaux");
        System.out.println("2. Gérer les personnes");
        System.out.println("3. Gérer les missions");
        System.out.println("4. Gérer les réservations");
        System.out.println("5. Sauvegarder les données");
        System.out.println("6. Charger les données");
        System.out.println("7. Quitter");
        System.out.print("Votre choix : ");
    }

    private void gererVaisseaux() {
        while (true) {
            System.out.println("\n--- Gestion des vaisseaux ---");
            System.out.println("1. Ajouter un vaisseau");
            System.out.println("2. Lister les vaisseaux");
            System.out.println("3. Retour au menu principal");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> ajouterVaisseau();
                case "2" -> listerVaisseaux();
                case "3" -> {
                    return; // Retour au menu principal
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void listerVaisseaux() {
        System.out.println("\n--- Liste des vaisseaux ---");
        List<Vaisseau> vaisseaux = systeme.getVaisseaux();
        if (vaisseaux == null || vaisseaux.isEmpty()) {
            System.out.println("Aucun vaisseau disponible.");
        } else {
            vaisseaux.forEach(vaisseau -> System.out.println("- " + vaisseau.getNom() + " (Capacité : " + vaisseau.getCapaciteMaximale() + ")"));
        }
    }

    private void ajouterVaisseau() {
        System.out.println("\n--- Ajouter un vaisseau ---");
        System.out.print("Nom du vaisseau : ");
        String nom = scanner.nextLine();
        System.out.print("Capacité du vaisseau : ");
        int capacite = Integer.parseInt(scanner.nextLine());

        try {
            systeme.ajouterVaisseau(new Vaisseau(nom, "TypeParDefaut", capacite));
            System.out.println("Vaisseau ajouté avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void gererPersonnes() {
        while (true) {
            System.out.println("\n--- Gestion des personnes ---");
            System.out.println("1. Ajouter une personne");
            System.out.println("2. Lister les personnes");
            System.out.println("3. Retour au menu principal");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();
    
            switch (choix) {
                case "1" -> ajouterPersonne();
                case "2" -> listerPersonnes();
                case "3" -> {
                    return; // Retour au menu principal
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void ajouterPersonne() {
        System.out.println("\n--- Ajouter une personne ---");
        System.out.print("Type (1 pour Civil, 2 pour Officier) : ");
        String type = scanner.nextLine();
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Identifiant : ");
        String identifiant = scanner.nextLine();
    
        try {
            if (type.equals("1")) {
                System.out.print("Planète d'origine : ");
                String planete = scanner.nextLine();
                System.out.print("Motif du voyage : ");
                String motif = scanner.nextLine();
                Civil civil = new Civil(nom, prenom, identifiant, planete, motif);
                systeme.ajouterPersonne(civil);
                System.out.println("Civil ajouté avec succès : " + civil);
            } else if (type.equals("2")) {
                System.out.print("Rang : ");
                String rang = scanner.nextLine();
                System.out.print("Spécialité : ");
                String specialite = scanner.nextLine();
                Officier officier = new Officier(nom, prenom, identifiant, rang, specialite);
                systeme.ajouterPersonne(officier);
                System.out.println("Officier ajouté avec succès : " + officier);
            } else {
                System.out.println("Type invalide. Veuillez entrer 1 ou 2.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur lors de l'ajout : " + e.getMessage());
        }
    }
    
    private void listerPersonnes() {
        System.out.println("\n--- Liste des personnes ---");
        List<Personne> personnes = systeme.getPersonnes();
        if (personnes.isEmpty()) {
            System.out.println("Aucune personne disponible.");
        } else {
            for (Personne personne : personnes) {
                System.out.println(personne.getNom() + " " + personne.getPrenom() + " (" + personne.getIdentifiant() + ") - " + personne.getDescription());
            }
        }
    }

    private void gererMissions() {
        while (true) {
            System.out.println("\n--- Gestion des missions ---");
            System.out.println("1. Créer une mission");
            System.out.println("2. Lister les missions");
            System.out.println("3. Retour au menu principal");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> creerMission();
                case "2" -> listerMissions();
                case "3" -> {
                    return; // Retour au menu principal
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void creerMission() {
        System.out.println("\n--- Créer une mission ---");
        System.out.print("Code de la mission : ");
        String code = scanner.nextLine();
        System.out.print("Description : ");
        String description = scanner.nextLine();
        System.out.print("Date de départ (format YYYY-MM-DD) : ");
        String dateDepartStr = scanner.nextLine();
        System.out.print("Date de retour (format YYYY-MM-DD) : ");
        String dateRetourStr = scanner.nextLine();
        System.out.print("Destination : ");
        String destination = scanner.nextLine();
        System.out.print("Capacité maximale : ");
        int capaciteMaximale;

        try {
            capaciteMaximale = Integer.parseInt(scanner.nextLine());
            if (capaciteMaximale < 0) {
                System.out.println("La capacité maximale ne peut pas être négative.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Capacité maximale invalide. Veuillez entrer un nombre.");
            return;
        }

        try {
            // Conversion des dates
            Date dateDepart = java.sql.Date.valueOf(dateDepartStr);
            Date dateRetour = java.sql.Date.valueOf(dateRetourStr);

            // Création de la mission
            Mission mission = new Mission(code, description, dateDepart, dateRetour, destination, null, capaciteMaximale);
            systeme.creerMission(mission);
            System.out.println("Mission créée avec succès !");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de la mission : " + e.getMessage());
        }
    }

    private void listerMissions() {
        System.out.println("\n--- Liste des missions ---");
        List<Mission> missions = systeme.getMissions();
        if (missions.isEmpty()) {
            System.out.println("Aucune mission disponible.");
        } else {
            for (Mission mission : missions) {
                System.out.println(mission);
            }
        }
    }

    private void gererReservations() {
        while (true) {
            System.out.println("\n--- Gestion des réservations ---");
            System.out.println("1. Créer une réservation");
            System.out.println("2. Lister les réservations");
            System.out.println("3. Annuler une réservation");
            System.out.println("4. Retour au menu principal");
            System.out.print("Votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> creerReservation();
                case "2" -> listerReservations();
                case "3" -> annulerReservation();
                case "4" -> {
                    return; // Retour au menu principal
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void creerReservation() {
        System.out.println("\n--- Créer une réservation ---");
        System.out.print("Identifiant de la mission : ");
        String idMission = scanner.nextLine();
        System.out.print("Identifiant de la personne : ");
        String idPersonne = scanner.nextLine();

        try {
            Mission mission = systeme.trouverMissionParId(idMission);
            Personne personne = systeme.trouverPersonneParId(idPersonne);

            if (mission == null) {
                System.out.println("Mission introuvable.");
                return;
            }
            if (personne == null) {
                System.out.println("Personne introuvable.");
                return;
            }

            boolean success = systeme.creerReservation(mission, personne);
            if (success) {
                System.out.println("Réservation créée avec succès !");
            } else {
                System.out.println("Impossible de créer la réservation (mission complète ou autre problème).");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de la réservation : " + e.getMessage());
        }
    }

    private void listerReservations() {
        System.out.println("\n--- Liste des réservations ---");
        List<Reservation> reservations = systeme.getReservations();
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation disponible.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    private void annulerReservation() {
        System.out.println("\n--- Annuler une réservation ---");
        System.out.print("Identifiant de la réservation : ");
        String idReservation = scanner.nextLine();

        try {
            boolean success = systeme.annulerReservation(idReservation);
            if (success) {
                System.out.println("Réservation annulée avec succès !");
            } else {
                System.out.println("Réservation introuvable.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'annulation de la réservation : " + e.getMessage());
        }
    }

    private void sauvegarderDonnees() {
        System.out.print("Nom du fichier de sauvegarde : ");
        String fichier = scanner.nextLine();
        try {
            systeme.sauvegarderDonnees();
            System.out.println("Données sauvegardées avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }

    private void chargerDonnees() {
        System.out.print("Nom du fichier de sauvegarde : ");
        String fichier = scanner.nextLine();
        try {
            systeme.chargerDonnees();
            System.out.println("Données chargées avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
        }
    }

    public List<Personne> getPersonnes() {
        return new ArrayList<>(personnes);
    }

    public void ajouterPersonne(Personne personne) {
        if (personne == null) {
            throw new IllegalArgumentException("La personne ne peut pas être null.");
        }
        personnes.add(personne);
    }

    public void ajouterMission(Mission mission) {
        if (mission == null) {
            throw new IllegalArgumentException("La mission ne peut pas être null.");
        }
        missions.add(mission);
    }

    public List<Mission> getMissions() {
        return new ArrayList<>(missions);
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
        for (Mission mission : missions) {
            if (mission.annulerReservation(idReservation)) {
                return true;
            }
        }
        return false;
    }

    public List<Reservation> getReservations() {
        List<Reservation> reservations = new ArrayList<>();
        for (Mission mission : missions) {
            reservations.addAll(mission.getReservations());
        }
        return reservations;
    }

    public Mission trouverMissionParId(String idMission) {
        for (Mission mission : missions) {
            if (mission.getCode().equals(idMission)) {
                return mission;
            }
        }
        return null;
    }

    public Personne trouverPersonneParId(String idPersonne) {
        for (Personne personne : personnes) {
            if (personne.getIdentifiant().equals(idPersonne)) {
                return personne;
            }
        }
        return null;
    }
}