package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Rock {
    private Texture rockTexrure;
    private Texture rockDownTexture;
    private Vector2 postRock;
    private Vector2 postDownRock;


    public Rock() {
        rockTexrure = new Texture("rock.png");
        rockDownTexture = new Texture("rockDown.png");
    }

}
