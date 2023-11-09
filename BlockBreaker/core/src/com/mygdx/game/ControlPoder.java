package com.mygdx.game;

import java.util.Random;

import com.mygdx.game.CarpetaInterfaces.Bola;

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
    
    public Bola activarPoder(int x, int y) { // Utiliza la interfaz Bola
        int numeroAleatorio = random.nextInt(100) + 1;

        // en esta parte se distribuirán los poderes con distintos porcentajes,
        // en este caso, multiplesbolas tiene un 60% de probabilidad de aparición
        if (numeroAleatorio > 90) {
            // Crea la nueva bola de poder (PingBallDoble en este caso, pero podría ser de otro tipo)
            Bola nueva = new PingBallDoble(x, y, 0, 0); // Valores por defecto o según la lógica del juego

            controlBolasEnJuego.agregarBolaEnJuego(nueva);

            return nueva; // Devuelve la nueva bola
        }

        return null; // En caso de que no se cree una nueva bola
    }
}
