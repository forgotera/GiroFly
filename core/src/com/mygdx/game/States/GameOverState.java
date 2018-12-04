package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GiroFly;

public class GameOverState extends State {

    private Texture gameOverTexture;
    private Texture back;
    private Texture tapTexture;
    private BitmapFont font;
    private int score;

    GameOverState(GameStateManager gameStateManager , int score) {
        super(gameStateManager);
        camera.setToOrtho(false,GiroFly.WIDTH,GiroFly.HEIGHT);
        gameOverTexture = new Texture("textGameOver.png");
        back = new Texture("UIbg.png");
        tapTexture = new Texture("tapTick.png");
        font = new BitmapFont(Gdx.files.internal("appetitenew2.fnt"));
        font.getData().setScale(GiroFly.WIDTH/700f);
        this.score = score;
    }


    private void displayScore(SpriteBatch batch){
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font,"Your score " + score+"m");
        font.draw(batch,glyphLayout,camera.position.x - tapTexture.getWidth()/2-200,camera.position.y-gameOverTexture.getHeight());
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
        displayScore(batch);
        batch.draw(gameOverTexture,camera.position.x-gameOverTexture.getWidth() /2,camera.position.y);
        batch.draw(tapTexture,camera.position.x - tapTexture.getWidth()/2,camera.position.y-gameOverTexture.getHeight()-100);
        batch.end();
    }

    @Override
    public void dispose() {
        back.dispose();
        gameOverTexture.dispose();
    }
}
