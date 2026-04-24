package org.gruppo3;

import java.time.LocalDate;

// DA IMPLEMENTARE + AVANTI!!!
public class Recenzione {
    private int stelle;
    private LocalDate dataRecenzione;
    private Utente utente;
    private String descrizzione;

    public Recenzione(int stelle, LocalDate dataRecenzione, Utente utente, String descrizzione){
        this.stelle = stelle;
        this.dataRecenzione = dataRecenzione;
        this.utente = utente;
        this.descrizzione = descrizzione;
    }


}
