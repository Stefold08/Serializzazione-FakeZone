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

        try (
            ObjectOutputStream utentiOut = new ObjectOutputStream(new FileOutputStream("datiUtenti.dat"));

            ObjectOutputStream prodottoOut = new ObjectOutputStream(new FileOutputStream("datiProdotti.dat"));

            ObjectOutputStream ordiniOut = new ObjectOutputStream(new FileOutputStream("datiOrdini.dat"))
        ){
            // Salvataggio utenti
            for (Utente u : utenti) {
                utentiOut.writeObject(u);
            }

            // Salvataggio prodotti
            for (Prodotto p : prodotti) {
                prodottoOut.writeObject(p);
            }

            // Salvataggio ordini
            for (Ordine o : ordini) {
                ordiniOut.writeObject(o);
            }

            System.out.println("Salvataggio completato!");

        } catch (IOException ioEx) {
            System.err.println("Errore: " + ioEx.getMessage());
        }
    }

    public void importaDati(String scelta){
        ObjectInputStream utentiIn = null;
        ObjectInputStream prodottiIn = null;
        ObjectInputStream ordiniIn = null;

        try {
            utentiIn = new ObjectInputStream(new FileInputStream("datiUtent.dat"));
            prodottiIn = new ObjectInputStream(new FileInputStream("datiProdotti.dat"));
            ordiniIn = new ObjectInputStream(new FileInputStream("datiOrdini.dat"));
        } catch (FileNotFoundException fileEx){
            System.err.println("Errore: " + fileEx.getMessage());
            System.err.println("Files non trovati");
        } catch (IOException ioEx){
            System.err.println("Errore: " + ioEx.getMessage());
            System.err.println("Errore di Input/Output");
        }

        try {
            if (scelta.equals("y")) {
                if (!utenti.isEmpty()) {
                    System.out.println("Eliminazione degli utenti temporanei in corso...");
                } else if (!prodotti.isEmpty()) {
                    System.out.println("Eliminazione dei prodotti temporanei in corso...");
                } else if (!ordini.isEmpty()) {
                    System.out.println("Eliminazione degli ordini temporanei in corso...");
                }
            }

            while (true) {
                Utente u = (Utente) utentiIn.readObject();
                utenti.add(u);
            }
        } catch (EOFException eofException) {
            System.out.println("Caricamento utenti completato!");

            try{
                utentiIn.close();
            }catch (IOException ioEx){
                System.err.println("Errore: " + ioEx.getMessage());
                System.err.println("Errore di Input/Output");
            }
        } catch (ClassNotFoundException classEx){
            System.err.println("Errore: " + classEx.getMessage());
            System.err.println("Classe non trovata");
        } catch (IOException ioEx){
            System.err.println("Errore: " + ioEx.getMessage());
            System.err.println("Errore di Input/Output");
        } catch (NullPointerException nullPtrEx){
            System.err.println("Errore: " +  nullPtrEx.getMessage());
            System.err.println("Oggetto non caricato correttamente");
        }

        try{
            while (true){
                Prodotto p = (Prodotto) prodottiIn.readObject();
                prodotti.add(p);
            }
        } catch (EOFException eofEx){
            System.out.println("Inserimento dati prodotti completato");

            try{
                prodottiIn.close();
            }catch (IOException ioEx){
                System.err.println("Errore: " + ioEx.getMessage());
                System.err.println("Errore di Input/Output");
            }
        }catch (ClassNotFoundException classEx){
            System.err.println("Errore: " + classEx.getMessage());
            System.err.println("Classe non trovata");
        } catch (IOException ioEx){
            System.err.println("Errore: " + ioEx.getMessage());
            System.err.println("Errore di Input/Output");
        } catch (NullPointerException nullPtrEx){
            System.err.println("Errore: " +  nullPtrEx.getMessage());
            System.err.println("Oggetto non caricato correttamente");
        }

        try{
            while (true){
                Ordine o = (Ordine) ordiniIn.readObject();
                ordini.add(o);
            }
        }catch (EOFException eofEx){
            System.out.println("Caricamento degli ordini comletato");

            try{
                ordiniIn.close();
            } catch (IOException ioEx){
                System.err.println("Errore: " + ioEx.getMessage());
                System.err.println("Errore di Input/Output");
            }
        }catch (ClassNotFoundException classEx){
            System.err.println("Errore: " + classEx.getMessage());
            System.err.println("Classe non trovata");
        } catch (IOException ioEx){
            System.err.println("Errore: " + ioEx.getMessage());
            System.err.println("Errore di Input/Output");
        } catch (NullPointerException nullPtrEx){
            System.err.println("Errore: " +  nullPtrEx.getMessage());
            System.err.println("Oggetto non caricato correttamente");
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