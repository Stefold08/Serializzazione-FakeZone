package org.gruppo3;

import java.time.LocalDate;
import java.util.ArrayList;

import java.io.Serializable;

public class Ordine implements Serializable{
    private static final long serialVersionUID = 1;
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
        String messaggio = null;

        messaggio = "la data dell'ordine e': " + dataOrdine + "\n l'importo dell'ordine e': " + importTotale + "\n l'utente che ha fatto l'ordine e': " + utente + "\n il numero dell'ordine e': " + numeroOrdine;

        for (int i = 0; i < dettagliProdotti.size(); i++){
            messaggio += dettagliProdotti.toString() + "\n";
        }

        return messaggio;
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

    public void calcolaImportoTotale(){
        double importo = 0;
        double costoQuantita = 0;
        for (int i = 0; i < dettagliProdotti.size(); i++){
            costoQuantita += dettagliProdotti.get(i).getQuantita();
            importo += dettagliProdotti.get(i).getCosto() * costoQuantita;
        }

        importTotale = importo;
    }

    public double getImportTotale(){
        return importTotale;
    }

    public LocalDate getDataOrdine(){
        return dataOrdine;
    }
}