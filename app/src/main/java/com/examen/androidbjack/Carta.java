package com.examen.androidbjack;

import android.widget.ImageView;

public class Carta {
    private int palo;
    private int valor;
    private ImageView ive;

    public Carta(int palo, int valor) {
        this.palo = palo;
        this.valor = valor;
    }

    public Carta(int palo, int valor, ImageView resource) {
        this.palo = palo;
        this.valor = valor;
        this.ive = resource;
    }

    public int getPalo() {
        return palo;
    }

    public void setPalo(int palo) {
        this.palo = palo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public ImageView getIve() {
        return ive;
    }

    public void setIve(ImageView ive) {
        this.ive = ive;
    }
}
