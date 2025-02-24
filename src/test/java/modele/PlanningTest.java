package modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanningTest {
    private final int SIZE = 100;
    private Planning planning;
    private Reservation res1, res2, res3;
    private DateCalendrier date1, date2, date3;
    private PlageHoraire plage1, plage2;
    Reservation resInvalide = new Reservation(null, null);

    @BeforeEach
    void setUp() {
        planning = new Planning();

        date1 = new DateCalendrier(2025, 2, 17);
        date2 = new DateCalendrier(2025, 3, 1);
        date3 = new DateCalendrier(2025, 1, 10); // Plus ancienne date

        plage1 = new PlageHoraire(new Horaire(9, 0), new Horaire(12, 0));
        plage2 = new PlageHoraire(new Horaire(14, 0), new Horaire(16, 0));

        res1 = new Reservation(date1, plage1);
        res2 = new Reservation(date1, plage2);
        res3 = new Reservation(date3, plage1); // Réservation avec la date la plus ancienne
    }


    @Test
    void testAjoutReservationValide() {
        assertTrue(planning.ajout(res1));
    }

    @Test
    void testAjoutReservationInvalide() {
        Reservation invalide = new Reservation(null, plage1);
        assertFalse(planning.ajout(invalide));
    }

    @Test
    void testAjoutReservationQuandPlein() {
        for (int i = 0; i < SIZE; i++) {
            planning.ajout(new Reservation(new DateCalendrier(2025, 4, 10), plage1));
        }
        assertFalse(planning.ajout(res1));
    }

    /*** 🔹 TESTS POUR LA MÉTHODE getReservations 🔹 ***/

    @Test
    void testGetReservationsDatePresente() {
        planning.ajout(res1);
        planning.ajout(res2);
        List<Reservation> reservations = planning.getReservations(date1);
        assertEquals(2, reservations.size());
        assertTrue(reservations.contains(res1));
        assertTrue(reservations.contains(res2));
    }

    @Test
    void testGetReservationsDateAbsente() {
        planning.ajout(res1);
        List<Reservation> reservations = planning.getReservations(date2);
        assertTrue(reservations.isEmpty());
    }

    @Test
    void testAjoutReservationInvalideQuandPlein() {
        for (int i = 0; i < SIZE; i++) {
            planning.ajout(new Reservation(new DateCalendrier(2025, 4, 10), plage1));
        }
        assertFalse(planning.ajout(resInvalide), "Ajout d'une réservation invalide dans un tableau plein devrait retourner false");
    }

    @Test
    void testGetReservationsTableauVide() {
        List<Reservation> reservations = planning.getReservations(date1);
        assertTrue(reservations.isEmpty(), "Si le tableau est vide, la liste doit être vide");
    }

    @Test
    void testGetReservationsAvecDateInvalide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            planning.getReservations(null);
        });
        assertEquals("Date invalide", exception.getMessage(), "Une date invalide doit générer une exception");
    }

    @Test
    void testPlusAncienneReserv() {
        planning.ajout(res1); // Supposons res1 = 2024-06-10
        planning.ajout(res2); // Supposons res2 = 2024-01-15
        planning.ajout(res3); // Supposons res3 = 2023-05-20 (le plus ancien)

        // Chercher la plus ancienne réservation entre les indices 0 et 2
        int indiceAncienne = planning.plusAncienneReserv(0, 2);

        // Vérifier que l'indice retourné correspond bien à res3
        assertEquals(2, indiceAncienne, "L'indice de la plus ancienne réservation doit être 2");
    }
    @Test
    void testPlusAncienneReservTableauVide() {
        assertThrows(IllegalArgumentException.class, () -> planning.plusAncienneReserv(0, 0),
                "Si le tableau est vide, la méthode doit lever une exception");
    }
    @Test
    void testPlusAncienneReservIndicesInvalides() {
        planning.ajout(res1);
        planning.ajout(res2);

        // Indice de début négatif
        assertThrows(IllegalArgumentException.class, () -> planning.plusAncienneReserv(-1, 1));

        // Indice de fin hors limites
        assertThrows(IllegalArgumentException.class, () -> planning.plusAncienneReserv(0, 10));

        // Indice de début > Indice de fin
        assertThrows(IllegalArgumentException.class, () -> planning.plusAncienneReserv(2, 1));
    }


}


