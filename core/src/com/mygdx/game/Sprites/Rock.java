package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Rock {

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

    private Texture upRockTexture;
    private Texture downRockTexture;

    private Vector2 upRockVector;
    private Vector2 downRockVector;

    public Rock(float x){

        upRockTexture = new Texture("rock.png");
        downRockTexture = new Texture("rockDown.png");

        Random random = new Random();

        upRockVector = new Vector2(x,0);
        //fixme расстояние межну скалами не должно быть меньше 250(200)
        downRockVector = new Vector2(upRockVector.x +(random.nextInt(1)+250),220);

    }
}
