package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends  State {

    private Texture back;
    private Texture button;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        back = new Texture("UIbg.png");
        button = new Texture("buttonLarge.png");
    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched()){
            gameStateManager.set(new GameState(gameStateManager));
        }
    }

    @Override
    public void update(float delta) {

        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.begin();
        batch.draw(back,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(button,((Gdx.graphics.getWidth()/2) - (button.getWidth()/2)),Gdx.graphics.getHeight()/2);
        batch.end();

    }

    @Override
    public void dispose() {
        back.dispose();
        button.dispose();
    }
}
