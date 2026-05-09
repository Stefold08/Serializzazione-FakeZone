package org.gruppo3;

import java.time.LocalDate;
import java.io.Serializable;

public class Utente implements Serializable{
    private static final long serialVersionUID = 1;
    private String password;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String sesso;
    private String codiceFiscale;
    private String email;
    private String indirizzo;
    private String numeroTel;

    public Utente(String pasword, String nome, String cognome, LocalDate dataNascita, String sesso, String codiceFiscale, String email, String indirizzo, String numeroTel){
        this.password = pasword;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.indirizzo = indirizzo;
        this.numeroTel = numeroTel;
    }

    @Override
    public String toString(){
        return "Dati utente: " + "\n nome: " + nome + "\n cognome: " + cognome + "\n codice fiscale: " + codiceFiscale + "\n sesso: " + sesso + "\n la password e': " + password + "\n email: " + email + "\n indirizzo di casa: " + indirizzo + "\n data di nascita: " + dataNascita + "\n numero di tel: " + numeroTel;
    }

    public String getCodiceFiscale(){
        return codiceFiscale;
    }

    public String getEmail(){
        return email;
    }

    public String getNumeroTel(){
        return numeroTel;
    }
}

