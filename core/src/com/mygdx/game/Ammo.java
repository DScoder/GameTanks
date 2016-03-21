package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ilya on 21.03.2016.
 */
public class Ammo {
    private Vector2 position;
    protected float angle;
    protected static Texture myTexture;
    protected float velocityX;
    protected float velocityY;
    protected boolean alive;

    public Ammo(Vector2 position, float angle) {
        this.position = position;
        this.angle = angle;
        myTexture = new Texture("Bullet16x16.png");
        alive = true;
    }

    public void draw(SpriteBatch batch){
        batch.draw(myTexture, position.x, position.y, 15, 0,
                myTexture.getWidth(), myTexture.getHeight(), 1.0f, 1.0f, angle, 0, 0, myTexture.getWidth(),
                myTexture.getHeight() ,false, false );
    }

    public void destroy(){
        alive = false;
    }

    public void update(){
//        velocityX = (float)Math.atan(angle-180);
//        velocityY = (float)Math.atan(angle-90);
//        position.x+=velocityX;
//        position.y+=velocityY;
    }

    public Vector2 getPosition() {
        return position;
    }
}
