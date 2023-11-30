package com.mygdx.game;


import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaInterfaces.ComportamientoS;

public class MovimientoNormal implements ComportamientoS{

	@Override
	public void aplicarPoder(Bola bola) {	
		if(bola.getXSpeed() == 0 && bola.getYSpeed() == 0)
		{
	        if (bola instanceof PingBall) {
	            bola.setXSpeed(10);
	            bola.setYSpeed(14);
	        } else if (bola instanceof PingBallDoble) {
	        	bola.setXSpeed((int) (6*(1.2)));
	            bola.setYSpeed((int) (6*(1.2)));
	        }
		}
		
		
	}

}
