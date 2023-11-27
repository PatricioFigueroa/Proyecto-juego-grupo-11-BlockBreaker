package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CarpetaInterfaces.DibujarElementos;

public class ControlBotones implements DibujarElementos{
    private Boton[] botones;
    private int selectedOption;
    private Screen[] screens;
    
    public ControlBotones(String[] opciones, float x, float y, float width, float height, Screen[] screens) {
        this.botones = Boton.crearBotones(opciones, x, y, width, height);
        this.selectedOption = 0;
        this.screens = screens;
    }

    public void draw(SpriteBatch batch) {
        for (Boton boton : botones) {
            boton.draw(batch);

            // Cambiar la opción seleccionada si se hace clic en un botón
            if (boton.isClicked()) {
                for (Boton btn : botones) {
                    btn.setSelected(false);
                }
                boton.setSelected(true);
                selectedOption = getIndexFromBoton(boton);
            }
        }
    }

    public void updateSelectedOption(int direction) {
        selectedOption = (selectedOption + direction + botones.length) % botones.length;
        updateBotonSelected();
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    private void updateBotonSelected() {
        for (Boton boton : botones) {
            boton.setSelected(false);
        }
        botones[selectedOption].setSelected(true);
    }

    private int getIndexFromBoton(Boton boton) {
        for (int i = 0; i < botones.length; i++) {
            if (botones[i] == boton) {
                return i;
            }
        }
        return -1;
    }
    
    public void handleExit() {
        // Lógica para salir de la aplicación
        Gdx.app.exit();
    }
    
    public Screen handleInput() {
        // Cambiar la opción seleccionada con las flechas
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            updateSelectedOption(1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            updateSelectedOption(-1);
        }

        // Seleccionar opción al presionar Enter
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            int selectedOption = getSelectedOption();
            if (selectedOption >= 0 && selectedOption < screens.length) {
                return screens[selectedOption];
            } else {
                // Llamar al método handleExit si la opción no es una pantalla
                handleExit();
            }
        }

        // Devolver null si no se selecciona ninguna pantalla
        return null;
    }
}