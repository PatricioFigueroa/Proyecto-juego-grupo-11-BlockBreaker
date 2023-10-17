package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen {
	
	final BlockBreakerMenu game;
  private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private ShapeRenderer shape;
	
	private PingBall ball;
	private Paddle pad;
	private ArrayList<Block> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;
	
	private Texture fondo;
	private Sprite fondoReal;
	private SpriteBatch spriteBatch;
	
	
	public GameScreen(final BlockBreakerMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
		camera = new OrthographicCamera();
		
		camera.setToOrtho(false, Gdx.graphics.getWidth()*1.08f, Gdx.graphics.getHeight());
	    //camera.setToOrtho(false);
	    batch = new SpriteBatch();
	    font = new BitmapFont();
	    font.getData().setScale(3, 2);
	    nivel = 1;
	    crearBloques(2+nivel);
	    
	    fondo= new Texture("fondo2.png");
	    fondoReal =new Sprite(fondo);
	    spriteBatch = new SpriteBatch();

		
	    shape = new ShapeRenderer();
	    ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);
	    pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,300,100);
	    vidas = 3;
	    puntaje = 0;  
	}
	
	public void crearBloques(int filas) {
		blocks.clear();
		int blockWidth = 50;
	    int blockHeight = 20;
	    int y = Gdx.graphics.getHeight();
	    for (int cont = 0; cont<filas; cont++ ) {
	    	y -= blockHeight+10;
	    	for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
	            blocks.add(new Block(x, y, blockWidth, blockHeight));
	        }
	    }
	}
	public void dibujaTextos() {
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		
		//dibujar textos
		font.draw(batch, "Puntos: " + puntaje, 10, 25);
		font.draw(batch, "Vidas : " + vidas, Gdx.graphics.getWidth()-20, 25);
		batch.end();
	}	
	
	//Fondo
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
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 		
        shape.begin(ShapeRenderer.ShapeType.Filled);
        
        
       //imagen de fondo 
        renderBackground();

        pad.draw(shape);
        // monitorear inicio del juego
        if (ball.estaQuieto()) {
        	ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
        	if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
        }else ball.update();
        //verificar si se fue la bola x abajo
        if (ball.getY()<0) {
        	vidas--;
        	//nivel = 1;
        	ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
        }
        // verificar game over
        if (vidas<=0) {
        	game.setScreen(new GameOverScreen(game));
	    	dispose();
        	//ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);	        	
        }
        // verificar si el nivel se terminó
        if (blocks.size()==0) {
        	nivel++;
        	crearBloques(2+nivel);
        	ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
        }    	
        //dibujar bloques
        for (Block b : blocks) {        	
            b.draw(shape);
            ball.checkCollision(b);
        }
        // actualizar estado de los bloques 
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.destroyed) {
            	puntaje++;
                blocks.remove(b);
                i--; //para no saltarse 1 tras eliminar del arraylist
            }
        }
        
        //Pausar juego
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) pause();
        
        ball.draw(shape);
        ball.checkCollision(pad);
        shape.end();
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
