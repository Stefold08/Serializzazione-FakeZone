package org.gruppo3;

import java.time.LocalDate;
import java.util.ArrayList;

import java.io.Serializable;

public class Ordine implements Serializable{
    private static final long serialVersionUID = 1;
    private LocalDate dataOrdine;
    private ArrayList<DettagliOrdine> dettagliProdotti;
    private double importTotale;
    private String stato;
    private Utente utente;
    private int numeroOrdine;

    public Ordine(LocalDate dataOrdine, Utente utente, int numeroOrdine){
        this.dataOrdine = dataOrdine;
        this.utente = utente;
        this.numeroOrdine = numeroOrdine;
        this.dettagliProdotti = new ArrayList<>();
    }

    public String toString(){
        calcolaImportoTotale();
        String messaggio = null;

        messaggio = "La data dell'ordine e': " + dataOrdine + "\nL'importo dell'ordine e': " + importTotale + "\nL'utente che ha fatto l'ordine e': " + utente.toString() + "\nIl numero dell'ordine e': " + numeroOrdine + "\n";

        for (int i = 0; i < dettagliProdotti.size(); i++){
            messaggio += dettagliProdotti.toString() + "\n";
        }

        return messaggio;
    }

    public int getNumeroOrdine(){
        return numeroOrdine;
    }

    public String getStato(){
        return stato;
    }

    public void setStato(String stato){
        this.stato = stato;
    }

    private void calcolaImportoTotale(){
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

    public void setDettagliProdotti(DettagliOrdine dettagli){
        dettagliProdotti.add(dettagli);
    }
}