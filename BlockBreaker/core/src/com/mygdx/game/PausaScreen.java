package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PausaScreen implements Screen {
	private final BlockBreakerMenu game;
	private GameScreen juego;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;

	public PausaScreen (final BlockBreakerMenu game, GameScreen juego) {
		this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(0, 0, 1.0f, 0.5f);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.draw(batch, "Juego en Pausa ", 100, 150);
		font.draw(batch, "Toca en cualquier lado para continuar !!!", 100, 100);
		batch.end();

        //Salir del juego
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) System.exit(0);
		
		if (Gdx.input.isTouched()) {
			game.setScreen(juego);
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
