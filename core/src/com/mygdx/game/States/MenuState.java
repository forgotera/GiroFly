package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends  State {

    private Texture back;
    private Texture button;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        back = new Texture("Ulbg.png");
        button = new Texture("buttonSmall.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(back,0,0,);
        batch.end();

    }

    @Override
    public void dispose() {

    }
}
