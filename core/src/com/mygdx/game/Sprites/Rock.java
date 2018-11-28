package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GiroFly;

import java.util.Random;

public class Rock {

    private Texture upRockTexture;
    private Texture downRockTexture;

    private Vector2 upRockVector;
    private Vector2 downRockVector;
    private  Rectangle upRockRectangle;
    private Rectangle downRockRectandle;

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
            upRockRectangle = new Rectangle(upRockVector.x, upRockVector.y, upRockTexture.getWidth(), GiroFly.HEIGHT / 2);
        }else {
            downRockVector = new Vector2(x, GiroFly.HEIGHT/2);
            downRockRectandle = new Rectangle(downRockVector.x, downRockVector.y, downRockTexture.getWidth(), GiroFly.HEIGHT / 2);
        }

        //FIXME как сделать фигруы треукгольнвми?
         /**
        upRockTriangle = new MeshSpawnShapeValue.Triangle(upRockVector.x,upRockVector.y,0,upRockVector.x+WIDTH,upRockVector.y,0
                ,(upRockVector.x - (upRockVector.x+WIDTH))/2,upRockVector.y+240,0);
        downRockTrinale = new MeshSpawnShapeValue.Triangle(downRockVector.x,downRockVector.y,0,downRockVector.x+WIDTH,downRockVector.y,0,
                (downRockVector.x-(downRockVector.x+WIDTH)/2),downRockVector.y+240,0);
         **/
    }




    public boolean colight(Rectangle player){
        return player.overlaps(upRockRectangle) || player.overlaps(downRockRectandle);
    }

    public  void dispose(){
        downRockTexture.dispose();
        upRockTexture.dispose();
    }
}
