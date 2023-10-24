package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    final BlockBreakerMenu game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture fondo;
    private Sprite fondoReal;
    private SpriteBatch spriteBatch;

    private Control controlador;

    public GameScreen(final BlockBreakerMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth() * 1.08f, Gdx.graphics.getHeight());

        fondo = new Texture("fondo2.png");
        fondoReal = new Sprite(fondo);
        spriteBatch = new SpriteBatch();

        // Inicializa el controlador
        controlador = new Control();

    }

    public void dibujaTextos() {
        // Actualizar matrices de la cámara
        camera.update();
        // Actualizar
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Dibujar textos
        font.draw(batch, "Puntos: " + controlador.getPuntaje(), 10, 25);
        font.draw(batch, "Vidas : " + controlador.getVidas(), Gdx.graphics.getWidth() - 20, 25);
        batch.end();
    }

    // Fondo
    public void renderBackground() {
        spriteBatch.begin();
        fondoReal.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Color del fondo
        ScreenUtils.clear(0f, 3f / 255, 52f / 255, 255f);

        // Imagen de fondo
        renderBackground();

        //Dibujar Tabla
        controlador.dibujarTabla();

        // Monitorear inicio del juego
        controlador.monitorearInico();

        // Verificar si se fue la bola x abajo
        controlador.verificarPelotar();


        // Verificar game over
        if (controlador.isGameOver()) {
            game.setScreen(new GameOverScreen(game));
            dispose();
        }

        // Verificar si el nivel se terminó
        if (controlador.isNivelCompleto()) {
            controlador.avanzarNivel();
        }

        // Dibujar bloques
        controlador.dibujarBloques();

        // Actualizar estado de los bloques
        controlador.actualizarBloques();

        // Pausar juego
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) pause();

        // Dibujar pelota
        controlador.dibujarPelota();
        // Verificar colisiones de la pelota
        controlador.colisionPelota();

        dibujaTextos();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        game.setScreen(new PausaScreen(game, this));
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

