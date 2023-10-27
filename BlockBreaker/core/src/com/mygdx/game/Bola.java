package com.mygdx.game;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

 

public interface Bola {
	   public boolean estaQuieto() ;
	   
	    public void setEstaQuieto(boolean bb);
	    
	    public int getY();
	    
	    public void draw(ShapeRenderer shape) ;	    

	    public void update() ;	    

	    public void checkCollision(Paddle paddle);	    

	    public void checkCollision(Block block) ;	    
}
