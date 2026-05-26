package org.gruppo3;

import java.io.Serializable;
import java.time.LocalDate;

public class Recenzione implements Serializable {
    private static final long serialVersionUID = 1;
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
}
