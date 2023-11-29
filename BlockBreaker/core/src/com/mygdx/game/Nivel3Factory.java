package com.mygdx.game;

import com.mygdx.game.CarpetaInterfaces.Bloque;
import com.mygdx.game.CarpetaInterfaces.Fondo;
import com.mygdx.game.CarpetaInterfaces.NivelFactory;
import com.mygdx.game.CarpetaInterfaces.Paddle;

public class Nivel3Factory implements NivelFactory {
	Fondo fondo;
	Bloque bloque;
	Paddle pad;
	@Override
	public Fondo crearFondo() {
		fondo = new FondoNivel3();
		return fondo;
	}

	@Override
	public Bloque crearBloque() {
		bloque = new BloqueNivel3();
		return bloque;
	}

	@Override
	public Paddle crearPaddle() {
		pad = new PaddleNivel3();
		return pad;
	}

}
