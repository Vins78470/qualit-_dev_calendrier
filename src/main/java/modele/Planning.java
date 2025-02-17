package modele;

import java.util.ArrayList;
import java.util.Date;

public class Planning {
    private final int SIZE = 100;
    private ArrayList<Reservation> chTabReserv;

    public Planning() {
        chTabReserv = new ArrayList<>();
    }

    public Boolean ajout(Reservation parReserv) {
        if (parReserv == null || !parReserv.estValide()) {
            System.out.println("Réservation invalide !");
            return false;
        }

        if (chTabReserv.size() < SIZE) { // Vérifie si on n'a pas atteint la limite
            chTabReserv.add(parReserv);
            return true;
        }

        System.out.println("Tableau plein !");
        return false;
    }

    public ArrayList<Reservation> getReservations(Date parDate) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        for (Reservation res : chTabReserv) {
            if (res != null && res.getDate().equals(parDate)) {
                reservations.add(res);
            }
        }

        return reservations;
    }

    public int plusAncienneReserv(int parDeb, int parFin) {
        if (parDeb < 0 || parFin >= chTabReserv.size() || parDeb > parFin) {
            throw new IllegalArgumentException("Indices invalides");
        }

        int i_plusAncien = -1;

        for (int i = parDeb; i <= parFin; i++) {
            if (chTabReserv.get(i) == null) continue; // Ignorer les valeurs nulles

            if (i_plusAncien == -1 || chTabReserv.get(i).compareTo(chTabReserv.get(i_plusAncien)) < 0) {
                i_plusAncien = i;
            }
        }

        return i_plusAncien; // Retourne l'indice ou -1 si toutes les valeurs sont null
    }

    public void tri() {
        for (int i = 0; i < chTabReserv.size() - 1; i++) {
            if (chTabReserv.get(i) == null) continue; // Ignorer les cases nulles

            int i_min = plusAncienneReserv(i, chTabReserv.size() - 1);

            if (i_min != -1 && i_min != i) { // Vérifier si un échange est nécessaire
                Reservation tmp = chTabReserv.get(i_min);
                chTabReserv.set(i_min, chTabReserv.get(i));
                chTabReserv.set(i, tmp);
            }
        }
    }
}
