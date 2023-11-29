package com.mygdx.game.CarpetaInterfaces;

public interface Bola extends ColisionElementos, DibujarElementos,MovimientoElementos{
	
	   public boolean estaQuieto() ;
	   
	   public void setEstaQuieto(boolean bb);
	    	    
	   public int getY();   
	    
	   public int getX();

	   public int getXSpeed();
	   
	   public int getYSpeed();
	   
	   public void setXSpeed(int xSpeed);
	   
	   public void setYSpeed(int ySpeed);
       	    
}
