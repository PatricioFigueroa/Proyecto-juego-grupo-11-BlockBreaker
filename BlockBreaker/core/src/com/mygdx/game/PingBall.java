package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.Bloque;
import com.mygdx.game.CarpetaInterfaces.Bola;
import com.mygdx.game.CarpetaInterfaces.Paddle;

public class PingBall implements Bola {
	    private int x;
	    private int y;
	    private int size;
	    private int xSpeed;
	    private int ySpeed;
	    private boolean estaQuieto;
	    private Sound hurtSound;
	    private Sprite sprite;
	    
	    public PingBall(Paddle pad) {
	        this.x = pad.getX()+pad.getWidth()/2-5;
	        this.y = pad.getY()+pad.getHeight()+11;
	        xSpeed = 0;
	        ySpeed = 0;
	        this.size = 20;
	        estaQuieto = true;
	        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	        sprite = new Sprite(new Texture("bolaBlancaHD.png"));
	        sprite.setBounds(x, y, size, size);
	    }

	    @Override
		public boolean estaQuieto() {
			// TODO Auto-generated method stub
	    	sprite.setPosition(x, y);
	    	return estaQuieto;
		}

	    public void setEstaQuieto(boolean bb) {
	    	estaQuieto=bb;
	    }

	    public void setXY(int x, int y) {
	    	this.x = x;
	        this.y = y;
	    }

	    public int getY() {
	    	return y;
	    	}
	    
	    public void draw(SpriteBatch batch){
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
	    
	    
	    public void checkCollision(Paddle paddle) {
			// TODO Auto-generated method stub
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
	    
	    public boolean checkCollision(Bloque block, Comportamiento comportamiento) {
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
	            return true;
	        }
	        return false;
	    }
	    private boolean collidesWith(Bloque bb) {

	    	boolean intersectaX = (bb.getX() + bb.getWidth() >= x-size) && (bb.getX() <= x+size);
	        boolean intersectaY = (bb.getY() + bb.getHeight() >= y-size) && (bb.getY() <= y+size);		
	    	return intersectaX && intersectaY;
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
		public int getX() {
			return x;
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