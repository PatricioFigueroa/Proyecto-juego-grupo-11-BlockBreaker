package com.mygdx.game;

import java.util.Random;

import com.mygdx.game.CarpetaInterfaces.Paddle;

public abstract class Poderes {
    private Random random = new Random();

    public void ejecutarPoder(PingBall p, Paddle e) {
        int numeroAleatorio = random.nextInt(4); // Número aleatorio entre 0 y 3

        switch (numeroAleatorio) {
            case 0:
                aumentarVelocidad();
                break;
            case 1:
                disminuirVelocidad();
                break;
            case 2:
                aumentarTamaño(p,e);
                break;
            case 3:
                disminuirTamaño();
                break;
            default:
                // Lógica por defecto o manejo de error
                break;
        }
    }

    public abstract void aumentarVelocidad();
    public abstract void disminuirVelocidad();
    public void aumentarTamaño(PingBall p, Paddle e){ 
    	if(p != null)
    	if(p.getSize() < 50)
        {
            p.setSize(p.getSize() + 15) ;
            
        }
    }
    public abstract void disminuirTamaño();
}