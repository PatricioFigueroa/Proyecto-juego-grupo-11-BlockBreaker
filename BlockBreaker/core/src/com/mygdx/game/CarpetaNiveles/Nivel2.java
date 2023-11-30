package com.mygdx.game.CarpetaNiveles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BloqueNivel2;
import com.mygdx.game.Niveles;

public class Nivel2 extends Niveles {

    public Nivel2(int numeroNivel) {
        super(numeroNivel);
    }

    @Override
    public void initializeBlocks() {
        int height = 8;
        int width = 13;

        int[][] customBlockLayout = new int[][]{
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}
        };

        clearBlocks();
        int blockWidth = Gdx.graphics.getWidth() / 13 - 10;
        int blockHeight = Gdx.graphics.getHeight() / 20;
        int y = Gdx.graphics.getHeight();
        for (int i = 0; i < height; i++) {
            y -= blockHeight + 10;
            for (int j = 0; j < width; j++) {
                if (customBlockLayout[i][j] == 1) {
                	addBlock(new BloqueNivel2(j * (blockWidth + 10), y, blockWidth, blockHeight));
                }
            }
        }
    }


}
