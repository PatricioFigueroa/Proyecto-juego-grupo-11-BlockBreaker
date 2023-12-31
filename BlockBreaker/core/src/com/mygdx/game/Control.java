package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Bloque;
import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaInterfaces.Fondo;
import com.mygdx.game.CarpetaInterfaces.NivelFactory;
import com.mygdx.game.CarpetaInterfaces.Paddle;
import com.mygdx.game.CarpetaNiveles.*;

public class Control {
    private ArrayList<Niveles> niveles;
    private ArrayList<Bloque> blocks;
    private int vidas = 3;
    private int puntaje = 0;
    private int indiceNivel;
    private ControlPoder controlPoder;
    private Paddle pad;
    private SpriteBatch batch;
    private ControlBolasEnJuego controlBolasEnJuego;
    private boolean empezoJuego;
    private Fondo fondo;
    private NivelFactory nivelActual;
    private Comportamiento comportamiento;
    
    public Control() {
        niveles = new ArrayList<>();
        indiceNivel = 0;
        niveles.add(new Nivel1(1));
        niveles.add(new Nivel2(2));
        niveles.add(new Nivel3(3));
        empezoJuego = false;
        // Inicializa controlBolasEnJuego
        controlBolasEnJuego = new ControlBolasEnJuego();
        comportamiento = new Comportamiento();
        // Crea una instancia de ControlPoder y pasa controlBolasEnJuego
        controlPoder = new ControlPoder(controlBolasEnJuego);
        batch = new SpriteBatch();
        nivelActual = new Nivel1Factory();
        pad = nivelActual.crearPaddle();
        fondo = nivelActual.crearFondo();
        nivelActual.crearBloque();
        // Inicializa el nivel 1
        InicializarJuegoPorNivel();
    }

    public void avanzarNivel(GameScreen game) {
        if (indiceNivel < niveles.size() - 1) {
        	controlBolasEnJuego.clear();
        	empezoJuego = false;
            indiceNivel++;
            
            if(indiceNivel == 2)
            {
            	nivelActual = new Nivel2Factory();
            	pad = nivelActual.crearPaddle();
                fondo = nivelActual.crearFondo();
                nivelActual.crearBloque();
            }
            else
            {
            	nivelActual = new Nivel3Factory();
            	pad = nivelActual.crearPaddle();
                fondo = nivelActual.crearFondo();
                nivelActual.crearBloque();
                comportamiento.setComportamiento(new MovimientoAleatorio());
            }

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
    	
        if(indiceNivel == 0)
        {
        	comportamiento.setComportamiento(new MovimientoNormal());
        }
    	
        if (controlBolasEnJuego.isEmpty()) {
            PingBall bola = new PingBall(pad);
            comportamiento.aplicarComportamiento(bola);    
            controlBolasEnJuego.crearNuevaBola(bola);
        }

        if (empezoJuego) {
            controlBolasEnJuego.update();
        } else {
            if (controlBolasEnJuego.estaQuieto()) {
                controlBolasEnJuego.moverPelotaConPaddle(pad);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    controlBolasEnJuego.iniciarBolas();
                    empezoJuego = true;
                }
            }
        }
    }

    public void verificarPelotar() {
        controlBolasEnJuego.clearBolasFueraDePantalla();
        if (controlBolasEnJuego.isEmpty()) {
            vidas--;
            pad = nivelActual.crearPaddle();
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
            ArrayList<Bloque> levelBlocks = currentLevel.getBlocks();
            blocks = new ArrayList<>(levelBlocks);
        }
    }

    // Verificar si el nivel se terminó
    public boolean isNivelCompleto() {
        return blocks.isEmpty();
    }
    
    public void renderBackground(SpriteBatch batch) {
    	
    	batch.begin();
        fondo.draw(batch);
        batch.end();
    }
    public boolean ifGameComplete() {
        // Verifica si no hay más niveles y si el juego está completo
        return indiceNivel >= niveles.size() - 1 && isNivelCompleto();
    }

    // Dibujar bloques
    public void dibujarBloques() {
        batch.begin();
        for (Bloque b : blocks) {
            b.draw(batch);
            controlBolasEnJuego.colisionPelotaBloques(b, comportamiento);
        }
        batch.end();
    }

    // Actualizar bloques
    public void actualizarBloques() {
        for (int i = 0; i < blocks.size(); i++) {
            Bloque b = blocks.get(i);
            if (b.getDestroyed()) {
                puntaje++;
                int bloqueX = b.getX(); // Obtiene la coordenada X del bloque
                int bloqueY = b.getY(); // Obtiene la coordenada Y del bloque
                blocks.remove(b);
                    // Se ve la probabilidad de crear un poder, esta implementación está adaptada solo a un poder
                Bola nuevaBola = controlPoder.activarPoder(bloqueX, bloqueY, pad, controlBolasEnJuego.getBolaInicial());
                if (nuevaBola != null) {
	                comportamiento.aplicarComportamiento(nuevaBola);
	                controlBolasEnJuego.agregarBolaEnJuego(nuevaBola);                      
                }
             i--; // Para no saltarse 1 tras eliminar del ArrayList
            }
        }
    }

    public void colisionPelota() {
        controlBolasEnJuego.colisionPelota(pad);
        for (Bloque block : blocks)
            controlBolasEnJuego.colisionPelotaBloques(block, comportamiento);
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