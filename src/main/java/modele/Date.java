package modele;

public class Date {
    protected int jour;
    protected int mois;
    protected int annee;

    public Date(int parJ, int parM, int parA) {
        jour = parJ;
        mois = parM;
        annee = parA;
    }
    public Date(int ann){
        annee=ann;
        mois=1;
        jour=1;
    }

    public Date(){

    }


    public boolean estValide(){
        if (annee>1582)
            if (mois>=1 && mois<=12 && jour>=1 && jour<=dernierjourdumois(mois,annee))
                return true;
        return false;

    }

    public int dernierjourdumois(int parM,int parA){
        switch(parM){
            case 2: if (estBissextile(parA))
                return 29;
                return 28;
            case 4: case 6: case 9: case 11: return 30;
            default : return 31;

        }
    }

    public static boolean estBissextile(int parA) {
        return parA%4==0 && (parA%100!=0 || parA%400==0);
    }

    public String toString(){
        return jour + "/" + mois + "/" + annee;
    }

    public Date dateDuLendemain() {
        int jourSuivant = jour;
        int moisSuivant = mois;
        int anneeSuivante = annee;
        if (!estValide()){
            System.out.println("date pas valable");
        }
        if (estValide()) {
            jourSuivant++;
            if (jourSuivant > dernierjourdumois(mois, annee)) {
                jourSuivant = 1;
                moisSuivant++;
                if (moisSuivant>12) {
                    moisSuivant = 1;
                    anneeSuivante++;
                }

            }


        }

        return new Date(jourSuivant, moisSuivant, anneeSuivante);
    }

    public Date dateDeLaVeille() {
        int jourHier = jour;
        int moisHier = mois;
        int anneeHier = annee;

        if (!estValide()) {
            System.out.println("Date invalide");

        }

        jourHier--;

        if (jourHier < 1) {
            moisHier--;
            if (moisHier < 1) {
                anneeHier--;
                moisHier = 12;
            }
            jourHier = dernierjourdumois(moisHier,anneeHier);
        }

        return new Date(jourHier, moisHier, anneeHier);
    }



    public int compareTo(Date date){
        if (date.annee>annee){
            return -1;

        }
        if(date.annee<annee){
            return 1;
        }
        if(date.annee>mois){
            return -1;

        }
        if(date.mois<mois){
            return 1;

        }
        if(date.jour>jour){
            return -1;

        }
        if(jour>date.jour){
            return 1;
        }
        return 0;
    }



}