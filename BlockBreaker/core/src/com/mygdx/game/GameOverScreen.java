package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
	private final BlockBreakerMenu game;
	private SpriteBatch batch;	   
	private Camera camera; 
	private Sprite fondoGameOver;
	private SpriteBatch spriteBatch;
    private ControlBotones controlBotones;
    private Screen selectedScreen;
    private Screen[] screens;

	public GameOverScreen(final BlockBreakerMenu game, Camera camera) {
		this.game = game;
        this.batch = game.getBatch();
        screens = new Screen[]{new GameScreen(game, camera)};
        this.camera = camera; 
		fondoGameOver = new Sprite(new Texture("fondoGameOver.jpg"));
		spriteBatch = new SpriteBatch();
        spriteBatch = new SpriteBatch();
        controlBotones = new ControlBotones(new String[]{"Reintentar", "Salir"}, camera.viewportWidth / 2 - 100, camera.viewportHeight / 2 + 50, 200, 40, screens);
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
        controlBotones.draw(batch);
		batch.end();
				
        selectedScreen = controlBotones.handleInput();
        if (selectedScreen != null) {
            if (selectedScreen instanceof GameScreen) {
                // Llama al método startMusic() antes de cambiar a GameScreen
                ((GameScreen) selectedScreen).startMusic();
            }
            game.setScreen(selectedScreen);
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
