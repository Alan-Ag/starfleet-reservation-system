# Documentation de Conception - StarFleetReservation

## 1. Introduction
Le projet **StarFleetReservation** est un système de gestion des réservations pour les missions spatiales de Starfleet. Il permet de gérer les vaisseaux, les missions, les réservations et les personnes impliquées.

Cette documentation décrit la conception du système, y compris son architecture, ses composants principaux et ses interactions.

---

## 2. Objectifs du système
- Gérer les vaisseaux spatiaux (ajout, liste, etc.).
- Planifier et gérer les missions spatiales.
- Gérer les réservations pour les missions.
- Gérer les personnes impliquées (officiers, passagers, etc.).
- Sauvegarder et charger les données du système.

---

## 3. Architecture du système
Le système suit une architecture **MVC (Modèle-Vue-Contrôleur)** simplifiée :
- **Modèle** : Contient les classes représentant les entités principales (Vaisseau, Mission, Personne, etc.).
- **Vue** : Interface console pour interagir avec l'utilisateur.
- **Contrôleur** : La classe `SystemeReservation` qui gère la logique métier.

---

## 4. Diagramme de classes
Voici un aperçu des principales classes du système :
+-------------------+ | SystemeReservation | +-------------------+ | - vaisseaux : List<Vaisseau> | | - missions : List<Mission> | | - personnes : List<Personne> | +-------------------+ | + ajouterVaisseau(v : Vaisseau) | | + ajouterMission(m : Mission) | | + ajouterPersonne(p : Personne) | | + sauvegarderDonnees(fichier : String) | | + chargerDonnees(fichier : String) | +-------------------+

+-------------------+ +-------------------+ | Vaisseau | | Mission | +-------------------+ +-------------------+ | - nom : String | | - code : String | | - immatriculation : String | | - description : String | | - capaciteMaximale : int | | - dateDepart : Date | +-------------------+ +-------------------+ | + getNom() : String | | + getCode() : String | +-------------------+ +-------------------+

---

## 5. Description des composants

### 5.1 Modèle
- **Vaisseau** : Représente un vaisseau spatial avec un nom, une immatriculation et une capacité maximale.
- **Mission** : Représente une mission avec un code, une description, une destination, des dates de départ et de retour, et une capacité maximale.
- **Personne** : Représente une personne impliquée dans le système (officier ou passager).
- **Reservation** : Représente une réservation pour une mission.

### 5.2 Vue
- **InterfaceConsole** : Fournit une interface utilisateur en ligne de commande pour interagir avec le système.

### 5.3 Contrôleur
- **SystemeReservation** : Gère la logique métier, comme l'ajout de vaisseaux, de missions, de personnes, et la gestion des réservations.

---

## 6. Cas d'utilisation
### 6.1 Ajouter un vaisseau
1. L'utilisateur sélectionne l'option "Ajouter un vaisseau".
2. Le système demande le nom, l'immatriculation et la capacité maximale du vaisseau.
3. Le système ajoute le vaisseau à la liste des vaisseaux.

### 6.2 Créer une mission
1. L'utilisateur sélectionne l'option "Créer une mission".
2. Le système demande les informations de la mission (code, description, dates, destination, capacité).
3. Le système ajoute la mission à la liste des missions.

### 6.3 Lister les missions
1. L'utilisateur sélectionne l'option "Lister les missions".
2. Le système affiche toutes les missions disponibles.

---

## 7. Sauvegarde et chargement des données
- Les données sont sauvegardées dans un fichier (`sauvegarde.dat`) en utilisant la sérialisation Java.
- Le système peut charger les données depuis ce fichier pour restaurer l'état précédent.

---

## 8. Technologies utilisées
- **Langage** : Java
- **IDE** : Visual Studio Code
- **Format de sauvegarde** : Sérialisation Java ou JSON
- **Gestion des dépendances** : Maven (si applicable)

---

## 9. Améliorations futures
- Ajouter une interface graphique (GUI) pour remplacer l'interface console.
- Implémenter une base de données pour stocker les données au lieu d'utiliser des fichiers.
- Ajouter des tests unitaires pour valider les fonctionnalités.
- Permettre la gestion des équipages pour chaque mission.

---

## 10. Conclusion
Le système **StarFleetReservation** offre une solution simple et efficace pour gérer les réservations de missions spatiales. Cette documentation fournit une vue d'ensemble de la conception et peut être utilisée comme référence pour le développement et l'amélioration du système.

