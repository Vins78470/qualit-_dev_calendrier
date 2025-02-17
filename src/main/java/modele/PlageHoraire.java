package modele;

public class PlageHoraire implements Comparable<PlageHoraire> {
    private Horaire parDebut;
    private Horaire parFin;

    public PlageHoraire(Horaire debut, Horaire fin) {
        this.parDebut = debut;
        this.parFin = fin;
    }

    // Getters
    public Horaire getDebut() {
        return parDebut;
    }

    public Horaire getFin() {
        return parFin;
    }

    // Vérifie si la plage horaire est valide
    public boolean estValide() {
        return parDebut != null && parFin != null && parDebut.compareTo(parFin) < 0;
    }

    // Compare deux plages horaires
    @Override
    public int compareTo(PlageHoraire parPlageHoraire) {
        int cmp = this.parDebut.compareTo(parPlageHoraire.parDebut);
        if (cmp == 0) {
            return this.parFin.compareTo(parPlageHoraire.parFin);
        }
        return cmp;
    }

    // Affichage clair de la plage horaire
    @Override
    public String toString() {
        return "PlageHoraire{" + "debut=" + parDebut + ", fin=" + parFin + '}';
    }
}
