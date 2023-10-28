package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBallDoble implements Bola{
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private boolean estaQuieto;
    private Sound hurtSound;
    private Sprite sprite;

    public PingBallDoble(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = 20;
        this.xSpeed = (int) (xSpeed * (1.25));
        this.ySpeed = (int) (ySpeed * (1.25));
        estaQuieto = false;
        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        sprite = new Sprite(new Texture("bolaAmarillaHD.png"));
        sprite.setBounds(x, y, size, size);
    }


    public boolean isOutOfBounds() {
        return y < 0; // Verifica si la bola ha caído fuera de la pantalla.
    }

    public boolean estaQuieto() {
    	sprite.setPosition(x, y);
        return estaQuieto;
    }

    public void setEstaQuieto(boolean bb) {
        estaQuieto = bb;
    }

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

        // Lógica para rebotar en los bordes de la pantalla
        if (x - size < 0) {
            x = size;
            xSpeed = Math.abs(xSpeed);
        } else if (x + size > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - size;
            xSpeed = -Math.abs(xSpeed);
        }

        if (y + size > Gdx.graphics.getHeight()) {
            y = Gdx.graphics.getHeight() - size;
            ySpeed = -Math.abs(ySpeed);
        }
    }
    
    public void checkCollision(Paddle paddle) {
    	
        boolean collidesWithPaddle = collidesWith(paddle);

        if (collidesWithPaddle) {

            // Reajusta la posición de la bola para evitar que quede dentro del paddle
            if (x - size <= paddle.getX()) {
                x = paddle.getX() - size; // Mueve la bola a la izquierda del paddle
                xSpeed = -xSpeed;
                ySpeed = Math.abs(ySpeed);
            } 
            else if (x + size >= paddle.getX() + paddle.getWidth()) 
            {
                x = paddle.getX() + paddle.getWidth() + size; // Mueve la bola a la derecha del paddle
                ySpeed = Math.abs(ySpeed);
                xSpeed = -xSpeed;
            }
            else
            {
            	ySpeed = -ySpeed;
            }

        }
    }

    
    
    private boolean collidesWith(Paddle paddle) {

        boolean intersectaX = (x + size >= paddle.getX() && x - size <= paddle.getX() + paddle.getWidth());
        boolean intersectaY = (y >= paddle.getY() && y <= paddle.getY() + paddle.getHeight());
        return intersectaX && intersectaY;
    }
   
    public void checkCollision(Block block) {
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
    

    private boolean collidesWith(Block bb) {
        boolean intersectaX = (bb.getX() + bb.getWidth() >= x - size) && (bb.getX() <= x + size);
        boolean intersectaY = (bb.getY() + bb.getHeight() >= y - size) && (bb.getY() <= y + size);
        return intersectaX && intersectaY;
    }
}

