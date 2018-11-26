package com.mygdx.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
    OrthographicCamera camera;
    GameStateManager gameStateManager;

    State(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
    }

    protected  abstract void handleInput();
    public abstract void update( float delta);
    public  abstract void render(SpriteBatch batch);
    public  abstract void dispose();
}
