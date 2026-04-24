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

    public void addUtente(Utente user){
        utenti.add(user);
    }

    public void addProdotto(Prodotto product){
        prodotti.add(product);
    }

    public void addOrdine(Ordine order){
        ordiniDelGiorno.add(order);
    }

    public void visualizzaDatiUtente(String codiceFiscale, String email, String numeroTelefono){
        for (int i = 0; i < utenti.size(); i++){
            if (utenti.get(i).getCodiceFiscale().equals(codiceFiscale)){
                System.out.println(utenti.get(i).toString());
            } else if (utenti.get(i).getEmail().equals(email)) {
                System.out.println(utenti.get(i).toString());
            }else if (utenti.get(i).getNumeroTel().equals(numeroTelefono)){
                System.out.println(utenti.get(i).toString());
            }else{
                System.out.println("Utente non trovato / inesistente!");
            }
        }
    }
}
