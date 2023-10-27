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

public class PausaScreen implements Screen {
	private final BlockBreakerMenu game;
	private GameScreen juego;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
	private Sprite fondoPausa;
	private SpriteBatch spriteBatch;

	public PausaScreen (final BlockBreakerMenu game, GameScreen juego) {
		this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
		fondoPausa = new Sprite(new Texture("fondoPausa.png"));
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
        fondoPausa.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Ajusta el tamaño de la imagen al de la pantalla
        fondoPausa.draw(spriteBatch);
        spriteBatch.end();
    }

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(0, 0, 0, 0);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
        renderBackground();
		batch.begin();
		font.draw(batch, "Juego en Pausa ", 100, 150);
		font.draw(batch, "Toca en cualquier lado para continuar !!!", 100, 100);
		batch.end();
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) System.exit(0);
		
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
