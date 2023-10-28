package com.mygdx.game;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public interface Bola extends ColisionElementos, DibujarElementos,MovimientoElementos{
	   public boolean estaQuieto() ;
	   
	    public void setEstaQuieto(boolean bb);
	    	    
	    public int getY();
	    
	    public void draw(SpriteBatch batch) ;	    

	    public void update() ;	    

	    public void checkCollision(Paddle paddle);	    

	    public void checkCollision(Block block) ;	    
	    	    
}
