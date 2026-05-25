package org.gruppo3;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Azienda {
    private final String nome = "Fakezone";
    private final String sitoWeb = "www.fakezone.sola";
    private ArrayList<Utente> utenti;
    private ArrayList<Prodotto> prodotti;
    private ArrayList<Ordine> ordini;

    public Azienda(){
        utenti = new ArrayList<>();
        prodotti = new ArrayList<>();
        ordini = new ArrayList<>();
    }

    public void addUtente(Utente user){
        utenti.add(user);
    }

    public void addProdotto(Prodotto product){
        prodotti.add(product);
    }

    public void addOrdine(Ordine order){
        ordini.add(order);
    }

    public void visualizzaDatiUtente(String codiceFiscale, String email, String numeroTelefono){
        Utente utente = getUtente(email, codiceFiscale, numeroTelefono);

        if (utente == null){
            System.out.println("Impossibile trovare l'utente");
        }else {
            System.out.println(utente.toString());
        }
    }

    public void visualizzaDatiProdotto(String codice){
        Prodotto prodotto = getProdotto(codice);

        if (prodotto == null){
            System.out.println("Prodotto non trvato");
        }else {
            System.out.println(prodotto.toString());
            prodotto.visualizzaRecensioni();
        }
    }

    public void visualizzaDatiOrdine(int numeroOrdine){
        System.out.println(getOrdine(numeroOrdine).toString());
    }

    public void modificaStatoOrdine(String stato, int numeroOrdine){
        Ordine ordine = getOrdine(numeroOrdine);

        if (ordine == null){
            System.out.println("Ordine non trovato");
        }else{
            ordine.setStato(stato);
        }
        System.out.println("Stato attuale: " + stato);
    }

    public void visulizzaIncasssoPeriodo(LocalDate inizio, LocalDate fine){
        double incassiPeriodo = 0;
        for (int i = 0; i < ordini.size(); i++){
            if ((ordini.get(i).getDataOrdine().isAfter(inizio) || ordini.get(i).getDataOrdine().isEqual(inizio))
                    && (ordini.get(i).getDataOrdine().isBefore(fine) || ordini.get(i).getDataOrdine().isEqual(fine))){
                incassiPeriodo += ordini.get(i).getImportTotale();
            }
        }

        System.out.println("Importo totale del periodo " + inizio.toString() + "/" + fine.toString() + ": " + incassiPeriodo);
    }

    public void esportaDati() {

        try{
            ObjectOutputStream outputUtenti = new ObjectOutputStream(new FileOutputStream("datiUtenti.dat"));
            ObjectOutputStream outputProdotti = new ObjectOutputStream(new FileOutputStream("datiProdotti.dat"));
            ObjectOutputStream outputOrdini = new ObjectOutputStream(new FileOutputStream("datiOrdini.dat"));

            outputUtenti.writeObject(utenti);
            outputProdotti.writeObject(prodotti);
            outputOrdini.writeObject(ordini);

            outputUtenti.close();
            outputProdotti.close();
            outputOrdini.close();

            System.out.println("Salvataggio completato");
        }catch (FileNotFoundException fileNotFoundEx){
            System.err.println("Errore: " + fileNotFoundEx.getMessage());
            System.err.println("Impossibile trovare i files");
        }catch (IOException ioEx){
            System.err.println("Errore: " + ioEx.getMessage());
            System.err.println("Errore di Input/Output");
        }
    }

    public void importaDati(String scelta){
        try{
            ObjectInputStream inputUtenti = new ObjectInputStream(new FileInputStream("datiUtenti.dat"));
            ObjectInputStream inputProdotti = new ObjectInputStream(new FileInputStream("datiProdotti.dat"));
            ObjectInputStream inputOrdini = new ObjectInputStream(new FileInputStream("datiOrdini.dat"));

            if ((!utenti.isEmpty() || !prodotti.isEmpty() || !ordini.isEmpty()) && (scelta.equals("y") || scelta.equals("Y"))){
                utenti = null;
                prodotti = null;
                ordini = null;
            }

            utenti = (ArrayList<Utente>) inputUtenti.readObject();
            prodotti = (ArrayList<Prodotto>) inputProdotti.readObject();
            ordini = (ArrayList<Ordine>) inputOrdini.readObject();

            System.out.println("Importazione completata");

        }catch (FileNotFoundException fileNotFoundEx){
            System.err.println("Errore: " + fileNotFoundEx.getMessage());
            System.err.println("Files non trovati");
        }catch (IOException ioEx){
            System.err.println("Errore: " + ioEx.getMessage());
            System.err.println("Errore di Input/Output");
        }catch (ClassNotFoundException classNotFoundEx){
            System.err.println("Errore: " + classNotFoundEx.getMessage());
            System.err.println("Classi non trovate");
        }
    }

    public Utente getUtente(String email, String codiceFiscale, String numeroTelefono){
        for (int i = 0; i < utenti.size(); i++){
            if (utenti.get(i).getCodiceFiscale().equals(codiceFiscale)){ // Controllo con codice fiscale
                return utenti.get(i);
            } else if (utenti.get(i).getEmail().equals(email)) { // Controllo con email
                return utenti.get(i);
            }else if (utenti.get(i).getNumeroTel().equals(numeroTelefono)){ // Controllo con numero di telefono
                return utenti.get(i);
            }
        }
        return null;
    }

    public Prodotto getProdotto(String codice){
        for (int i = 0; i < prodotti.size(); i++){
            if (prodotti.get(i).getCodice().equals(codice)){
                return prodotti.get(i);
            }
        }

        return null;
    }

    public Ordine getOrdine(int numeroOrdine){
        for (int i = 0; i < ordini.size(); i++){
            if (ordini.get(i).getNumeroOrdine() == numeroOrdine){
                return ordini.get(i);
            }
        }

        return null;
    }

    public int generaNumeroOrdine(){
        int numero = -1;
        for (int i = 0; i < ordini.size(); i++){
            numero = ordini.get(i).getNumeroOrdine();
        }

        return numero + 1;
    }

    public void aggiungiRecensione(Recenzione recenzione, String codiceProdotto){
        Prodotto prodotto = getProdotto(codiceProdotto);

        if (prodotto == null){
            System.out.println("Impossibile trovare il prodotto");
        }else {
            prodotto.aggiungiRecensione(recenzione);
        }
    }

    public void visuaizzaRecensioniProdotto(String codiceProdotto){
        Prodotto prodotto = getProdotto(codiceProdotto);

        if (prodotto == null){
            System.out.println("Impossibile trovare il prodotto");
        }else {
            prodotto.visualizzaRecensioni();
        }
    }
}