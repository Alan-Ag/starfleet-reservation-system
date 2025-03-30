package fr.starfleet;

import fr.starfleet.modele.systeme.SystemeReservation;
import fr.starfleet.modele.vaisseau.Vaisseau;
import fr.starfleet.ui.InterfaceConsole; // Ajouté

public class Main {
    public static void main(String[] args) {
        SystemeReservation systeme = new SystemeReservation();
        Vaisseau vaisseau = new Vaisseau("USS Enterprise", "NCC-1701", 100);
        systeme.ajouterVaisseau(vaisseau); // Optionnel : pour tester l'intégration
        InterfaceConsole console = new InterfaceConsole(systeme);
        console.demarrer();
    }
}