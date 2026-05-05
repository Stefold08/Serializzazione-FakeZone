package org.gruppo3;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Azienda {
    private final String nome = "Fakezone";
    private final String sitoWeb = "www.fakezone.sola";
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
            if (utenti.get(i).getCodiceFiscale().equals(codiceFiscale)){ // Controllo con codice fiscale
                System.out.println(utenti.get(i).toString());
            } else if (utenti.get(i).getEmail().equals(email)) { // Controllo con email
                System.out.println(utenti.get(i).toString());
            }else if (utenti.get(i).getNumeroTel().equals(numeroTelefono)){ // Controllo con numero di telefono
                System.out.println(utenti.get(i).toString());
            }else{
                System.out.println("Utente non trovato / inesistente!");
            }
        }
    }

    public void visualizzaDatiProdotto(String codice){
        for (int i = 0; i < prodotti.size(); i++){
            if (prodotti.get(i).getCodice().equals(codice)){
                System.out.println(prodotti.get(i).toString());
            }
        }
    }

    public void visualizzaDatiOrdine(int numeroOrdine){
        for (int i = 0; i < ordiniDelGiorno.size(); i++){
            if (ordiniDelGiorno.get(i).getNumeroOrdine() == numeroOrdine){
                System.out.println(ordiniDelGiorno.get(i).toString());
            }
        }
    }

    public void modificaStatoOrdine(char stato, int numeroOrdine){
        for (int i = 0; i < ordiniDelGiorno.size(); i++){
            if (ordiniDelGiorno.get(i).getNumeroOrdine() == numeroOrdine){
                ordiniDelGiorno.get(i).setStato(stato);
            }
        }
    }

    public void visulizzaIncasssoPeriodo(LocalDate inizio, LocalDate fine){
        double incassiPeriodo = 0;
        for (int i = 0; i < ordiniDelGiorno.size(); i++){
            if ((ordiniDelGiorno.get(i).getDataOrdine().isAfter(inizio) || ordiniDelGiorno.get(i).getDataOrdine().isEqual(inizio))
                    && (ordiniDelGiorno.get(i).getDataOrdine().isBefore(fine) || ordiniDelGiorno.get(i).getDataOrdine().isEqual(fine))){
                incassiPeriodo += ordiniDelGiorno.get(i).getImportTotale();
            }
        }

        System.out.println("Importo totale del periodo " + inizio.toString() + "/" + fine.toString() + ": " + incassiPeriodo);
    }

    public void esportaDati(){
        ObjectOutputStream utentiOut = null;
        ObjectOutputStream prodottoOut = null;
        ObjectOutputStream ordiniOut = null;

        try{
            utentiOut = new ObjectOutputStream(new FileOutputStream("datiUtenti.dat"));
            prodottoOut = new ObjectOutputStream(new FileOutputStream("datiProdotti.dat"));
            ordiniOut = new ObjectOutputStream(new FileOutputStream("datiOrdini.dat"));
        } catch (FileNotFoundException fileEx){
            System.err.println("Errore: " + fileEx.getMessage());
            System.err.println("Files non trovati");
        } catch (IOException ioEx){
            System.err.println("Errore: " + ioEx.getMessage());
            System.err.println("Errore di Input/Output");
        }

        try{
            // Salvataggio degli utenti
            for (int i = 0; i < utenti.size(); i++){
                utentiOut.writeObject(utenti.get(i));
            }

            // Salvataggio prodotti
            for (int i = 0; i < prodotti.size(); i++){
                prodottoOut.writeObject(prodotti.get(i));
            }

            // Salvataggio ordini
            for (int i = 0; i < ordiniDelGiorno.size(); i++){
                ordiniOut.writeObject(ordiniDelGiorno.get(i));
            }

            // Chiusura dei file
            utentiOut.close();
            prodottoOut.close();
            ordiniOut.close();

            System.out.println("Salvataggio completato!");
            System.out.println("Tipo di salvataggio: serializzazione");
            System.out.println("Nomi dei file: datiUtenti.dat, datiProdotti.dat, datiOrdini.dat");
        } catch (IOException ioEx){
            System.err.println("Errore: " + ioEx.getMessage());
            System.err.println("Errore di Input/Output");
        } catch (NullPointerException nullPtrEx){
            System.err.println("Errore: " + nullPtrEx.getMessage());
            System.err.println("Oggetto non caricato correttamente");
        }
    }
}
