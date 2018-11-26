package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GiroFly;


public class Girocopter {
    private static int downRockHeight = 0;
    private  static int upRockHeight = 0;

    private static final int MOVENT = 100;
    private static  final int GRAVITY = -10;
    private Vector3 position;
    private Vector3 velosity;

    private Rectangle giroRedtangle;

    private Texture gyroTexture;

    public Girocopter(int x,int y) {
        position =  new Vector3(x , y,0);
        velosity = new Vector3(0,0,0);
        gyroTexture = new Texture("planeYellow1.png");
        giroRedtangle = new Rectangle(x,y,gyroTexture.getWidth()-50,gyroTexture.getHeight()-50);
    }

    public Texture getGyroTexture() { return gyroTexture; }

    public Vector3 getPosition() { return position; }

    public void update(float delta , Texture upRockTexture, Texture downRockTexture){
        if (position.y > 0) {
            velosity.add(0, GRAVITY, 0);
        }
        velosity.scl(delta);
        position.add(MOVENT*delta, velosity.y, 0);
        downRockHeight = downRockTexture.getHeight();
        upRockHeight =upRockTexture.getHeight();

        if(position.y < upRockTexture.getHeight()/4) position.y = upRockTexture.getHeight()/4;
        if (position.y > GiroFly.HEIGHT - downRockTexture.getHeight()/2) position.y = GiroFly.HEIGHT-downRockTexture.getHeight()/2;

        velosity.scl(1/delta);
        giroRedtangle.setPosition(position.x,position.y);
    }

    public void move(){
        velosity.y +=25;
        //фикс прилипания к вершнинам
        if (position.y >= GiroFly.HEIGHT-downRockHeight/2){
            velosity.y = 0;
        }

    }
    public void dispose(){gyroTexture.dispose();}

    public Rectangle getGyrocopter(){
        return giroRedtangle;
    }
}
