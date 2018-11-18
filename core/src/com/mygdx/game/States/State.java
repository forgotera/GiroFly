package com.mygdx.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    OrthographicCamera camera;
    private Vector3 vector3;
    GameStateManager gameStateManager;

    State(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
        vector3 = new Vector3();
    }

    protected  abstract void handleInput();
    public abstract void update( float delta);
    public  abstract void render(SpriteBatch batch);
    public  abstract void dispose();
}
