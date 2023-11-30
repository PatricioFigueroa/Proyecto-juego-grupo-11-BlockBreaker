package com.mygdx.game.CarpetaInterfaces;

import com.mygdx.game.Comportamiento;

public interface ColisionElementos {
    public void checkCollision(Paddle paddle);	    

    public boolean checkCollision(Bloque block, Comportamiento comportamiento) ;	
}
