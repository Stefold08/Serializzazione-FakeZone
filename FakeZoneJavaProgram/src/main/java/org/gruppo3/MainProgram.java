package org.gruppo3;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.time.LocalDate;

public class MainProgram {

    private static Azienda azienda;

    static void main(String[] args){
        int scelta = 0;

        do{
            scelta = menu();

            switch (scelta){
                case 1:
                    aggiungiUtente();
                    break;
                case 2:
                    visualizzaDatiUtente();
                    break;
                case 3:
                    aggiungiProdotto();
                    break;
                case 4:
                    informazioniProdotto();
                    break;
                case 5:
                    insericiOrdine();
                    break;
                case 6:
                    informazioniOrdine();
                    break;
                case 7:
                    modificaStatoOrdine();
                    break;
                case 8:
                    visualizzaIncassoPeriodo();
                    break;
                case 9:
                    esporta();
                    break;
                case 10:
                    importa();
                    break;
                case 0:
                    System.out.println("Uscita dal programma in corso...");
                    break;
                default:
                    System.out.println("Scelta non valida!");
                    break;
            }
        }while (scelta != 0);
    }

    private static int menu(){
        int scelta = 0;
        Scanner in = new Scanner(System.in);

        try{
            System.out.println("Menu");
            System.out.println("1) Inserisci dati utente");
            System.out.println("2) Visualizza dati utente");
            System.out.println("3) Inserisci nuovo prodotto");
            System.out.println("4) Visualizza dati prodotto");
            System.out.println("5) Ordina un prodotto");
            System.out.println("6) Visualizza informazioni sull'ordine");
            System.out.println("7) Modifica lo stato di un ordine");
            System.out.println("8) Visualizza l'incasso di un periodo");
            System.out.println("9) Esporta tutti i dati");
            System.out.println("10) Importa dati");
            System.out.println("0) Esci");
            System.out.print("Scelta: ");
            scelta = in.nextInt();

            return scelta;
        }catch (InputMismatchException inEx){
            System.err.println("Errore: " + inEx.getMessage());
            System.err.println("Input non valido");
            return -1;
        }
    }

    private static void aggiungiUtente(){
        Scanner in = new Scanner(System.in);
        String nome, cognome, sesso, password, codiceFiscale, email, indirizzo, numeroTelefono;
        LocalDate dataNascita;

        System.out.print("Nome: ");
        nome = in.nextLine();
        System.out.print("Cognome: ");
        cognome = in.nextLine();
        System.out.print("Sesso: ");
        sesso = in.nextLine();
        System.out.print("Data di nascita (MM/GG/AAAA): ");
        dataNascita = LocalDate.parse(in.nextLine());
        System.out.print("Codice fiscale: ");
        codiceFiscale = in.nextLine();
        System.out.print("Indirizzo/Via: ");
        indirizzo = in.nextLine();
        System.out.print("Numero di telefono: ");
        numeroTelefono = in.nextLine();
        System.out.print("Email: ");
        email = in.nextLine();
        System.out.print("Password: ");
        password = in.nextLine();

        azienda.addUtente(new Utente(password, nome, cognome, dataNascita, sesso, codiceFiscale, email, indirizzo, numeroTelefono));
    }

    private static void visualizzaDatiUtente(){
        Scanner in = new Scanner(System.in);
        String email, codiceFiscale, numeroTelefono;

        System.out.println("Ricerca utente (puoi lasciare campi vuoti)");
        System.out.print("Email: ");
        email = in.nextLine();
        System.out.print("Codice fiscale: ");
        codiceFiscale = in.nextLine();
        System.out.print("Numero di telefono: ");
        numeroTelefono = in.nextLine();

        azienda.visualizzaDatiUtente(codiceFiscale, email, numeroTelefono);
    }

