package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ControlBolasEnJuego {
    private ArrayList<PingBallDoble> bolasEnJuego;

    public ControlBolasEnJuego() {
        bolasEnJuego = new ArrayList<PingBallDoble>();
    }

    public void agregarBolaEnJuego(PingBallDoble bola) {
        bolasEnJuego.add(bola);
    }
  
    public boolean isEmpty()
    {
    	return bolasEnJuego.isEmpty();
    }
    
    public void colisionPelota(Paddle pad){
	    
	    // Colisiones de las bolas dobles con el paddle
	    for (PingBallDoble bola : bolasEnJuego) {
	        bola.checkCollision(pad);
	     // Lógica de colisión de todas las bolas con los bloques
	        
	    }
    }
    public void update() {
        // Actualiza la posición de las bolas y realiza cualquier otro cálculo necesario
        for (PingBallDoble bola : bolasEnJuego) {
            bola.update();
        }
    }
    
    public void dibujarPelotas(SpriteBatch batch)
    {
    	for (PingBallDoble bola : bolasEnJuego) {
            bola.draw(batch);
        }
    }
    
    public void colisionPelotaBloques( Block block)
    {
    	for (PingBallDoble bola : bolasEnJuego) 
    		bola.checkCollision(block);
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
