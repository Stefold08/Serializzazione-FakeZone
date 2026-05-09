package org.gruppo3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainProgram {

    private static Azienda azienda;

    static void main(String[] args){

    }

    private int menu(){
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
}