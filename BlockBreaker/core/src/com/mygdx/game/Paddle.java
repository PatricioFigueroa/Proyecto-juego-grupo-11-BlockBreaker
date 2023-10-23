package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;
    
    
    public Paddle() {
    	this.x = Gdx.graphics.getWidth()/2-50;
    	this.y= 40;
    	width = 200;
    	height = 20;
    }
     
    public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}

	public void draw(ShapeRenderer shape){
        shape.setColor(Color.RED);
        int x2 = x; //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x2 = x - 15;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x2 = x + 15;
        }
       // y = Gdx.graphics.getHeight() - Gdx.input.getY(); 
        if (x2 > 0 && x2+width < Gdx.graphics.getWidth()) {
            x = x2;
        }
        shape.rect(x, y, width, height);
    }
    
    
}
