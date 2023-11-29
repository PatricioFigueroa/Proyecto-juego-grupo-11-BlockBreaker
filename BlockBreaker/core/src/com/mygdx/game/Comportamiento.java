package com.mygdx.game;

import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaInterfaces.ComportamientoS;

public class Comportamiento {
	private ComportamientoS comportamiento;
	
    public void setComportamiento(ComportamientoS c) {
		comportamiento = c;
	}

	public void aplicarComportamiento(Bola bola)
    {
    	comportamiento.aplicarPoder(bola);
    }
}
