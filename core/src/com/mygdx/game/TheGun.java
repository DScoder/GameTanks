package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

/**
 * Created by Ilya on 19.03.2016.
 */
public class TheGun {
    protected Vector2 position;
    protected float angle;
    protected Texture myTexture;

    public TheGun(Vector2 position) {
        this.position = position;
        angle = 0;
        myTexture = new Texture("Gun130x40.png");
    }

    public void draw(SpriteBatch batch){
        batch.draw(myTexture, position.x, position.y, myTexture.getWidth()/2, myTexture.getHeight()/2,
                myTexture.getWidth(), myTexture.getHeight(), 1.0f, 1.0f, angle, 0, 0, myTexture.getWidth(),
                myTexture.getHeight() ,false, false );
    }

    public void update(){
        Point location = MouseInfo.getPointerInfo().getLocation();
        position.x = (float)location.getX() - myTexture.getWidth()/2;
        position.y = 600 - (float)location.getY();



    }
}
