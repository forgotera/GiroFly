package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GiroFly;

public class GameOverState extends State {

    private Texture gameOverTexture;
    private Texture back;
    private Texture tapTexture;

    GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false,GiroFly.WIDTH,GiroFly.HEIGHT);
        gameOverTexture = new Texture("textGameOver.png");
        back = new Texture("UIbg.png");
        tapTexture = new Texture("tapTick.png");

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()){
            gameStateManager.set(new GameState(gameStateManager));
        }

    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(back,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(gameOverTexture,camera.position.x-gameOverTexture.getWidth() /2,camera.position.y);
        batch.draw(tapTexture,camera.position.x - tapTexture.getWidth()/2,camera.position.y-gameOverTexture.getHeight());
        batch.end();

    }

    @Override
    public void dispose() {
        back.dispose();
        gameOverTexture.dispose();

    }
}
