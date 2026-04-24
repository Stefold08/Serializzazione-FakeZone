package org.gruppo3;

public class DettagliOrdine {
    private Prodotto prodotto;
    private double costo;
    private int quantita;

    public DettagliOrdine(Prodotto prodotto, double costo, int quantita){
        this.prodotto = prodotto;
        this.costo = costo;
        this.quantita = quantita;
    }

    public int getQuantita(){
        return quantita;
    }

    public double getCosto(){
        return costo;
    }
}