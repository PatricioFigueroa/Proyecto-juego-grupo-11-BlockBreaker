package com.mygdx.game.CarpetaInterfaces;

import com.mygdx.game.Block;
import com.mygdx.game.Paddle;

public interface ColisionElementos {
    public void checkCollision(Paddle paddle);	    

    public void checkCollision(Block block) ;	
}
