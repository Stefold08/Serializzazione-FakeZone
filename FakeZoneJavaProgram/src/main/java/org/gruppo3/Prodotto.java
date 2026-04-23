package org.gruppo3;

import java.util.ArrayList;

public class Prodotto {
    private String codice;
    private String descrizione;
    private double costo;
    private ArrayList<String> pathImmagini;

    public Prodotto(String codice, String descirzione, double costo){
        this.codice = codice;
        this.descrizione = descirzione;
        this.costo = costo;
        pathImmagini = new ArrayList<>();
    }
}
