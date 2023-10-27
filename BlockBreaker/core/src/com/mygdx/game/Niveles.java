package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public abstract class Niveles {
    protected ArrayList<Block> blocks;
    protected int levelNumber;
    protected Texture background;

    public Niveles(int numeroNivel) {
        this.levelNumber = numeroNivel;
        this.blocks = new ArrayList<>();
        // Inicializa los bloques del nivel según la matriz de generación de bloques.
        initializeBlocks();
        // Inicializa otros aspectos del nivel, como fondos, música, etc.
        initializeLevelAssets();
    }

    public abstract void initializeBlocks();

    public abstract void initializeLevelAssets();
   
    //se utiliza para extraer los bloques, no para modificarlos
    //, en el método InicializarJuegoPorNivel se crea una copia de este y ese se modifica
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    // Agrega métodos para verificar si el nivel se ha completado, etc.
}
