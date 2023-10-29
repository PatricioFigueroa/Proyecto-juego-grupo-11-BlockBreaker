package com.mygdx.game.CarpetaInterfaces;

public interface Bola extends ColisionElementos, DibujarElementos,MovimientoElementos{
	
	   public boolean estaQuieto() ;
	   
	    public void setEstaQuieto(boolean bb);
	    	    
	    public int getY();   
	    
	    public int getX();
       	    
}
