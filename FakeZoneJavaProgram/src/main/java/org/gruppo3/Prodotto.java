package org.gruppo3;

import java.util.ArrayList;

public class Prodotto {
    private String codice;
    private String nomeProdotto;
    private String descrizione;
    private double costo;
    private ArrayList<String> pathImmagini;

    public Prodotto(String codice, String descirzione, String nomeProdotto, double costo){
        this.codice = codice;
        this.descrizione = descirzione;
        this.costo = costo;
        this.nomeProdotto = nomeProdotto;
        pathImmagini = new ArrayList<>();
    }

    @Override
    public String toString(){
        String messaggio = null;
        messaggio = "Codice prodotto: " + codice + "\nNome: " + nomeProdotto + "\nDescrizione:\n" + descrizione + "\nCosto: " + costo + "€" + "\nImmagini:\n";

        for (int i = 0; i < pathImmagini.size(); i++){
            messaggio += pathImmagini.get(i) + "\n";
        }

        return messaggio;
    }

    public void aggiungiImmagine(String path){
        pathImmagini.add(path);
    }
}
