package modele;

public class Horaire implements Comparable<Horaire> {
    private int heure;
    private int minute;

    public Horaire(int heure, int minute) {
        this.heure = heure;
        this.minute = minute;
    }

    public int compareTo(Horaire other) {
        if (this.heure != other.heure) {
            return Integer.compare(this.heure, other.heure);
        }
        return Integer.compare(this.minute, other.minute);
    }

}