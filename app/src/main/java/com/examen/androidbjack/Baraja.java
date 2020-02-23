package com.examen.androidbjack;


import android.widget.ImageView;

import java.util.ArrayList;

public class Baraja {
    public static ArrayList<Carta> crear(){
        ArrayList<Carta> baraja = new ArrayList<>();
        for(int palo = 0;palo<4;palo++){
            for(int valor = 0;valor<13;valor++){

            }
        }
        return baraja;
    }

    public static ArrayList<Carta> barajar(ArrayList<Carta> baraja ){
        for(int actual = 0;actual<baraja.size();actual++){
            Carta temp = baraja.get(actual);
            int nuevapos = (int) Math.random()*baraja.size();
            baraja.set(actual,baraja.get(nuevapos));
            baraja.set(nuevapos,temp);
        }
        return baraja;
    }


}
