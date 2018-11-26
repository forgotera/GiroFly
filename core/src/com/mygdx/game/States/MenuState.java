package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends  State {

    private Texture back;
    private Texture button;

    protected MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        back = new Texture("UIbg.png");
        button = new Texture("buttonLarge.png");
    }

    @Override
    protected void handleInput() {
        //FIXME нужно добваить клики именно по кнопкам. смотреть клик по координатам?
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
        batch.draw(button,  ((Gdx.graphics.getWidth()/2) - (button.getWidth())),Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
        batch.end();
    }

    @Override
    public void dispose() {
        back.dispose();
        button.dispose();
    }
}
