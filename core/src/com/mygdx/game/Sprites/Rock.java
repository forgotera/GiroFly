package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GiroFly;

import java.util.Random;

public class Rock {

    private Texture upRockTexture;
    private Texture downRockTexture;

    private Vector2 upRockVector;
    private Vector2 downRockVector;
    private Polygon upRockPolygon;
    private Polygon downRockPolygon;
    private boolean isOverlapsUp = false;
    private boolean isOverlapsDown = false;

    public static final int WIDTH = 40;

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

        Random random = new Random();


        // формы скалам для соударения
        if(random.nextInt(2) ==0) {
            upRockVector = new Vector2(x ,0);
            upRockPolygon  = new Polygon(PolygonPointUpRock());
        }else {
            downRockVector = new Vector2(x, GiroFly.HEIGHT/2);
            downRockPolygon = new Polygon(PolygonPointDownRock());
        }

    }


    private float [] PolygonPointUpRock() {
        float[] upRockPolylinePoints = new float[6];
        upRockPolylinePoints[0] = upRockVector.x;
        upRockPolylinePoints[1] = (0.0f);
        upRockPolylinePoints[2] = (upRockTexture.getWidth() / 2) + upRockVector.x;
        upRockPolylinePoints[3] = (float) upRockTexture.getHeight();
        upRockPolylinePoints[4] = upRockVector.x + upRockTexture.getWidth();
        upRockPolylinePoints[5] = 0;
        return upRockPolylinePoints;
    }

    private float[] PolygonPointDownRock(){
        float[] downRockPolylinePoints = new float[6];
        downRockPolylinePoints[0] = downRockVector.x;
        downRockPolylinePoints[1] = GiroFly.HEIGHT;
        downRockPolylinePoints[2] =  (downRockTexture.getWidth()/2)+downRockVector.x;
        downRockPolylinePoints[3] = (float)  downRockTexture.getHeight();
        downRockPolylinePoints[4] = downRockVector.x + downRockTexture.getWidth();
        downRockPolylinePoints[5] = GiroFly.HEIGHT;
        return downRockPolylinePoints;
    }


    public boolean colight(Polygon girocopter){
       if(upRockPolygon != null){
           isOverlapsUp = Intersector.overlapConvexPolygons(upRockPolygon,girocopter);
       }
       if(downRockPolygon != null){
           isOverlapsDown = Intersector.overlapConvexPolygons(downRockPolygon,girocopter);
       }
        return  isOverlapsUp || isOverlapsDown;
    }


    public  void dispose(){
        downRockTexture.dispose();
        upRockTexture.dispose();
    }
}