    private static void aggiungiProdotto(){
        Scanner in = new Scanner(System.in);
        String codice, nomeProdotto, descrizione, scelta = null;
        double costo;
        Prodotto prodotto;

        System.out.print("Codice: ");
        codice = in.nextLine();
        System.out.print("Nome: ");
        nomeProdotto = in.nextLine();
        System.out.print("Costo: ");
        costo = in.nextDouble();
        in.nextLine();
        System.out.print("Descrizione: ");
        descrizione = in.nextLine();

        prodotto = new Prodotto(codice, descrizione, nomeProdotto, costo);

        do{
            System.out.print("Percorco immagine: ");
            prodotto.aggiungiImmagine(in.nextLine());
            System.out.print("Vuoi aggiungere un altra immagine? (y/n): ");
            scelta = in.nextLine();
        }while (scelta.equals("y"));

        azienda.addProdotto(prodotto);
    }

    private static void informazioniProdotto(){
        Scanner in = new Scanner(System.in);
        String codice;

        System.out.print("Codice prodotto: ");
        codice = in.nextLine();

        azienda.visualizzaDatiProdotto(codice);
    }

    private static void insericiOrdine(){
        Scanner in = new Scanner(System.in);
        Utente user;
        Prodotto product;
        Ordine ordine;
        DettagliOrdine dettagli;
        LocalDate dataOrdine;
        int numeroOrdine, quantita;
        double costoProdotto;
        String scelta = null;

        if (!ricercaUtente().equals(null)){
            user = ricercaUtente();
        }else{
            System.out.println("Impossibile ottenere l'utente!");
            return;
        }

        System.out.print("Data ordine (MM/GG/AAAA): ");
        dataOrdine = LocalDate.parse(in.nextLine());
        numeroOrdine = azienda.generaNumeroOrdine();

        ordine = new Ordine(dataOrdine, user, numeroOrdine);

        do{
            product = ricercaProdotto();
            if (!product.equals(null)){
                costoProdotto = product.getCosto();

                System.out.print("Quantità: ");
                quantita = in.nextInt();

                dettagli = new DettagliOrdine(product, costoProdotto, quantita);
                ordine.setDettagliProdotti(dettagli);
                System.out.print("Vuoi aggiungere un altro prodotto? (y/n): ");
                scelta = in.nextLine();
            }
        }while (scelta.equals("y"));

        azienda.addOrdine(ordine);
    }

    private static void modificaStatoOrdine(){
        Scanner in = new Scanner(System.in);
        String stato;
        int numeroOrdine;

        System.out.print("Numero ordine: ");
        numeroOrdine = in.nextInt();
        in.nextLine();
        System.out.print("Nuovo stato ordine: ");
        stato = in.nextLine();

        azienda.modificaStatoOrdine(stato, numeroOrdine);
    }

    private static void informazioniOrdine(){
        Scanner in = new Scanner(System.in);
        int numeroOrdine;

        System.out.print("Numero ordine: ");
        numeroOrdine = in.nextInt();

        azienda.visualizzaDatiOrdine(numeroOrdine);
    }

    private static void visualizzaIncassoPeriodo(){
        Scanner in = new Scanner(System.in);
        LocalDate dataInizio, dataFine;

        System.out.print("Data inizio (MM/GG/AAAA): ");
        dataInizio = LocalDate.parse(in.nextLine());
        System.out.print("Data fine (MM/GG/AAAA): ");
        dataFine = LocalDate.parse(in.nextLine());

        azienda.visulizzaIncasssoPeriodo(dataInizio, dataFine);
    }

    private static Utente ricercaUtente(){
        Scanner in = new Scanner(System.in);
        String email, codiceFiscale, numeroTelefono;

        System.out.println("Ricerca utente (puoi lasciare campi vuoti)");
        System.out.print("Email: ");
        email = in.nextLine();
        System.out.print("Codice fiscale: ");
        codiceFiscale = in.nextLine();
        System.out.print("Numero di telefono: ");
        numeroTelefono = in.nextLine();

        return azienda.getUtente(email, codiceFiscale, numeroTelefono);
    }

    private static Prodotto ricercaProdotto(){
        Scanner in = new Scanner(System.in);
        String codice;

        System.out.print("Codice prodotto: ");
        codice = in.nextLine();

        return azienda.getProdotto(codice);
    }
}