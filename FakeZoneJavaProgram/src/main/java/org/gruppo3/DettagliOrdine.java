package org.gruppo3;

import java.io.Serializable;

public class DettagliOrdine implements Serializable{
    private static final long serialVersionUID = 1;
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

    @Override
    public String toString(){
        return "Prodotto: " + prodotto.getDettagliProdottoOrdine() + "\nCosto: " + costo + "\nQuantità: " + quantita;
    }
}