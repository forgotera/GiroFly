package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GiroFly;
import com.mygdx.game.Sprites.Girocopter;
import com.mygdx.game.Sprites.Rock;


public class GameState extends State {

    private static final int ROCK_COUNT = 3;
    private static final int ROCK_SPACE = 250;

    private Texture backTexture;
    private Texture graundTexture;
    private Girocopter girocopter;
    private Array<Rock> rocks;

    GameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        girocopter = new Girocopter(50,300);
        backTexture = new Texture("background.png");
        graundTexture = new Texture("groundDirt.png");
        rocks = new Array<Rock>();
        camera.setToOrtho(false,GiroFly.WIDTH,GiroFly.HEIGHT);

        /**
         * Добавление скал
         */
        for (int i = 0; i < ROCK_COUNT;i++){
            rocks.add(new Rock(i*(ROCK_SPACE+Rock.WIDTH)));
        }
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

        /*
          Привязывваем камеру к вертолету
         */
        camera.position.x = girocopter.getPosition().x+100;

        /*
          Перестановка скал из конца в начало
         */
        for(Rock rock : rocks){
            if ( (camera.position.x - (camera.viewportWidth / 2) > rock.getUpRockVector().x+rock.getUpRockTexture().getWidth())){
                rock.reposition(rock.getUpRockVector().x + ((Rock.WIDTH+ROCK_SPACE))*ROCK_COUNT);
            }
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backTexture,camera.position.x - (camera.viewportWidth/2),0);
        batch.draw(girocopter.getGyroTexture(),girocopter.getPosition().x,girocopter.getPosition().y);

        for(Rock rock :rocks) {
            batch.draw(rock.getUpRockTexture(), rock.getUpRockVector().x, rock.getUpRockVector().y);
            batch.draw(rock.getDownRockTexture(), rock.getDownRockVector().x, rock.getDownRockVector().y);
        }

        batch.draw(graundTexture,camera.position.x - (camera.viewportWidth/2),0);
        batch.end();
    }

    @Override
    public void dispose() {
        backTexture.dispose();
        graundTexture.dispose();
        girocopter.dispose();
        for(int i =0; i < rocks.size;i++){
            rocks.get(i).dispose();
        }
    }
}
