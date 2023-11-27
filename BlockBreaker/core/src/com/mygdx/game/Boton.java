package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.DibujarElementos;

//En la clase Boton

public class Boton implements DibujarElementos {
	 private Sprite sprite;
	 private BitmapFont font;
	 private String texto;
	 private float x, y, width, height;
	 private boolean seleccionado;
	
	 public Boton(String texto, float x, float y, float width, float height) {
	     this.texto = texto;
	     this.x = x;
	     this.y = y;
	     this.width = width;
	     this.height = height;
	     this.seleccionado = false;
	
	     // Cargar la textura del botón
	     this.sprite = new Sprite(new Texture("boton.png"));
	
	     // Ajustar tamaño y posición del sprite
	     this.sprite.setSize(width, height);
	     this.sprite.setPosition(x, y);
	
	     // Crear una fuente para el texto
	     this.font = new BitmapFont();
	     this.font.getData().setScale(2, 2);
	 }
	
	 public void draw(SpriteBatch batch) {
	     // Dibujar el botón
	     sprite.draw(batch);
	
	     // Dibujar el texto centrado en el botón
	     font.setColor(1, 1, 1, 1);
	     GlyphLayout layout = new GlyphLayout(font, texto);
	     float textX = x + (width - layout.width) / 2;
	     float textY = y + (height + layout.height) / 2;
	     font.draw(batch, texto, textX, textY);
	
	     // Resaltar el botón si está seleccionado
	     if (seleccionado) {
	         sprite.setColor(1, 1, 0, 1);
	     } else {
	         sprite.setColor(1, 1, 1, 1);
	     }
	 }
	
	 public void setSelected(boolean selected) {
	     this.seleccionado = selected;
	 }
	
	 public boolean isMouseOver() {
	     // Verificar si el mouse está sobre el botón
	     float mouseX = Gdx.input.getX();
	     float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invertir la coordenada y del mouse
	     return (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
	 }
	
	 public boolean isClicked() {
	     // Verificar si el botón fue clickeado
	     return Gdx.input.justTouched() && isMouseOver();
	 }
	
	 public static Boton[] crearBotones(String[] opciones, float x, float y, float width, float height) {
	     Boton[] botones = new Boton[opciones.length];
	     for (int i = 0; i < opciones.length; i++) {
	         botones[i] = new Boton(opciones[i], x, y - 50 * i, width, height);
	     }
	     botones[0].setSelected(true);
	     return botones;
	 }
}

