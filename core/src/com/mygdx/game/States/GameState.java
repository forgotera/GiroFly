package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GiroFly;
import com.mygdx.game.Sprites.Girocopter;
import com.mygdx.game.Sprites.Rock;


public class GameState extends State {

    private static final int ROCK_COUNT = 4;
    private static final int ROCK_SPACE = 400;
    
    private Texture backTexture;
    private Texture graundTexture;
    private Texture downGraundTexture;
    private Vector2 groundVector1,groundVector2;
    private Vector2 downGroundVector1,downGroundVector2;
    private Girocopter girocopter;
    private Array<Rock> rocks;

    GameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        girocopter = new Girocopter(300,GiroFly.HEIGHT/4);
        backTexture = new Texture("background.png");
        graundTexture = new Texture("groundDirt.png");
        downGraundTexture = new Texture("DownGroundDirt.png");

        groundVector1 = new Vector2(camera.position.x-(camera.viewportWidth)/2,0);
        groundVector2 = new Vector2(camera.position.x-(camera.viewportWidth/2)+graundTexture.getWidth(),0);

        downGroundVector1 = new Vector2(camera.position.x-(camera.viewportWidth/2),GiroFly.HEIGHT-downGraundTexture.getHeight());
        downGroundVector2 = new Vector2(camera.position.x-(camera.viewportWidth/2)+graundTexture.getWidth(),GiroFly.HEIGHT-downGraundTexture.getHeight());

        rocks = new Array<Rock>();
        camera.setToOrtho(false,GiroFly.WIDTH,GiroFly.HEIGHT);

        /*
          Добавление скал
         */
        for (int i = 0; i < ROCK_COUNT;i++){
            rocks.add(new Rock(i*(ROCK_SPACE+Rock.WIDTH)));
        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            girocopter.move();
        }

    }

    @Override
    public void update(float delta) {
        handleInput();
        updateGround();

            //Передвижение вертолета
            girocopter.update(delta);
        /*
          Привязывваем камеру к вертолету
         */
            camera.position.x = girocopter.getPosition().x + 100;


        /*
          Перестановка скал из конца в начало
         */

        for(Rock rock : rocks){
            if ( (camera.position.x - (camera.viewportWidth / 2) > rock.getDownRockVector().x+rock.getDownRockTexture().getWidth())){
                rock.reposition(rock.getUpRockVector().x + ((Rock.WIDTH+ROCK_SPACE))*ROCK_COUNT);
            }

            //соприкосновения спрайтов

            if(rock.colight(girocopter.getGyrocopter())){
                gameStateManager.set(new GameOverState(gameStateManager));

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
        batch.draw(graundTexture,groundVector1.x,groundVector1.y);
        batch.draw(graundTexture,groundVector2.x,groundVector2.y);
        batch.draw(downGraundTexture,downGroundVector1.x,downGroundVector1.y);
        batch.draw(downGraundTexture,downGroundVector2.x,downGroundVector2.y);
        batch.end();

    }

    private void updateGround(){


        //высnавление земли снизу
        //FIXME  земля с верху не повторятся
        if(camera.position.x - (camera.viewportWidth/2) > groundVector1.x+graundTexture.getWidth()){
             groundVector1.add(graundTexture.getWidth()*2,0);
             downGroundVector1.add(graundTexture.getWidth()*2,GiroFly.HEIGHT-downGraundTexture.getHeight());
        }
        if (camera.position.x - (camera.viewportWidth/2) > groundVector2.x+graundTexture.getWidth()){
           groundVector2.add(graundTexture.getWidth()*2,0);
            downGroundVector2.add(graundTexture.getWidth()*2,GiroFly.HEIGHT-downGraundTexture.getHeight());
        }


        //выставление земли сверху
        /*
        if (camera.position.x - (camera.viewportWidth/2) > downGroundVector1.x+downGraundTexture.getWidth()){
            downGroundVector1.add(downGraundTexture.getWidth()*2,407);
        }
        if(camera.position.x - (camera.viewportWidth/2) > downGroundVector2.x+downGraundTexture.getWidth()){
            downGroundVector2.add(downGraundTexture.getWidth()*2,407);
        }
        */

    }

    @Override
    public void dispose() {
        backTexture.dispose();
        graundTexture.dispose();
        girocopter.dispose();
        downGraundTexture.dispose();
        for(int i =0; i < rocks.size;i++){
            rocks.get(i).dispose();
        }
    }
}
