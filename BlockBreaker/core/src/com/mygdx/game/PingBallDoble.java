package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

public class PingBallDoble implements Bola {
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private Color color = Color.WHITE;
    private boolean estaQuieto;
    private Sound hurtSound;

    public PingBallDoble(PingBall pingBall) {
        this.x = pingBall.getX();
        this.y = pingBall.getY();
        this.size = pingBall.getSize();
        this.xSpeed = -pingBall.getXSpeed(); // Invertir la velocidad en X
        this.ySpeed = -pingBall.getYSpeed(); // Invertir la velocidad en Y
        estaQuieto = pingBall.estaQuieto();
        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));

        System.out.println("Velocidad X inicial: " + xSpeed);
        System.out.println("Velocidad Y inicial: " + ySpeed);
    }

    public PingBallDoble(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = 10;
        this.xSpeed = (int) (xSpeed*(1.2));
        this.ySpeed = (int) (ySpeed*(1.2));
        estaQuieto = false;
        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        System.out.println("Velocidad X inicial: " + xSpeed);
        System.out.println("Velocidad Y inicial: " + ySpeed);
    }

    public boolean isOutOfBounds() {
        return y < 0; // Verifica si la bola ha caído fuera de la pantalla.
    }

    public int getSize() {
        return size;
    }

    public boolean estaQuieto() {
        return estaQuieto;
    }

    public void setEstaQuieto(boolean bb) {
        estaQuieto = bb;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
        update();
    }

    public void update() {
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
            color = Color.BLUE;

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

        } else {
            // No hay colisión
            color = Color.WHITE;
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

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public int getX() {
        return x;
    }
}

