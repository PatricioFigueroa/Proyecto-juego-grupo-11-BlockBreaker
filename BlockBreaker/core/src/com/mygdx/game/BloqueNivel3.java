package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Bloque;

public class BloqueNivel3 implements Bloque {
    private int x,y,width,height;
    private boolean destroyed;
    private Sprite sprite;
    public BloqueNivel3()
    {
        this.destroyed = false;
    }
	public BloqueNivel3(int x, int y, int width, int height) {
        this.x = x + 5;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = false;
        sprite = new Sprite(new Texture ("bloque3.png"));
        sprite.setBounds(this.x, this.y, this.width, this.height);
  
    }
	public boolean getDestroyed()
    {
    	return this.destroyed;
    }
    
    public void setDestroyed()
    {
    	this.destroyed = true;
    }
    
    public int getX()
    {
    	return this.x;
    }
    
    public int getY()
    {
    	return this.y;
    }
    
    public int getWidth()
    {
    	return this.width;
    }
    
    public int getHeight()
    {
    	return this.height;
    }


	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
       
            sprite.draw(batch);
	}

}
