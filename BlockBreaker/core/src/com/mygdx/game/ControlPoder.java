package com.mygdx.game;

import java.util.Random;

public class ControlPoder {
    private ControlBolasEnJuego controlBolasEnJuego;
    private Random random;

    public ControlPoder(ControlBolasEnJuego controlBolasEnJuego) {
        this.controlBolasEnJuego = controlBolasEnJuego;
        this.random = new Random();
    }
    
    public ControlPoder() {
        this.random = new Random();
    }
    

    public PingBallDoble activarPoder(PingBall so, int x, int y) {
        int numeroAleatorio = random.nextInt(100) + 1;

        if (numeroAleatorio > 80) {
            // Calcula las velocidades inversas
            int nuevoXSpeed = -so.getXSpeed();
            int nuevoYSpeed = -so.getYSpeed();

            PingBallDoble nuevo = new PingBallDoble(x, y, nuevoXSpeed, nuevoYSpeed);
            controlBolasEnJuego.agregarBolaEnJuego(nuevo);

            return nuevo; // Devuelve la nueva bola
        }

        return null; // En caso de que no se cree una nueva bola
    }


}
