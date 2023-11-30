package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
	private final BlockBreakerMenu game;
    private SpriteBatch batch;
    private Camera camera; 
    private ControlBotones controlBotones;
    private Sprite fondoMenu;
    private SpriteBatch spriteBatch;
    private Screen[] screens;
    private Screen selectedScreen;

    public MainMenuScreen(final BlockBreakerMenu game) {
    	this.game = game;
        this.batch = game.getBatch();
        game.getFont();
        fondoMenu = new Sprite(new Texture("fondoMenuInicial.png"));
        spriteBatch = new SpriteBatch();
        camera = Camera.getInstance(); 
        screens = new Screen[]{new GameScreen(game, Camera.getInstance())};
        controlBotones = new ControlBotones(new String[]{"Jugar", "Salir"}, camera.viewportWidth / 2 - 100, camera.viewportHeight / 2 + 50, 200, 40, screens);
    }

    public void renderBackground() {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        fondoMenu.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fondoMenu.draw(spriteBatch);
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
        batch.setProjectionMatrix(camera.combined);
        renderBackground();
        batch.begin();

        // Renderizar los botones usando ControlBotones
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
