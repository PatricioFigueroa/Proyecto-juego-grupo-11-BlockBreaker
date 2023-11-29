package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Bloque;
import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaInterfaces.Paddle;

public class PingBallDoble implements Bola{
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private boolean estaQuieto;
    private Sound hurtSound;
    private Sprite sprite;

    public PingBallDoble(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 20;
        estaQuieto = false;
        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        sprite = new Sprite(new Texture("bolaAmarillaHD.png"));
        sprite.setBounds(x, y, size, size);
    }


    public boolean isOutOfBounds() {
        return y < 0; // Verifica si la bola ha caído fuera de la pantalla.
    }

	@Override
    public boolean estaQuieto() {
    	sprite.setPosition(x, y);
        return estaQuieto;
    }
	
	@Override
    public void setEstaQuieto(boolean bb) {
        estaQuieto = bb;
    }
    
	@Override
    public int getY() {
        return y;
    }

    public void draw(SpriteBatch batch) {
    	
    	sprite.draw(batch);
    }

    public void update() {
    	sprite.setPosition(x, y);

        if (estaQuieto) return;
        
        x += xSpeed;
        y += ySpeed;

        if (x - size < 0) {
            x = size; // Ajusta la posición de la bola para evitar que pase por el borde izquierdo
            xSpeed = Math.abs(xSpeed); // Cambia la dirección en X
        } else if (x + size > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - size; // Ajusta la posición de la bola para evitar que pase por el borde derecho
            xSpeed = -Math.abs(xSpeed); // Cambia la dirección en X
        }

        if (y + size > Gdx.graphics.getHeight()) {
            y = Gdx.graphics.getHeight() - size; // Ajusta la posición de la bola para evitar que pase por el borde inferior
            ySpeed = -Math.abs(ySpeed); // Cambia la dirección en Y
        }
    }
    
    @Override
    public void checkCollision(Paddle paddle) {
        boolean collidesWithPaddle = collidesWith(paddle);

        if (collidesWithPaddle) {
            ySpeed = -ySpeed; // Invertir la velocidad en el eje Y

            // Asegúrate de que la velocidad en Y sea siempre positiva para evitar que la bola quede atrapada
            ySpeed = Math.abs(ySpeed);

            // Ajusta la posición de la bola para evitar que quede dentro del Paddle
            if (y - size < paddle.getY() + paddle.getHeight()) {
                y = paddle.getY() + paddle.getHeight() + size;
            }

            // Reproduce el sonido de colisión
            hurtSound.play();
        }
    }


    
    
    private boolean collidesWith(Paddle paddle) {

        boolean intersectaX = (x + size >= paddle.getX() && x - size <= paddle.getX() + paddle.getWidth());
        boolean intersectaY = (y >= paddle.getY() && y <= paddle.getY() + paddle.getHeight());
        return intersectaX && intersectaY;
    }
    
   
    public void checkCollision(Bloque block) {
        if (collidesWith(block)) {
            if (x - size <= block.getX()) {
                xSpeed = -Math.abs(xSpeed);
            } else if (x + size >= block.getX() + block.getWidth()) {
                xSpeed = Math.abs(xSpeed);
            } else if (y + size >= block.getY() + block.getHeight()) {
                ySpeed = Math.abs(ySpeed);
            } else {
                ySpeed = -Math.abs(ySpeed);
            }
            hurtSound.play();
            block.setDestroyed();
        }
    }
    

    private boolean collidesWith(Bloque bb) {
        boolean intersectaX = (bb.getX() + bb.getWidth() >= x - size) && (bb.getX() <= x + size);
        boolean intersectaY = (bb.getY() + bb.getHeight() >= y - size) && (bb.getY() <= y + size);
        return intersectaX && intersectaY;
    }


	@Override
	public int getX() {
		return x;
	}
	
    @Override
	public int getXSpeed() {
		return xSpeed;
	}

	@Override
	public int getYSpeed() {
		return ySpeed;
	}


	@Override
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
		
	}

	@Override
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
}

