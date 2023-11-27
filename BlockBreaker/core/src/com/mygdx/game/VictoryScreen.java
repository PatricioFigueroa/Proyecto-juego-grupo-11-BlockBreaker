package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class VictoryScreen implements Screen{
    private Sprite fondoVictoria;
    private SpriteBatch batch;
    private SpriteBatch spriteBatch;
    private Screen[] screens;
	private Camera camera; 
    private ControlBotones controlBotones;
    private Screen selectedScreen;
	private final BlockBreakerMenu game;

    public VictoryScreen(BlockBreakerMenu game, GameScreen juego, Camera camera) {
		this.game = game;
        this.batch = game.getBatch();
        fondoVictoria = new Sprite(new Texture("fondoVictoria.png"));
        this.camera = camera; 
        spriteBatch = new SpriteBatch();
        batch = game.getBatch(); // Asegúrate de que esta línea inicialice correctamente "batch"
        screens = new Screen[]{new MainMenuScreen(game)};
        controlBotones = new ControlBotones(new String[]{"Menú", "Salir"}, camera.viewportWidth / 2 - 100, camera.viewportHeight / 2 + 50, 200, 40, screens);
    }

	
    public void renderBackground() {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        fondoVictoria.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fondoVictoria.draw(spriteBatch);
        spriteBatch.end();
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		
        camera.update();
        
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderBackground();
        
        batch.begin();
        controlBotones.draw(batch);    
        batch.end();
        
        selectedScreen = controlBotones.handleInput();
        if (selectedScreen != null) {
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
