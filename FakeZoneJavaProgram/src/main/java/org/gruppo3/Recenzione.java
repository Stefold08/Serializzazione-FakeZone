package org.gruppo3;

import java.time.LocalDate;

public class Recenzione {
    private int stelle;
    private LocalDate dataRecenzione;
    private Utente utente;
    private String descrizione;

    public Recenzione(int stelle, LocalDate dataRecenzione, Utente utente, String descrizione){
        this.stelle = stelle;
        this.dataRecenzione = dataRecenzione;
        this.utente = utente;
        this.descrizione = descrizione;
    }

    @Override
    public String toString(){
        return utente.toString() + "\nData recensione: " + dataRecenzione + "\nValutazione: " + stelle + "\nCommento:\n" + descrizione;
    }

    public int getStelle(){
        return stelle;
    }

    public LocalDate getDataRecenzione(){
        return dataRecenzione;
    }

    public String getDescrizione(){
        return descrizione;
    }

    public Utente getUtente(){
        return utente;
    }
}
