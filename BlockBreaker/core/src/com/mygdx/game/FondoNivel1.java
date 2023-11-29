package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Fondo;

public class FondoNivel1 implements Fondo {
    private Sprite sprite;
    public FondoNivel1()
    {
    	sprite = new Sprite(new Texture("fondoJuego.png"));
    	sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }
    @Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
            sprite.draw(batch);
	}
}
