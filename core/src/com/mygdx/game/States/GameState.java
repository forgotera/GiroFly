package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Sprites.Girocopter;

public class GameState extends State {

    private Texture backTexture;
    private Texture graundTexture;
    private Girocopter girocopter;

    GameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        girocopter = new Girocopter(50,300);
        backTexture = new Texture("background.png");
        graundTexture = new Texture("groundDirt.png");
        camera.setToOrtho(false,800,480);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            girocopter.move();
        }

    }

    @Override
    public void update(float delta) {
        handleInput();
        girocopter.update(delta);

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backTexture,camera.position.x - (camera.viewportWidth/2),0);
        batch.draw(girocopter.getGyroTexture() ,girocopter.getPosition().x,girocopter.getPosition().y);
        batch.draw(graundTexture,camera.position.x - (camera.viewportWidth/2),0);
        batch.end();

    }

    @Override
    public void dispose() {
        backTexture.dispose();
        graundTexture.dispose();
    }
}
