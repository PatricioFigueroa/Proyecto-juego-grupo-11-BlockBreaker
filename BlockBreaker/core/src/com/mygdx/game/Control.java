package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaNiveles.*;

public class Control {
    private ArrayList<Niveles> niveles;
    private ArrayList<Block> blocks;
    private int vidas = 3;
    private int puntaje = 0;
    private int indiceNivel;
    private ControlPoder controlPoder;
    private Paddle pad;
    private SpriteBatch batch;
    private ControlBolasEnJuego controlBolasEnJuego;
    private boolean empezoJuego;

    public Control() {
        niveles = new ArrayList<>();
        indiceNivel = 0;
        niveles.add(new Nivel1(1));
        niveles.add(new Nivel2(2));
        empezoJuego = false;
        // Inicializa controlBolasEnJuego
        controlBolasEnJuego = new ControlBolasEnJuego();

        // Crea una instancia de ControlPoder y pasa controlBolasEnJuego
        controlPoder = new ControlPoder(controlBolasEnJuego);
        batch = new SpriteBatch();
        pad = new Paddle();
        // Inicializa el nivel 1
        InicializarJuegoPorNivel();
    }

    public void avanzarNivel(GameScreen game) {
        if (indiceNivel < niveles.size() - 1) {
        	controlBolasEnJuego.clear();
        	empezoJuego = false;
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
        if (controlBolasEnJuego.isEmpty()) {
            controlBolasEnJuego.crearNuevaBola(pad);
        }
    
        if(empezoJuego == false)
        {
        	if(controlBolasEnJuego.estaQuieto())
            {
                controlBolasEnJuego.moverPelotaConPaddle(pad);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    controlBolasEnJuego.iniciarBolas();
                    empezoJuego = true;
                } 
            }
        }
        else
        {
            controlBolasEnJuego.update(); // Mueve la pelota cuando no está quieta
        }
    }

    public void verificarPelotar() {
        controlBolasEnJuego.clearBolasFueraDePantalla();
        if (controlBolasEnJuego.isEmpty()) {
            vidas--;
            empezoJuego = false;
            if (vidas > 0) {
                // Si quedan vidas, realiza acciones de fin de juego
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
            blocks = new ArrayList<>(levelBlocks);
        }
    }

    // Verificar si el nivel se terminó
    public boolean isNivelCompleto() {
        return blocks.isEmpty();
    }
    
    public boolean ifGameComplete() {
        // Verifica si no hay más niveles y si el juego está completo
        return indiceNivel >= niveles.size() - 1 && isNivelCompleto();
    }

    // Dibujar bloques
    public void dibujarBloques() {
        batch.begin();
        for (Block b : blocks) {
            b.draw(batch);
            controlBolasEnJuego.colisionPelotaBloques(b);
        }
        batch.end();
    }

    // Actualizar bloques
    public void actualizarBloques() {
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
                    // Se ve la probabilidad de crear un poder, esta implementación está adaptada solo a un poder
                    Bola nuevaBola = controlPoder.activarPoder(bloqueX, bloqueY);
                    if (nuevaBola != null) {
                        controlBolasEnJuego.agregarBolaEnJuego(nuevaBola);
                    }
                }
                i--; // Para no saltarse 1 tras eliminar del ArrayList
            }
        }
    }

    public void colisionPelota() {
        controlBolasEnJuego.colisionPelota(pad);
        for (Block block : blocks)
            controlBolasEnJuego.colisionPelotaBloques(block);
    }

    public void dibujarTabla() {
        batch.begin();
        pad.draw(batch);
        batch.end();
    }

    // Se usa un getBolasEnJuego, que devuelve una colección, solo para dibujo
    public void dibujarPelota() {
        batch.begin();
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