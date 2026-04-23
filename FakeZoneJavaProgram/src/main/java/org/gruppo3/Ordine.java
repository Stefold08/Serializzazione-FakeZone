package org.gruppo3;

import java.time.LocalDate;
import java.util.ArrayList;

public class Ordine {
    private LocalDate dataOrdine;
    private ArrayList<DettagliOrdine> dettagliProdotti;
    private double importTotale;
    private char stato;
    private Utente utente;
    private int numeroOrdine;

    public Ordine(LocalDate dataOrdine, double importTotale, Utente utente, int numeroOrdine){
        this.dataOrdine = dataOrdine;
        this.importTotale = importTotale;
        this.utente = utente;
        this.numeroOrdine = numeroOrdine;
    }

    public String toString(){
        return "la data dell'ordine e': " + dataOrdine + "\n l'importo dell'ordine e': " + importTotale + "\n l'utente che ha fatto l'ordine e': " + utente + "\n il numero dell'ordine e': " + numeroOrdine;
    }

    public int getNumeroOrdine(){
        return numeroOrdine;
    }

    public char getStato(){
        return stato;
    }

    public void setStato(char stato){
        this.stato = stato;
    }
}
