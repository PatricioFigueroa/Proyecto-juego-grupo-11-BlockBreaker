package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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
    
    public void dibujarPelotas(ShapeRenderer shape)
    {
    	for (PingBallDoble bola : bolasEnJuego) {
            bola.draw(shape);
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
