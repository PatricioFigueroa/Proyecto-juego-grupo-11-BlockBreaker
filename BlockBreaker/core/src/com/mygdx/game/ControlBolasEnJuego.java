package com.mygdx.game;

import java.util.ArrayList;

public class ControlBolasEnJuego {
    private ArrayList<PingBallDoble> bolasEnJuego;

    public ControlBolasEnJuego() {
        bolasEnJuego = new ArrayList<PingBallDoble>();
    }

    public void agregarBolaEnJuego(PingBallDoble bola) {
        bolasEnJuego.add(bola);
    }

    public void imprimirTiposDeBolas() {
        for (PingBallDoble bola : bolasEnJuego) {
            System.out.println("Tipo de bola: " + bola.getClass().getName());
        }
    }

    public ArrayList<PingBallDoble> getBolasEnJuego() {
        return bolasEnJuego;
    }

    public boolean isEmpty()
    {
    	return bolasEnJuego.isEmpty();
    }
    
    public void clear() {
        bolasEnJuego.clear();
    }

    public void clearBolasFueraDePantalla() {
        ArrayList<PingBallDoble> bolasParaEliminar = new ArrayList<>();
        
        for (PingBallDoble bola : bolasEnJuego) {
            if (bola.getY() <= 0) {
                bolasParaEliminar.add(bola);
            }
        }
        
        bolasEnJuego.removeAll(bolasParaEliminar);
    }

    
}
