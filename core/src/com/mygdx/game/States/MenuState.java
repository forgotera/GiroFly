package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GiroFly;

public class MenuState extends  State {

    private Texture back;
    private Texture buttonStart;
    private Sprite  buttonStartSprite;
    private Sprite  buttonExitSprite;
    private Sprite backGroundSprite;
    private OrthographicCamera camera;

    protected MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera = new OrthographicCamera(GiroFly.WIDTH,GiroFly.HEIGHT);
        camera.setToOrtho(false);
        back = new Texture("UIbg.png");
        backGroundSprite = new Sprite(back);
        buttonStart = new Texture("buttonLarge.png");
        Texture buttonExit = new Texture("buttonLarge.png");
        buttonStartSprite = new Sprite(buttonStart);
        buttonExitSprite = new Sprite(buttonExit);
        // задаём относительный размер
        float BUTTON_RESIZE_FACTOR = 800f;
        buttonStartSprite.setSize(buttonStartSprite.getWidth() *(GiroFly.WIDTH/ BUTTON_RESIZE_FACTOR), buttonStartSprite.getHeight()*(GiroFly.WIDTH/ BUTTON_RESIZE_FACTOR));
        buttonExitSprite.setSize(buttonExitSprite.getWidth() *(GiroFly.WIDTH/ BUTTON_RESIZE_FACTOR), buttonExitSprite.getHeight()*(GiroFly.WIDTH/ BUTTON_RESIZE_FACTOR));
        backGroundSprite.setSize(GiroFly.WIDTH,GiroFly.HEIGHT);
        // задаём позицию конпки start
        float START_VERT_POSITION_FACTOR = 2.7f;
        buttonStartSprite.setPosition((GiroFly.WIDTH/2f -buttonStartSprite.getWidth()/2) , GiroFly.WIDTH/ START_VERT_POSITION_FACTOR);
        // задаём позицию кнопки exit
        float EXIT_VERT_POSITION_FACTOR = 4.2f;
        buttonExitSprite.setPosition((GiroFly.WIDTH/2f -buttonExitSprite.getWidth()/2) , GiroFly.WIDTH/ EXIT_VERT_POSITION_FACTOR);

    }

    @Override
    protected void handleInput() {
        Vector3 temp = new Vector3();
        if(Gdx.input.justTouched()) {
            // Получаем координаты касания и устанавливаем эти значения в временный вектор
            temp.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            // получаем координаты касания относительно области просмотра нашей камеры
            camera.unproject(temp);
            float touchX = temp.x;
            float touchY= temp.y;
            // обработка касания по кнопке Stare
            if((touchX>=buttonStartSprite.getX()) && touchX<= (buttonStartSprite.getX()+buttonStartSprite.getWidth()) && (touchY>=buttonStartSprite.getY()) && touchY<=(buttonStartSprite.getY()+buttonStartSprite.getHeight()) ){
                gameStateManager.set(new GameState(gameStateManager));
            }
            // обработка касания по кнопке Exit
            else if((touchX>=buttonExitSprite.getX()) && touchX<= (buttonExitSprite.getX()+buttonExitSprite.getWidth()) && (touchY>=buttonExitSprite.getY()) && touchY<=(buttonExitSprite.getY()+buttonExitSprite.getHeight()) ){
                Gdx.app.exit(); // выход из приложения
            }
        }

    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        backGroundSprite.draw(batch);
        buttonStartSprite.draw(batch);
        buttonExitSprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        back.dispose();
        buttonStart.dispose();
    }
}
