package com.mygdx.game;

import java.util.Random;

import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaInterfaces.ComportamientoS;

public class MovimientoAleatorio implements ComportamientoS{

	@Override
	public void aplicarPoder(Bola bola) {
		
		if(bola.getXSpeed() != 0&& bola.getYSpeed() != 0)
		{
	        Random random = new Random();
	        
	        boolean randomX = random.nextBoolean();
	        double randomValue = random.nextInt(2);
	        if (randomX) 
	        	bola.setXSpeed((int) (-bola.getXSpeed() * Math.pow(-1, randomValue)));	
	        else
	        	bola.setXSpeed((int) (bola.getXSpeed() * Math.pow(-1, randomValue)));	
	        bola.setYSpeed((int) (bola.getYSpeed() * Math.pow(-1, randomValue)));
		}
		else
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
