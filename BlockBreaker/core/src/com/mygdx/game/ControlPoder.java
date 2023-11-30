package com.mygdx.game;

import java.util.Random;

import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaInterfaces.Paddle;

public class ControlPoder {
    private ControlBolasEnJuego controlBolasEnJuego;
    private Random random;
    private Poderes poderTabla;
    private Poderes poderPelota;

    public ControlPoder(ControlBolasEnJuego controlBolasEnJuego) {
        this.controlBolasEnJuego = controlBolasEnJuego;
        this.random = new Random();
        }
    
    public ControlPoder() {
        this.random = new Random();
    }
    
    public Bola activarPoder(int x, int y, Paddle pad) { // Utiliza la interfaz Bola
        int numeroAleatorio = random.nextInt(100) + 1;

        // en esta parte se distribuirán los poderes con distintos porcentajes,
        if (numeroAleatorio > 0 && numeroAleatorio<33) {
            // Crea la nueva bola de poder (PingBallDoble en este caso, pero podría ser de otro tipo)
            Bola nueva = new PingBallDoble(x, y); // Valores por defecto o según la lógica del juego
            controlBolasEnJuego.agregarBolaEnJuego(nueva);

            return nueva; // Devuelve la nueva bola
        } else if(numeroAleatorio >=33  && numeroAleatorio<66) {
        	poderTabla.ejecutarPoder();
        }else {
        	poderPelota.ejecutarPoder();
        }

        return null; // En caso de que no se cree una nueva bola
    }
}
