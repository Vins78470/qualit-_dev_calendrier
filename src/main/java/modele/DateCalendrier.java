package modele;


import java.util.Calendar;

public class DateCalendrier extends Date implements ConstantesCalendrier,Comparable<Date> {
    public int jourSemaine;
    public int mois;
    public int annee;

    public DateCalendrier(){

        Calendar today= Calendar.getInstance();
        annee=today.get(Calendar.YEAR);
        mois=today.get(Calendar.MONTH)+1;
        jour=today.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek=today.get(Calendar.DAY_OF_WEEK);
        jourSemaine=dayOfWeek;

        if(jourSemaine==1)
            jourSemaine=7;
        else jourSemaine-=1;



    }

    public DateCalendrier(int parJour,int parMois,int parAnnee){
        super(parJour, parMois,parAnnee); //appel au super constructeur
        Calendar today= Calendar.getInstance();
        today.set(parAnnee,parMois-1,parJour);
        this.annee=today.get(Calendar.YEAR);
        this.mois=today.get(Calendar.MONTH)+1;
        this.jour=today.get(Calendar.DAY_OF_MONTH);
        this.jourSemaine=today.get(Calendar.DAY_OF_WEEK);

        if(jourSemaine==1)
            jourSemaine=7;
        else {
            jourSemaine-=1;
        }

    }

    public DateCalendrier dateDeLaVeille(){
        Date d=super.dateDeLaVeille();
        DateCalendrier c= new DateCalendrier(d.jour,d.mois,d.annee);
        return c;
    }

    public DateCalendrier dateDuLendemain(){
        Date d= super.dateDuLendemain();
        DateCalendrier c= new DateCalendrier( d.jour,d.mois, d.annee);
        return c;


    }


    public String numMonthTostr(){
        return MOIS[mois-1];
    }



    public String toString(){
        return JOUR_SEMAINE[jourSemaine-1]+" "+ jour +" "+ MOIS[mois-1] + annee;
    }

    public int compareTo(Date date){
        if (date.annee>annee){
            return -1;

        }
        if(date.annee<annee){
            return 1;
        }
        if(date.mois>mois){
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

    public int getJourSemaine() {
        return jourSemaine;
    }


    public int getMois() {
        return mois;
    }




    public int getAnnee(){
        return annee;
    }
}