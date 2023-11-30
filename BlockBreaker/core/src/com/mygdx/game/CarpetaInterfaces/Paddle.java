package com.mygdx.game.CarpetaInterfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Paddle {
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public void draw(SpriteBatch batch);

}
