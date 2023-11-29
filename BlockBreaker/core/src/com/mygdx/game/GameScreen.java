package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CarpetaInterfaces.Fondo;

public class GameScreen implements Screen {

    private final BlockBreakerMenu game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Camera camera; 
    private Fondo fondo;

    private Control controlador;
    private Music music;
    
    public GameScreen(final BlockBreakerMenu game, Camera camera) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        this.camera = camera; 

        

        // Inicializa el controlador
        controlador = new Control();
    }

    public void dibujaTextos() {
        // Actualizar matrices de la cámara
        camera.update();
        // Actualizar
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        font.getData().setScale(5.0f);
        
        GlyphLayout vidaLayout = new GlyphLayout(font, "Vidas : " + controlador.getVidas());
        GlyphLayout puntosLayout = new GlyphLayout(font, "Puntos: " + controlador.getPuntaje());
        
        float puntosX = 10;
        float vidasX = Math.max(10, Gdx.graphics.getWidth() - 10 - vidaLayout.width);
        
        float vidasY = 10 + vidaLayout.height;
        float PuntosY = 10 + puntosLayout.height;

        // Dibujar textos
        font.draw(batch, "Puntos: " + controlador.getPuntaje(), puntosX, PuntosY);
        font.draw(batch, "Vidas : " + controlador.getVidas(), vidasX, vidasY);
        batch.end();
    }
    
    public void startMusic() {
        this.music = Gdx.audio.newMusic(Gdx.files.internal("cancion juego.mp3"));
        this.music.setLooping(true);
        this.music.setVolume(0.1f); 
        this.music.play();
    }

    // Fondo
    

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
        controlador.renderBackground(batch);

        // Dibujar Tabla
        controlador.dibujarTabla();

        // Monitorear inicio del juego
        controlador.monitorearInico();

        // Verificar si se fue la bola x abajo
        controlador.verificarPelotar();

        // Verificar game over
        if (controlador.isGameOver()) {
            // Destruir
            music.dispose();
            dispose();
            game.setScreen(new GameOverScreen(game, camera));
        }
        
        if (controlador.ifGameComplete()) {
            music.dispose();
            game.setScreen(new VictoryScreen(game, this, camera));
        }

        // Verificar si el nivel se terminó
        if (controlador.isNivelCompleto()) {
            controlador.avanzarNivel(this);
        }

        // Dibujar bloques
        controlador.dibujarBloques();

        // Actualizar estado de los bloques
        controlador.actualizarBloques();

        // Pausar juego
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            pause();
        	
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
    	music.pause();
    	game.setScreen(new PausaScreen(game, this, camera));
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    	music.play();
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    	music.dispose();
    }
}