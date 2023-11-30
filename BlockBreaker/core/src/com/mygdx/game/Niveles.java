package com.mygdx.game;

import java.util.ArrayList;

import com.mygdx.game.CarpetaInterfaces.Bloque;

public abstract class Niveles {
    private ArrayList<Bloque> blocks;
    public Niveles(int numeroNivel) {
        this.blocks = new ArrayList<>();
        // Inicializa los bloques del nivel según la matriz de generación de bloques.
        initializeBlocks();
        // Inicializa otros aspectos del nivel, como fondos, música, etc.
        initializeLevelAssets();
    }
    
    public void clearBlocks() {
        blocks.clear();
    }
    
    public void addBlock(Bloque block) {
        blocks.add(block);
    }

    public abstract void initializeBlocks();

    public abstract void initializeLevelAssets();
   
    //se utiliza para extraer los bloques, no para modificarlos
    //, en el método InicializarJuegoPorNivel se crea una copia de este y ese se modifica
    public ArrayList<Bloque> getBlocks() {
        return blocks;
    }

    // Agrega métodos para verificar si el nivel se ha completado, etc.
}
