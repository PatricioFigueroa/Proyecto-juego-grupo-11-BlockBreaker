package com.mygdx.game;

import java.util.Random;

public abstract class Poderes {
    private Random random = new Random();

    public void ejecutarPoder() {
        int numeroAleatorio = random.nextInt(4); // Número aleatorio entre 0 y 3

        switch (numeroAleatorio) {
            case 0:
                aumentarVelocidad();
                break;
            case 1:
                disminuirVelocidad();
                break;
            case 2:
                aumentarTamaño();
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
    public void aumentarTamaño(){ 
    	
    }
    public abstract void disminuirTamaño();
}