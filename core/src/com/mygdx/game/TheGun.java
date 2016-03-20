package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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
        batch.draw(myTexture, position.x, position.y, myTexture.getWidth()/2, myTexture.getHeight()/4,
                myTexture.getWidth(), myTexture.getHeight(), 1.0f, 1.0f, angle, 0, 0, myTexture.getWidth(),
                myTexture.getHeight() ,false, false );
    }

    public void update(){
        angle = (float) Math.toDegrees(Math.atan2(Gdx.graphics.getHeight() - position.y - myTexture.getWidth()/2 - Gdx.input.getY() + 3, Gdx.input.getX() - myTexture.getHeight()/2 -  position.x + 3)) - 90;
        if(angle < 0){
            angle += 360;
        }
    }
}
