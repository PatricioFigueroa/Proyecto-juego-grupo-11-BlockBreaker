package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Fondo;

public class FondoNivel2 implements Fondo {
	private Sprite sprite;
    public FondoNivel2()
    {
    	sprite = new Sprite(new Texture("fondo2.png"));
    }
    @Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
       
            sprite.draw(batch);
	}

}
