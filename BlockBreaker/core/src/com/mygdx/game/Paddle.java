package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;
    private Texture texture;
    
    public Paddle() {
    	this.x = Gdx.graphics.getWidth()/2-50;
    	this.y= 40;
    	width = 200;
    	height = 20;
    	texture = new Texture("paddle.png");
    }
     
    public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}

    public void draw(SpriteBatch batch) {
		batch.draw(texture, x, y, width, height);
		
        int x2 = x;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x2 = x - 15;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x2 = x + 15;
        }
        if (x2 > 0 && x2 + width < Gdx.graphics.getWidth()) {
            x = x2;
        }
    }
    
    
}
