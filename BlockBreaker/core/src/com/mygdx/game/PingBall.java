package com.mygdx.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall {
	    private int x;
	    private int y;
	    private int size;
	    private int xSpeed;
	    private int ySpeed;
	    private Color color = Color.WHITE;
	    private boolean estaQuieto;
	    private Sound hurtSound;
	    
	    
	    
	    //pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11
	    
	    public PingBall(Paddle pad) {
	        this.x = pad.getX()+pad.getWidth()/2-5;
	        this.y = pad.getY()+pad.getHeight()+11;
	        this.size = 10;
	        this.xSpeed = 10;
	        this.ySpeed = 14;
	        estaQuieto = true;
	        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	    }
    
	    public boolean estaQuieto() {
	    	return estaQuieto;
	    }
	    public void setEstaQuieto(boolean bb) {
	    	estaQuieto=bb;
	    }
	    public void setXY(int x, int y) {
	    	this.x = x;
	        this.y = y;
	    }
	    public int getY() {return y;}
	    
	    public void draw(ShapeRenderer shape){
	        shape.setColor(color);
	        shape.circle(x, y, size);
	    }
	    
	    public void update() {
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
	        if(collidesWith(block)){
	        	if (x - size <= block.getX()) {
	        		xSpeed = -Math.abs(xSpeed);
			    } 
			    else if (x + size >= block.getX() + block.getWidth()) 
			    {
			        xSpeed = Math.abs(xSpeed);
			    }
			    else if(y + size >= block.getY() + block.getHeight())
			    {
			    	ySpeed = Math.abs(ySpeed);
			    }
			    else
			    {
			    	ySpeed = -Math.abs(ySpeed);
			    }
	            hurtSound.play();
	            block.setDestroyed(); 
	        }
	    }
	    private boolean collidesWith(Block bb) {

	    	boolean intersectaX = (bb.getX() + bb.getWidth() >= x-size) && (bb.getX() <= x+size);
	        boolean intersectaY = (bb.getY() + bb.getHeight() >= y-size) && (bb.getY() <= y+size);		
	    	return intersectaX && intersectaY;
	    }
	    
	}