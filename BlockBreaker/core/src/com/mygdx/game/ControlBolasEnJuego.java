package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Bloque;
import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaInterfaces.Paddle;

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

    public Bola colisionPelotaBloques(Bloque block, Comportamiento comportamiento) {
        boolean colision;
    	for (Bola bola : bolasEnJuego) { // Utiliza la interfaz Bola
        	colision = bola.checkCollision(block, comportamiento);
        	if(colision)
        	{
        		comportamiento.aplicarComportamiento(bola);
        		return bola;
        	}
        }
    	return null;
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

    public void iniciarBolas() {
        for (Bola bola : bolasEnJuego) {
            bola.setEstaQuieto(false);
        }
    }
    

    public void moverPelotaConPaddle(Paddle pad) {
        PingBall ball = (PingBall) bolasEnJuego.get(0);
        if (ball.estaQuieto()) {
            ball.setXY(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                ball.setEstaQuieto(false);
            }
        } else {
            // Mueve la bola con el paddle solo si no está quieta
            ball.setXY(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11);
        }
    }
     

    public boolean estaQuieto()
    {
        PingBall ball = (PingBall) bolasEnJuego.get(0);
        return ball.estaQuieto();
    }

	public PingBall getBolaInicial() {
		Bola bolaAux = bolasEnJuego.get(0);	
		if(bolaAux instanceof PingBall)
			return (PingBall) bolaAux;	
		return null;
	}
    
    public void crearNuevaBola(PingBall bola) {
        agregarBolaEnJuego(bola);
    }
	
}
