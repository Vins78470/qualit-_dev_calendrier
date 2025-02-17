package modele;

class Reservation {
    private DateCalendrier chDateCalend;
    private PlageHoraire chPlageHoraire;

    public Reservation(DateCalendrier dateCalend, PlageHoraire plageHoraire) {
        this.chDateCalend = dateCalend;
        this.chPlageHoraire = plageHoraire;
    }

    // Getter pour la date de la réservation
    public DateCalendrier getDate() {
        return chDateCalend;
    }

    public int compareTo(Reservation parReserv) {
        return this.chDateCalend.compareTo(parReserv.chDateCalend);
    }

    public boolean estValide() {
        if (chDateCalend == null) {
            return false; // La réservation doit avoir une date valide
        }

        if (chPlageHoraire == null || chPlageHoraire.getDebut().compareTo(chPlageHoraire.getFin()) >= 0) {
            return false; // L'horaire doit être valide (début avant fin)
        }

        return true; // La réservation est valide
    }
}
