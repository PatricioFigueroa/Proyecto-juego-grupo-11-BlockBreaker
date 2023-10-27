package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.CarpetaNiveles.*;

public class Control {
    private ArrayList<Niveles> niveles;
    private ArrayList<Block> blocks;
    private int vidas = 3;
    private int puntaje = 0;
    private int indiceNivel;
    private ControlPoder controlPoder;
    private PingBall ball;
    private Paddle pad;
    private ShapeRenderer shape;
    private SpriteBatch batch;
    private ControlBolasEnJuego controlBolasEnJuego;

     public Control() {
        niveles = new ArrayList<>();
            indiceNivel = 0;
            niveles.add(new Nivel1(1));
            niveles.add(new Nivel2(2));

            // Inicializa controlBolasEnJuego
            controlBolasEnJuego = new ControlBolasEnJuego();

            // Crea una instancia de ControlPoder y pasa controlBolasEnJuego
            controlPoder = new ControlPoder(controlBolasEnJuego);

            batch = new SpriteBatch();
            shape = new ShapeRenderer();
            pad = new Paddle();
            ball = new PingBall(pad);
            blocks = new ArrayList<>();

            // Inicializa el nivel 1
            InicializarJuegoPorNivel();
        }    
    


    public void avanzarNivel() {
        if (indiceNivel < niveles.size() - 1) {
            indiceNivel++;
            InicializarJuegoPorNivel();
        }
    }

    public Niveles getNivelActual() {
        if (indiceNivel < niveles.size()) {
            return niveles.get(indiceNivel);
        }
        return null;
    }

    // Monitorear inicio del juego
    public void monitorearInico() {
        if (ball.estaQuieto()) {
            ball.setXY(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
        } else {
            ball.update();
        }
    }


    public void verificarPelotar() {
    	controlBolasEnJuego.clearBolasFueraDePantalla();
        if (ball.getY() < 0 && controlBolasEnJuego.isEmpty()) {
            vidas--;

            if (vidas > 0) {
                // Si quedan vidas, restablece la posición de la pelota principal
                ball = new PingBall(pad);
            } else {
                // Si no quedan vidas, realiza acciones de fin de juego
                // Aquí puedes mostrar un mensaje de "Juego terminado" o realizar otras acciones necesarias
                // También puedes llamar a controlBolasEnJuego.clear() si deseas eliminar todas las bolas en juego
            }
        }
    }




    // Verificar game over
    public boolean isGameOver() {
        return vidas <= 0;
    }

    // Inicializar juego por nivel
    public void InicializarJuegoPorNivel() {
        Niveles currentLevel = getNivelActual();
        if (currentLevel != null) {
            currentLevel.initializeBlocks();
            ArrayList<Block> levelBlocks = currentLevel.getBlocks();
            blocks.clear();
            blocks.addAll(levelBlocks);
            ball = new PingBall(pad);
        }
    }

    // Verificar si el nivel se terminó
    public boolean isNivelCompleto() {
        return blocks.isEmpty();
    }

    // Dibujar bloques
    public void dibujarBloques() {
        batch.begin();
        for (Block b : blocks) {
            b.draw(batch);
            ball.checkCollision(b);
        }
        batch.end();
    }

    // Actualizar bloques
    public void actualizarBloques(ControlBolasEnJuego controlBolasEnJuego) {
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.getDestroyed()) {
                puntaje++;
                int bloqueX = b.getX(); // Obtiene la coordenada X del bloque
                int bloqueY = b.getY(); // Obtiene la coordenada Y del bloque
                blocks.remove(b);

                Random random = new Random();
                int numeroAleatorio = random.nextInt(100) + 1;
                if (numeroAleatorio >= 50) {
                	//se ve la propbabilidad de crear un poder, esta implementacion está adaptada solo a un poder
                	
                	
                    PingBallDoble nuevaBola = controlPoder.activarPoder(ball, bloqueX, bloqueY);
                    if (nuevaBola != null) {
                        controlBolasEnJuego.agregarBolaEnJuego(nuevaBola);
                    }

                }
                i--; // Para no saltarse 1 tras eliminar del ArrayList
            }
        }
    }

    public void colisionPelota(ControlBolasEnJuego controlBolasEnJuego) {
    	controlBolasEnJuego.colisionPelota(pad);
    	for (Block block : blocks) 
    		controlBolasEnJuego.colisionPelotaBloques(block);
        ball.checkCollision(pad); // Colisión de la bola original con el paddle
        
    }



    public void dibujarTabla() {
        batch.begin();
        pad.draw(batch);
        batch.end();
    }

    //se usa un getBolasEnJuego, que devuelve colección, solo para dibujo
    public void dibujarPelota(ControlBolasEnJuego controlBolasEnJuego) {
    	batch.begin();
        ball.draw(batch);
        controlBolasEnJuego.dibujarPelotas(batch);
        batch.end();
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
