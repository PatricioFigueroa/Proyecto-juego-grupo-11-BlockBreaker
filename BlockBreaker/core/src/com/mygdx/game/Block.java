package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block {
    private int x,y,width,height;
    private boolean destroyed;
    private Sprite sprite;
    
    public Block(int x, int y, int width, int height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = false;
        sprite = new Sprite(texture);
        sprite.setBounds(x, y, width, height);
  
    }
    
    public void draw(SpriteBatch batch) {
        if (!destroyed) {
            sprite.draw(batch);
        }
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
}