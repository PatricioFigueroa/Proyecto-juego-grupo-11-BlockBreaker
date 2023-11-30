package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Paddle;

public class PaddleNivel3 extends Poderes implements Paddle {
	private int x;
    private int y;
    private int width;
    private int height;
    private int velocidad;
    private Texture texture;
    
    public PaddleNivel3() {
    	this.x = Gdx.graphics.getWidth()/2-50;
    	this.y= 40;
    	width = 200;
    	velocidad = 15;
    	height = 20;
    	texture = new Texture("paddle3.png");
    }
     
    public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
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

	@Override
	public void aumentarVelocidad() {
		// TODO Auto-generated method stub
		if(velocidad <=20)
			velocidad += 1;
	}

	@Override
	public void disminuirVelocidad() {
		// TODO Auto-generated method stub
		if(velocidad >=10)
			velocidad -= 1;
		
	}

	@Override
	public void disminuirTamaño() {
		// TODO Auto-generated method stub
		if(width >= 150)
			width-=10;
	}

	@Override 
	public void aumentarTamaño(PingBall b, Paddle p) {
		if(width <= 250)
			width+=50;
	}
}
