package org.gruppo3;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prodotto {
    private String codice;
    private String descrizione;
    private ArrayList<String> pathImmagini;
    private double costo;

    public Prodotto(String codice, String descrizione, double costo){
        this.codice = codice;
        this.descrizione = descrizione;
        this.costo = costo;
    }

    public String toString(){
        return "il codice del prodotto e': " + codice + "\n descrizzione del prodotto: " + descrizione + "\n il costo del prodotto e': " + costo;
    }

    public void aggiungiImmagine(String path){
        pathImmagini.add(path);
    }

    public double getCosto() {
        return costo;
    }

    public String getCodice(){
        return codice;
    }
}
