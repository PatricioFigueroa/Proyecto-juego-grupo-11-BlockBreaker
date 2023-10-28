package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
	private final BlockBreakerMenu game;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
	private Sprite fondoGameOver;
	private SpriteBatch spriteBatch;

	public GameOverScreen(final BlockBreakerMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
		fondoGameOver = new Sprite(new Texture("fondoGameOver.jpg"));
		spriteBatch = new SpriteBatch();
        spriteBatch = new SpriteBatch();
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	
	   // Fondo
	public void renderBackground() {
	     spriteBatch.setProjectionMatrix(camera.combined);
	     spriteBatch.begin();
	     fondoGameOver.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Ajusta el tamaño de la imagen al de la pantalla
	     fondoGameOver.draw(spriteBatch);
	     spriteBatch.end();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		renderBackground();
		
		batch.begin();
		font.draw(batch, "Toca en cualquier lado para reiniciar.", 100, 100);
		batch.end();
		
        //Salir del juego
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) System.exit(0);

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
