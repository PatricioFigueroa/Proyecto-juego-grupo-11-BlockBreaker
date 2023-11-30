package com.mygdx.game.CarpetaNiveles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.BloqueNivel3;
import com.mygdx.game.Niveles;

public class Nivel3 extends Niveles {
		public Nivel3(int numeroNivel) {
			super(numeroNivel);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void initializeBlocks() {
			
		    int[][] invertedCenteredTriangle = new int[][]{
		    	{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
		        {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
		        {0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0},
		        {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
		        {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
		        {0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
		        {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
		        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
		};

		    clearBlocks();
		    int blockWidth = Gdx.graphics.getWidth() / 13 - 10;
		    int blockHeight = Gdx.graphics.getHeight() / 20;
		    int y = Gdx.graphics.getHeight();
		    for (int i = 0; i < 8; i++) {
		        y -= blockHeight + 10;
		        for (int j = 0; j < 13; j++) {
		            if (invertedCenteredTriangle[i][j] == 1) // poner en la condicion el array
		            {
		            	addBlock(new BloqueNivel3(j * (blockWidth + 10), y, blockWidth, blockHeight));
		            }
		        }
		    }
		}
		
	}



