package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Girocopter {

    private static  final int GRAVITY = -10;
    private Vector3 position;
    private Vector3 velosity;

    private Texture gyroTexture;

    public Girocopter(int x,int y) {
        position =  new Vector3(x , y,0);
        velosity = new Vector3(0,0,0);
        gyroTexture = new Texture("planeYellow1.png");
    }

    public Texture getGyroTexture() { return gyroTexture; }

    public Vector3 getPosition() { return position; }

    public void update(float delta){
        if (position.y > 0) {
            velosity.add(0, GRAVITY, 0);
        }
        velosity.scl(delta);
        position.add(0, velosity.y, 0);

        if(position.y < 0) position.y = 0;
        if (position.y > 400) position.y = 400;

        velosity.scl(1/delta);
    }

    public void move(){velosity.y +=25;}

    public void dispose(){gyroTexture.dispose();}
}
