package com.mygdx.game;

import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaInterfaces.ComportamientoS;

public class Comportamiento {
	private ComportamientoS comportamiento;
	
	public Comportamiento()
	{
		comportamiento = new MovimientoNormal();
	}
	
    public void setComportamiento(ComportamientoS c) {
		comportamiento = c;
	}

	public void aplicarComportamiento(Bola bola)
    {
    	comportamiento.aplicarPoder(bola);
    }
}
