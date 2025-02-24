package modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanningTest {

    private Planning planning;
    private Reservation res1, res2, res3;
    private DateCalendrier date1, date2, date3;
    private PlageHoraire plage1, plage2;

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
}


