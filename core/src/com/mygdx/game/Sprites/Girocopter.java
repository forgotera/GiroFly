package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GiroFly;


public class Girocopter {
    private static int downRockHeight = 0;

    private static final int MOVENT = 100;
    private static  final int GRAVITY = -10;
    private Vector3 position;
    private Vector3 velosity;

    private Polygon giroPoligon;

    private Texture gyroTexture;

    public Girocopter(int x,int y) {
        position =  new Vector3(x , y,0);
        velosity = new Vector3(0,0,0);
        gyroTexture = new Texture("planeYellow1.png");
        giroPoligon = new Polygon(polygonGirocopterPoint());
    }

    //Fixme надо немнного подравить размеры спрайта
    private float[] polygonGirocopterPoint(){
        //обрисовка вертолета по координинатам
        return new float[] {0,14,5,10,21,10,21,0,74,0,73,7,86,18,86,64,82,67,68,66,6,72,57,72,47,65,33,61,25,65,18,57,19,44,9,32,1,29};
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

        if(position.y < upRockTexture.getHeight()/4) position.y = upRockTexture.getHeight()/4;
        if (position.y > GiroFly.HEIGHT - downRockTexture.getHeight()/2) position.y = GiroFly.HEIGHT-downRockTexture.getHeight()/2;

        velosity.scl(1/delta);
       giroPoligon.setPosition(position.x,position.y);
    }

    public void move(){
        velosity.y +=25;
        //фикс прилипания к вершнинам
        if (position.y >= GiroFly.HEIGHT-downRockHeight/2){
            velosity.y = 0;
        }

    }
    public void dispose(){gyroTexture.dispose();}

    public Polygon getGyrocopter(){
        return giroPoligon;
    }
}
