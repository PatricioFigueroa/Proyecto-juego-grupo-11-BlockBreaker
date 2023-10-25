package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;
 

public interface Bola {
	   public boolean estaQuieto() ;
	   
	    public void setEstaQuieto(boolean bb);
	    
	    public void setXY(int x, int y);
	    
	    public int getY();
	    
	    public void draw(ShapeRenderer shape) ;
	    

	    public void update() ;
	    

	    public void checkCollision(Paddle paddle);
	    

	    public void checkCollision(Block block) ;
	    

	    public int getXSpeed();
	    

	    public int getYSpeed() ;
	    
	    public int getX() ;
	    
}
