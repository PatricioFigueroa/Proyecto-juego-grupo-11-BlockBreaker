package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
	
	final BlockBreakerMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private int selectedOption; // Opción seleccionada en el menú (0 para Jugar, 1 para Salir)
	private String[] menuOptions = {"Jugar", "Salir"}; // Opciones del menú
	private Sprite fondoMenu;
	private SpriteBatch spriteBatch;

	public MainMenuScreen(final BlockBreakerMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		selectedOption = 0; // Inicialmente seleccionar la primera opción
		fondoMenu = new Sprite(new Texture("fondoMenuInicial.png"));
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
        fondoMenu.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Ajusta el tamaño de la imagen al de la pantalla
        fondoMenu.draw(spriteBatch);
        spriteBatch.end();
    }

	@Override
	public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderBackground();
        batch.begin();

        // Calcula la posición x central para el texto
        GlyphLayout layout = new GlyphLayout(); // Objeto para calcular las dimensiones del texto
        layout.setText(font, menuOptions[selectedOption]); // Establece el texto y la fuente
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;

        font.getData().setScale(2, 2);
        for (int i = 0; i < menuOptions.length; i++) {
            if (i == selectedOption) {
                font.setColor(1, 1, 0, 1); // Resalta la opción seleccionada
            } else {
                font.setColor(1, 1, 1, 1);
            }
            // Dibuja el texto centrado horizontalmente
            font.draw(batch, menuOptions[i], x, camera.viewportHeight / 2 + 50 - 50 * i);
        }
	    batch.end();

	    // Cambiar la opción seleccionada con las flechas
	    if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
	        selectedOption = (selectedOption + 1) % menuOptions.length;
	    } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
	        selectedOption = (selectedOption - 1 + menuOptions.length) % menuOptions.length;
	    }

	    // Seleccionar opción al presionar Enter
	    if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
	        if (selectedOption == 0) {
	            game.setScreen(new GameScreen(game));
	            dispose();
	        } else if (selectedOption == 1) {
	            Gdx.app.exit(); // Salir del juego
	        }
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
