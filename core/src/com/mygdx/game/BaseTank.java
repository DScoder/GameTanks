package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by DScoder on 19.03.2016.
 */
public abstract class BaseTank {
    protected Vector2 position;
    protected float velocityX;
    protected float velocityY;
    protected float angle;
    protected Texture myTexture;
    protected ArrayList<Ammo> ams;
    protected float time;

    public BaseTank(Vector2 position) {
        this.position = position;
        this.angle = 0;
    }

    public abstract void draw(SpriteBatch batch);

    public void update() {
        if (position.x > Gdx.graphics.getWidth() - 100 - myTexture.getHeight() && velocityX > 0 || position.x < 0 && velocityX < 0)
            velocityX = 0;
        if (position.y > Gdx.graphics.getHeight() - myTexture.getHeight() && velocityY > 0 || position.y < 0 && velocityY < 0)
            velocityY = 0;
    }

    public void shoot() {
    }
}


