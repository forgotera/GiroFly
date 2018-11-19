package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Sprites.Girocopter;
import com.mygdx.game.Sprites.Rock;

public class GameState extends State {



    private Texture backTexture;
    private Texture graundTexture;
    private Girocopter girocopter;
    private Rock rock;

    GameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        girocopter = new Girocopter(50,300);
        backTexture = new Texture("background.png");
        graundTexture = new Texture("groundDirt.png");
        rock = new Rock(350);
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
        batch.draw(girocopter.getGyroTexture(),girocopter.getPosition().x,girocopter.getPosition().y);
        batch.draw(rock.getUpRockTexture(),rock.getUpRockVector().x,rock.getUpRockVector().y);
        batch.draw(rock.getDownRockTexture(),rock.getDownRockVector().x,rock.getDownRockVector().y);
        batch.draw(graundTexture,camera.position.x - (camera.viewportWidth/2),0);
        batch.end();

    }

    @Override
    public void dispose() {
        backTexture.dispose();
        graundTexture.dispose();
        girocopter.dispose();
    }
}
