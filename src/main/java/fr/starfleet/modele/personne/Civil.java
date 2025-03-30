package fr.starfleet.modele.personne;

public class Civil extends Personne {
    private String planeteOrigine;
    private String motifVoyage;

    public Civil(String nom, String prenom, String identifiant, String planeteOrigine, String motifVoyage) {
        super(nom, prenom, identifiant);
        this.planeteOrigine = planeteOrigine;
        this.motifVoyage = motifVoyage;
    }

    @Override
    public String getDescription() {
        return "Civil [planeteOrigine=" + planeteOrigine + ", motifVoyage=" + motifVoyage + "]";
    }

    // Getters and Setters
    public String getPlaneteOrigine() {
        return planeteOrigine;
    }

    public void setPlaneteOrigine(String planeteOrigine) {
        this.planeteOrigine = planeteOrigine;
    }

    public String getMotifVoyage() {
        return motifVoyage;
    }

    public void setMotifVoyage(String motifVoyage) {
        this.motifVoyage = motifVoyage;
    }
    @Override
    public String toString() {
        return "Civil [planeteOrigine=" + planeteOrigine + ", motifVoyage=" + motifVoyage + ", " + super.toString() + "]";
    }
}