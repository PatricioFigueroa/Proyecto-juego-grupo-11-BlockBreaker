package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Bola;

public class ControlBolasEnJuego {
    private ArrayList<Bola> bolasEnJuego; // Utiliza la interfaz Bola

    public ControlBolasEnJuego() {
        bolasEnJuego = new ArrayList<Bola>();
        
    }

    public void agregarBolaEnJuego(Bola bola) {
        bolasEnJuego.add(bola);
    }

    public boolean isEmpty() {
        return bolasEnJuego.isEmpty();
    }

    public void colisionPelota(Paddle pad) {
        for (Bola bola : bolasEnJuego) { // Utiliza la interfaz Bola
            bola.checkCollision(pad);
            // Lógica de colisión de todas las bolas con el paddle
        }
    }

    public void update() {
        // Actualiza la posición de las bolas y realiza cualquier otro cálculo necesario
        for (Bola bola : bolasEnJuego) { // Utiliza la interfaz Bola
            bola.update();
        }
    }

    public void dibujarPelotas(SpriteBatch batch) {
        for (Bola bola : bolasEnJuego) {
        	// Utiliza la interfaz Bola
            bola.draw(batch);
        }
    }

    public void colisionPelotaBloques(Block block) {
        for (Bola bola : bolasEnJuego) { // Utiliza la interfaz Bola
            bola.checkCollision(block);
        }
    }

    public void clear() {
        bolasEnJuego.clear();
    }

    public void clearBolasFueraDePantalla() {
        ArrayList<Bola> bolasParaEliminar = new ArrayList<>();

        for (Bola bola : bolasEnJuego) {
            if (bola.getY() <= 0) {
                bolasParaEliminar.add(bola);
            }
        }

        bolasEnJuego.removeAll(bolasParaEliminar);
    }

    public void crearNuevaBola(Paddle pad) {
        Bola nuevaBola = new PingBall(pad);
        agregarBolaEnJuego(nuevaBola);
    }

    public void iniciarBolas() {
        for (Bola bola : bolasEnJuego) {
            bola.setEstaQuieto(false);
        }
    }
}
