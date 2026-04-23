package org.gruppo3;

import java.util.ArrayList;

public class Azienda {
    private String nome = "Fakezone";
    private String sitoWeb = "www.fakezone.sola";
    private ArrayList<Utente> utenti;
    private ArrayList<Prodotto> prodotti;
    private ArrayList<Ordine> ordiniDelGiorno;

    public Azienda(){
        utenti = new ArrayList<>();
        prodotti = new ArrayList<>();
        ordiniDelGiorno = new ArrayList<>();
    }
}
