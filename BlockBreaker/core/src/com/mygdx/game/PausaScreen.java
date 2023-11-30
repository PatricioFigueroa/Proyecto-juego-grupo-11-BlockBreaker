package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PausaScreen implements Screen {
	private final BlockBreakerMenu game;
	private SpriteBatch batch;	   
    private Screen[] screens;
    private Camera camera; 
	private Sprite fondoPausa;
	private SpriteBatch spriteBatch;
    private ControlBotones controlBotones;
    private Screen selectedScreen;

	public PausaScreen (BlockBreakerMenu game, GameScreen juego, Camera camera) {
		this.game = game;
        this.batch = game.getBatch();
        screens = new Screen[]{juego, new MainMenuScreen(game)};
        this.camera = camera; 
		fondoPausa = new Sprite(new Texture("fondoPausa.png"));
		spriteBatch = new SpriteBatch();
		controlBotones = new ControlBotones(new String[]{"Continuar", "Menú", "Salir"}, camera.viewportWidth / 2 - 100, camera.viewportHeight / 2 + 50, 200, 40, screens);
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
        
        // Renderizar los botones usando ControlBotones
        batch.begin();
        controlBotones.draw(batch);
        batch.end();
        
        selectedScreen = controlBotones.handleInput();
        if (selectedScreen != null) {
            if (selectedScreen instanceof GameScreen) {
                // Llama al método startMusic() antes de cambiar a GameScreen
                ((GameScreen) selectedScreen).resume();
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
