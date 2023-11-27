package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
    private static Camera camera;

    private Camera() {
        super();
        this.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public static Camera getInstance() {
        if (camera == null) {
            camera = new Camera();
        }
        return camera;
    }
}
