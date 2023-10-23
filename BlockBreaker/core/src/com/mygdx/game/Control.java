package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.CarpetaNiveles.*;

public class Control {
    private ArrayList<Niveles> niveles;
    private ArrayList<Block> blocks;
    private int vidas;
    private int puntaje;
    private int indiceNivel;
    private PingBall ball;
    private Paddle pad;
    private ShapeRenderer shape;

    public Control() {
        niveles = new ArrayList<>();
        indiceNivel = 0;
        niveles.add(new Nivel1(1));
        niveles.add(new Nivel2(2));

        shape = new ShapeRenderer();
        pad = new Paddle();
        ball = new PingBall(pad);
        blocks = new ArrayList<>();
        vidas = 3;
        puntaje = 0;
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

    // Verificar si se fue la bola x abajo
    public void verificarPelotar() {
        if (ball.getY() < 0) {
            vidas--;
            ball = new PingBall(pad);
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
    	shape.begin(ShapeRenderer.ShapeType.Filled); // Llama a begin aquí
        for (Block b : blocks) {
            b.draw(shape);
            ball.checkCollision(b);
        }
        shape.end();
    }

    // Actualizar bloques
    public void actualizarBloques() {
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.getDestroyed()) {
                puntaje++;
                blocks.remove(b);
                i--; // Para no saltarse 1 tras eliminar del ArrayList
            }
        }
    }
    
    public void dibujarTabla() {
    	shape.begin(ShapeRenderer.ShapeType.Filled);
        pad.draw(shape);
        shape.end();
    }

    public void colisionPelota() {
        ball.checkCollision(pad);
    }

    public void dibujarPelota() {
    	shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        shape.end();
    }
}
