package org.gruppo3;

import java.time.LocalDate;

public class Utente {
    private String pasword;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private char sesso;
    private String codiceFiscale;
    private String email;
    private String indirizzo;
    private String numeroTel;

    private Utente(String pasword, String nome, String cognome, LocalDate dataNascita, char sesso, String codiceFiscale, String email, String indirizzo, String numeroTel){
        this.pasword = pasword;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.indirizzo = indirizzo;
        this.numeroTel = numeroTel;
    }
}

