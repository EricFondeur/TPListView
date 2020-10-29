package com.example.listview3;

public class Mois {
    private String mois;
    private boolean etat;
    private boolean select;

    public Mois (String month, boolean status){
        mois = month;
        etat = status;
        select = false;
    }


    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getMois() {
        return mois;
    }

    public boolean est_affiche() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public void select(){
        if (select){
            select = false;
        }else{
            select = true;
        }
    }

    public boolean isSelecte() {
        return select;
    }

    @Override
    public String toString() {
        return mois;
    }
}
