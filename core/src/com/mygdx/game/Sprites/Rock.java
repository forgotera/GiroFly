package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Rock {

    private Texture upRockTexture;
    private Texture downRockTexture;

    private Vector2 upRockVector;
    private Vector2 downRockVector;
    private  Random random;

    public static final int WIDTH = 40;
    private static final int SPACE = 200;

    public Texture getUpRockTexture() {
        return upRockTexture;
    }

    public Texture getDownRockTexture() {
        return downRockTexture;
    }

    public Vector2 getUpRockVector() {
        return upRockVector;
    }

    public Vector2 getDownRockVector() {
        return downRockVector;
    }

    public Rock(float x){

        upRockTexture = new Texture("rock.png");
        downRockTexture = new Texture("rockDown.png");

        random = new Random();

        upRockVector = new Vector2(x ,0);
        //рандомное расстояние от 250 до 320
        downRockVector = new Vector2(upRockVector.x+random.nextInt(120)+SPACE, 240);

    }

    /**
     * изменение позиции при движение камеры
     * @param x - координата x для скалы
     */
    public void reposition(float x){

        upRockVector = new Vector2(x ,0);
        downRockVector = new Vector2(upRockVector.x+random.nextInt(120)+SPACE , 240);

    }

    public  void dispose(){
        downRockTexture.dispose();
        upRockTexture.dispose();
    }
}
