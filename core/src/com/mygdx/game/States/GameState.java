package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GiroFly;
import com.mygdx.game.Sprites.Girocopter;
import com.mygdx.game.Sprites.Rock;



public class GameState extends State {

    private static final int ROCK_COUNT = 8;
    private  int ROCK_SPACE = 230;

    private Texture backTexture;
    private Texture graundTexture;
    private Texture downGraundTexture;
    private Texture tapTexture;
    private Texture getReadyTexture;
    private Vector2 groundVector1,groundVector2;
    private Vector2 downGroundVector1,downGroundVector2;
    private Girocopter girocopter;
    private Array<Rock> rocks;
    private BitmapFont font;
    private Boolean isPause = true;


    GameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        girocopter = new Girocopter(100,GiroFly.HEIGHT/2);
        backTexture = new Texture("background.png");
        graundTexture = new Texture("groundDirt.png");
        downGraundTexture = new Texture("DownGroundDirt.png");
        tapTexture = new Texture("tapTick.png");
        getReadyTexture = new Texture("textGetReady.png");
        //отображение текста
        font = new BitmapFont(Gdx.files.internal("appetitenew2.fnt"));
        font.getData().setScale(GiroFly.WIDTH/1300f);

        groundVector1 = new Vector2(camera.position.x-(camera.viewportWidth)/2,0);
        groundVector2 = new Vector2(camera.position.x-(camera.viewportWidth/2)+graundTexture.getWidth(),0);

        downGroundVector1 = new Vector2(camera.position.x-(camera.viewportWidth/2),GiroFly.HEIGHT-downGraundTexture.getHeight());
        downGroundVector2 = new Vector2(camera.position.x-(camera.viewportWidth/2)+graundTexture.getWidth(),GiroFly.HEIGHT-downGraundTexture.getHeight());

        camera.setToOrtho(false,GiroFly.WIDTH,GiroFly.HEIGHT);

        /*
         Добавление скал
        */
        rocks = new Array<Rock>();
        for (int i = 0; i < ROCK_COUNT;i++){
            rocks.add(new Rock((i*ROCK_SPACE+Rock.WIDTH)+500));
        }


    }

    private void displayMessage(SpriteBatch batch){
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, "Distance " + Math.round((girocopter.getPosition().x-100.0f)/100)+"m");
        font.draw(batch,glyphLayout,camera.position.x-400,GiroFly.HEIGHT);
        if(isPause){
           batch.draw(tapTexture,camera.position.x-tapTexture.getWidth()/2,camera.position.y-getReadyTexture.getHeight()+100);
           batch.draw(getReadyTexture,camera.position.x-getReadyTexture.getWidth()/2,camera.position.y+100);
        }
    }


    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched()){
            isPause = false;
        }

        //android управление
        if(Gdx.input.isTouched()){
            girocopter.move();
        }


        /*desktop
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            girocopter.move();
        }*/

    }

    @Override
    public void update(float delta) {
        handleInput();
        updateGround();

        //переменная для изменеия расстояния
        if(!isPause) {
            //FIXME нужно сделать увеличение скорости
            //Передвижение вертолета
            girocopter.update(delta, rocks.get(1).getUpRockTexture(), rocks.get(1).getDownRockTexture());

        /*
          Привязывваем камеру к вертолету
         */
            camera.position.x = girocopter.getPosition().x+300;
        }

            /*
             добавление скал
                предедущая скала удалясется и рандомно добавляется новая
            */
        for(int i =0;i<rocks.size;i++) {

            if (rocks.get(i).getDownRockVector() != null) {

                if ((camera.position.x - (camera.viewportWidth / 2) > rocks.get(i).getDownRockVector().x + rocks.get(i).getDownRockTexture().getWidth())) {
                    rocks.add((new Rock(rocks.get(i).getDownRockVector().x + ((Rock.WIDTH + ROCK_SPACE)) * ROCK_COUNT)));
                    rocks.removeIndex(i);
                }

            } else {
                if (rocks.get(i).getUpRockVector() != null) {
                    {

                        if (((camera.position.x - (camera.viewportWidth / 2) > rocks.get(i).getUpRockVector().x + rocks.get(i).getUpRockTexture().getWidth()))) {
                            rocks.add(new Rock(rocks.get(i).getUpRockVector().x + ((Rock.WIDTH + ROCK_SPACE)) * ROCK_COUNT));
                            rocks.removeIndex(i);
                        }

                    }
                }
            }

          //  if(rocks.get(i).colight(girocopter.getGyrocopter())){
           //     gameStateManager.set(new GameOverState(gameStateManager));
            //}

            camera.update();
        }

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backTexture,camera.position.x - (camera.viewportWidth/2),0);
        batch.draw(girocopter.getGyroTexture(),girocopter.getPosition().x,girocopter.getPosition().y);
        for(Rock rock :rocks) {
            if(rock.getUpRockVector() != null) {
                batch.draw(rock.getUpRockTexture(), rock.getUpRockVector().x, rock.getUpRockVector().y);
            }
            if(rock.getDownRockVector() != null)
            batch.draw(rock.getDownRockTexture(), rock.getDownRockVector().x, rock.getDownRockVector().y);
        }
        batch.draw(graundTexture,groundVector1.x,groundVector1.y);
        batch.draw(graundTexture,groundVector2.x,groundVector2.y);
        //WTF!?!  если брать координту y из vector2 спрайт не вставляется
        batch.draw(downGraundTexture,downGroundVector1.x,GiroFly.HEIGHT-downGraundTexture.getHeight());
        batch.draw(downGraundTexture,downGroundVector2.x,GiroFly.HEIGHT-downGraundTexture.getHeight());
        displayMessage(batch);
        batch.end();

    }

    private void updateGround(){


        //выставление земли
        //WTF!?!
        if(camera.position.x - (camera.viewportWidth/2) > groundVector1.x+graundTexture.getWidth()){
             groundVector1.add(graundTexture.getWidth()*2,0);
             downGroundVector1.add(graundTexture.getWidth()*2,GiroFly.HEIGHT-downGraundTexture.getHeight());
        }
        if (camera.position.x - (camera.viewportWidth/2) > groundVector2.x+graundTexture.getWidth()){
           groundVector2.add(graundTexture.getWidth()*2,0);
           downGroundVector2.add(graundTexture.getWidth()*2,GiroFly.HEIGHT-downGraundTexture.getHeight());
        }


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
