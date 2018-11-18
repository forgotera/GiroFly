package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GiroFly;
import com.mygdx.game.Sprites.Girocopter;

public class GameState extends State {

    private Texture backTexture;
    private Girocopter girocopter;

    public GameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        girocopter = new Girocopter(50,300);
        backTexture = new Texture("background.png");
        camera.setToOrtho(false);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {
        girocopter.update(delta);

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backTexture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(girocopter.getGyroTexture() ,girocopter.getPosition().x,girocopter.getPosition().y);
        batch.end();

    }

    @Override
    public void dispose() {

    }
}
